package com.wangyi.user.controller;

import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;
import com.wangyi.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登录
    @PostMapping("server/login/login.do")
    public R login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    //校验令牌
    @GetMapping("server/login/checkLogin.do")
    public R checkLogin(@RequestParam String token) {
        return loginService.checkLogin(token);
    }

    //注销
    @GetMapping("server/login/logout.do")
    public R logout(@RequestParam String token) {
        return loginService.logout(token);
    }

    //找回密码 调用接口步骤：
     /*
     1、校验手机号是否存在、冻结
     2、发送短信
     3、校验短信验证码
     4、重置密码
      */
    @PostMapping("server/login/findpass.do")
    public R findPass(@RequestBody LoginDto loginDto) {
        return loginService.findPass(loginDto);
    }

    // 检验账户是否被冻结
    @GetMapping("server/login/checkFreeze.do")
    public R checkFreeze(@RequestParam String phone) {
        return loginService.checkFreeze(phone);
    }
}
