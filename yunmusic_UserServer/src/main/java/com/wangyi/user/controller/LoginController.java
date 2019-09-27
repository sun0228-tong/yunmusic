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

    @PostMapping("server/login/login.do")
    public R login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    @GetMapping("server/login/checkLogin.do")
    public R checkLogin(@RequestParam String token) {
        return loginService.checkLogin(token);
    }

    @GetMapping("server/login/logout.do")
    public R logout(@RequestParam String token) {
        return loginService.logout(token);
    }
}
