package com.wangyi.userapi.api;

import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;
import com.wangyi.dto.UserDto;
import com.wangyi.userapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "操作用户服务",tags = "操作用户服务")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "校验手机号是否可用",notes = "校验手机号是否可用")
    @GetMapping("server/user/checkphone.do")
    public R checkPhone(@RequestParam("phone") String phone){
        return userService.checkPhone(phone);
    }

    @ApiOperation(value = "校验邮箱是否可用",notes = "校验邮箱是否可用")
    @GetMapping("server/user/checkemail.do")
    public R checkEmail(@RequestParam("email") String email){
        return userService.checkEmail(email);
    }

    @ApiOperation(value = "实现用户的注册",notes = "实现用户的注册")
    @PostMapping("server/user/save.do")
    public R save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @ApiOperation(value = "用户修改密码",notes = "用户修改密码")
    @PostMapping("server/user/changePass.do")
    public R changePass(@RequestBody LoginDto loginDto) {
        return userService.changePass(loginDto);
    }

}
