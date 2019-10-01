package com.wangyi.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangyi.common.config.RedisKeyConfig;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.util.RedissonUtil;
import com.wangyi.common.util.TimeUtil;
import com.wangyi.common.vo.R;
import com.wangyi.entity.User;
import com.wangyi.entity.UserAwards;
import com.wangyi.entity.UserSign;
import com.wangyi.entity.UserWallet;
import com.wangyi.user.dao.UserAwardsDao;
import com.wangyi.user.dao.UserSignDao;
import com.wangyi.user.dao.UserWalletDao;
import com.wangyi.user.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class UserSignServiceImpl implements UserSignService {

    @Autowired(required = false)
    private UserSignDao userSignDao;

    @Autowired(required = false)
    private UserWalletDao userWalletDao;

    @Autowired(required = false)
    private UserAwardsDao userAwardsDao;

    //用户签到
    @Override
    @Transactional
    public R save(String token) {
        //获取用户id
        int uid = getUid(token);

        if (uid > 0) {
            UserSign userSign = userSignDao.selectByUid(uid);
            //基础奖励 3至6个一壳
            int basemoney = new Random().nextInt(4) + 3;
            //额外奖励
            int extramoney = 0;
            //连续签到天数
            int days = 0;

            if (userSign == null) {
                //首签
                extramoney = 100;
            } else {
                //判断是否重复签到
                if (TimeUtil.getDaysByDay(userSign.getCtime()) == 0) {
                    return RUtil.setERROR("今日已签到");
                }

                //获取当前日期与最近签到日期的差值
                int dayInterval = TimeUtil.getDaysByDay(userSign.getCtime());
                if (dayInterval == 1) {
                    //连续签到
                    days = userSign.getDays() + 1;
                    if (days % 365 == 0) {
                        //连续签到1年
                        extramoney = 365;
                    } else if (days % 30 == 0) {
                        //连续签到 30天
                        extramoney = 40;
                    } else if (days % 7 == 0) {
                        //连续签到 7天
                        extramoney = 20;
                    } else if (days % 5 == 0) {
                        //连续签到 5天
                        extramoney = 10;
                    } else if (days % 3 == 0) {
                        //连续签到 3天
                        extramoney = basemoney;
                    }
                }
            }
            UserSign userSign1 = new UserSign();
            userSign1.setBasemoney(basemoney);
            userSign1.setExtramoney(extramoney);
            userSign1.setDays(days);
            userSign1.setUid(uid);

            userSignDao.save(userSign1);
            //签到成功后修改用户钱包
            userWalletDao.updateMoney(uid,basemoney + extramoney);
            return RUtil.setOK("签到成功",userSign1);
        } else {
            return RUtil.setERROR("登录超时，请重新登录");
        }
    }

    //检查是否可以签到
    @Override
    public R checkSign(String token) {
        //1、获取用户 id
        int uid = getUid(token);
        //2、获取当前用户最后一条签到数据
        UserSign userSign = userSignDao.selectByUid(uid);
        if (userSign != null) {
            //3、日期比较 验证今天有没有签到
            if (TimeUtil.getDaysByDay(userSign.getCtime()) == 0) {
                return RUtil.setERROR("今日已签到");
            }
        }
        return RUtil.setOK("可以签到");
    }

    //查询用户当前月的签到数据
    @Override
    public R queryMonth(String token) {
        int uid = getUid(token);
        if (uid > 0) {
            return RUtil.setOK("OK",userSignDao.selectMonths(uid));
        } else {
            return RUtil.setERROR("登录超时，请重新登录");
        }
    }

    //检查是否可以抽奖
    @Override
    public R checkLotto(String token) {
        int uid = getUid(token);
        UserWallet userWallet = userWalletDao.selectByUid(uid);
        if (userWallet.getMoney() >= 10) {
            return RUtil.setOK("可以抽奖");
        } else {
            return RUtil.setERROR("壹壳不足，请明天再来");
        }
    }

    //开始抽奖 消耗 10壹壳
    @Override
    @Transactional
    public R startLotto(String token) {
        int uid = getUid(token);

        //判断是否可以抽奖
        UserWallet userWallet = userWalletDao.selectByUid(uid);
        if (userWallet.getMoney() >= 10) {
            //开始抽奖
            Random random = new Random();
            //获取随机数 范围是 1-10之间
            int num = random.nextInt(10) + 1;
            UserAwards userAwards = new UserAwards();
            userAwards.setDepletemoney(10);
            userAwards.setUid(uid);
            int extramoney = 0;
            if (num == 10) {
                //五等奖
                userAwards.setExtramoney("奖励10个积分");
                userAwards.setResult("五等奖");
                extramoney = 10;
            } else {
                userAwards.setExtramoney("奖励1个积分");
                userAwards.setResult("参与奖");
                extramoney = 1;
            }
            if (10 - extramoney != 0) {
                userWalletDao.updateMoney(uid, extramoney - 10);
            }
            userAwardsDao.save(userAwards);
            return RUtil.setOK("本次抽奖结束", userAwards);
        } else {
            return RUtil.setERROR("壹壳不足，请明天再来");
        }
    }

    private int getUid(String token) {
        if (RedissonUtil.checkKey(RedisKeyConfig.TOKEN_USER + token)) {
            User user = JSON.parseObject(RedissonUtil.getStr(RedisKeyConfig.TOKEN_USER + token),User.class);
            return user.getId();
        } else {
            return -1;
        }
    }
}
