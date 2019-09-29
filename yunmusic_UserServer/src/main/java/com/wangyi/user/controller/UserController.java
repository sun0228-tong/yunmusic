package com.wangyi.user.controller;

import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;
import com.wangyi.dto.UserDto;
import com.wangyi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("server/user/checkPhone.do")
    public R checkPhone(@RequestParam("phone") String phone) {
        return userService.checkPhone(phone);
    }

    @GetMapping("server/user/checkEmail.do")
    public R checkEmail(@RequestParam("email") String email) {
        return userService.checkEmail(email);
    }

    @PostMapping("server/user/save.do")
    public R save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PostMapping("server/user/changePass")
    public R changePass(@RequestBody LoginDto loginDto) {
        return userService.changePass(loginDto);
    }
}
