package com.wangyi.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangyi.common.config.RedisKeyConfig;
import com.wangyi.common.util.JwtUtil;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.util.RedissonUtil;
import com.wangyi.common.util.TimeUtil;
import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;
import com.wangyi.dto.TokenDto;
import com.wangyi.entity.User;
import com.wangyi.user.dao.UserDao;
import com.wangyi.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired(required = false)
    private UserDao userDao;

    //登陆方法
    @Override
    public R login(LoginDto loginDto) {
        //验证账号是否可用(是否被冻结)
        if (RedissonUtil.checkKey(RedisKeyConfig.FREEZE_USER + loginDto.getPhone())) {
            return RUtil.setERROR("账号被冻结，请稍后重试");
        } else {
            //1、校验手机号是否存在
            User user = userDao.selectByName(loginDto.getPhone());
            if (user != null) {
                //2、校验密码是否合法
                if (user.getPassword().equals(loginDto.getPass())) {
                    //3、生成令牌
                    TokenDto tokenDto = new TokenDto();
                    tokenDto.setId(user.getId());
                    tokenDto.setPhone(user.getTelephone());
                    String token = JwtUtil.createJWT(JSON.toJSONString(tokenDto));

                    //4、存储令牌  10分钟
                    if (RedissonUtil.checkKey(RedisKeyConfig.USER_TOKEN + user.getTelephone() + loginDto.getFacility())) {
                        //当前设备之前登录过  记录挤掉的信息
                        RedissonUtil.saveStr(RedisKeyConfig.LOSE_TOKEN + RedissonUtil.getStr
                                (RedisKeyConfig.USER_TOKEN + user.getTelephone() + loginDto.getFacility()), JSON.toJSONString(loginDto));
                    }

                    RedissonUtil.saveStr(RedisKeyConfig.USER_TOKEN + user.getTelephone() + loginDto.getFacility(), token, 600);
                    RedissonUtil.saveStr(RedisKeyConfig.TOKEN_USER + token, JSON.toJSONString(user));

                    //5、返回令牌
                    return RUtil.setOK("登录成功", token);

                }

                //登陆失败 记录失败次数 并校验是否需要冻结账号
                List<String> list = RedissonUtil.getKeys(RedisKeyConfig.FALL_USER + loginDto.getPhone());
                RedissonUtil.saveStr(RedisKeyConfig.FALL_USER + loginDto.getPhone() + TimeUtil.getCurrentTime(),"fail",15*60);
                if (list != null && list.size() == 3) {
                    //冻结账号
                    RedissonUtil.saveStr(RedisKeyConfig.FREEZE_USER + loginDto.getPhone(),TimeUtil.getTime(),30*60);
                    return RUtil.setERROR("连续输入错误3次，账号被冻结");
                }
                return RUtil.setERROR("手机号或密码错误");
            }

            return RUtil.setERROR("手机号或密码错误");
        }
    }

    //校验令牌有效性
    @Override
    public R checkLogin(String token) {
        // 检查令牌是否存在
        if (RedissonUtil.checkKey(RedisKeyConfig.TOKEN_USER + token)) {
            // 令牌存在
            return RUtil.setOK("令牌有效");
        } else {
            //验证是否被挤掉
            if (RedissonUtil.checkKey(RedisKeyConfig.LOSE_TOKEN + token)) {
                LoginDto loginDto = JSON.parseObject(RedissonUtil.getStr(RedisKeyConfig.LOSE_TOKEN + token),LoginDto.class);
                return RUtil.setERROR("已在其他设备登录：" + loginDto.getFacinfo());
            } else {
                return RUtil.setERROR("登录失效，请重新登录");
            }
        }
    }

    //用户退出方法
    @Override
    public R logout(String token) {
        TokenDto tokenDto = JSON.parseObject(JwtUtil.parseJWT(token), TokenDto.class);
        User user = userDao.selectByName(tokenDto.getPhone());
        //注销 清除 redis中与登陆有关的信息
        if (RedissonUtil.checkKey(RedisKeyConfig.TOKEN_USER + token)) {

            RedissonUtil.delKey(RedisKeyConfig.TOKEN_USER + token);
            return RUtil.setOK("退出成功");
        } else {
            return RUtil.setOK("登录已失效");
        }
    }

    @Override
    public R findPass(LoginDto loginDto) {
        if (RedissonUtil.checkKey(RedisKeyConfig.FREEZE_USER + loginDto.getPhone())) {
            //账户已冻结
            return RUtil.setERROR("账号异常，不允许找回");
        } else {
            boolean ret = userDao.updatePass(loginDto) > 0;
            if (ret) {
                //密码修改成功 清空相关的各种Key
                String token = RedissonUtil.getStr(RedisKeyConfig.USER_TOKEN + loginDto.getPhone() + loginDto.getFacility());
                RedissonUtil.delKey(RedisKeyConfig.TOKEN_USER + token);
                RedissonUtil.delKey(RedisKeyConfig.USER_TOKEN + loginDto.getPhone() + loginDto.getFacility());
            }
            return RUtil.setR(ret,"密码找回");
        }
    }

    @Override
    public R checkFreeze(String phone) {
        if (RedissonUtil.checkKey(RedisKeyConfig.FREEZE_USER + phone)) {
            //账户被冻结
            return RUtil.setERROR("账号被冻结");
        } else {
            return RUtil.setOK("账号可用");
        }
    }
}
