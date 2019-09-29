package com.wangyi.userapi.api;

import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;
import com.wangyi.userapi.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "用户登陆相关服务",tags = "用户登陆相关服务")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("api/login/login.do")
    @ApiOperation(value = "用户登陆",notes = "用户登陆")
    public R login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    @GetMapping("api/login/checkLogin.do")
    @ApiOperation(value = "检验用户登陆状态",notes = "检验用户登陆状态")
    public R checkLogin(@RequestParam String token) {
        return loginService.checkLogin(token);
    }

    @GetMapping("api/login/logout.do")
    @ApiOperation(value = "用户退出",notes = "用户退出")
    public R logout(@RequestParam String token) {
        return loginService.logout(token);
    }

    @PostMapping("api/login/findPass.do")
    @ApiOperation(value = "找回密码方法",notes = "找回密码方法")
    //调用接口步骤：
    /*
     1、校验手机号是否存在、冻结
     2、发送短信
     3、校验短信验证码
     4、重置密码
      */
    public R findPass(@RequestBody LoginDto loginDto) {
        return loginService.findPass(loginDto);
    }

    @GetMapping("api/login/checkFreeze.do")
    @ApiOperation(value = "判断用户是否被冻结",notes = "判断用户是否被冻结")
    public R checkFreeze(@RequestParam String phone) {
        return loginService.checkFreeze(phone);
    }
}
