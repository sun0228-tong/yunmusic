package com.wangyi.userapi.service;

import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("UserProvider")
public interface LoginService {

    @PostMapping("server/login/login.do")
    R login(@RequestBody LoginDto loginDto);

    @GetMapping("server/login/checkLogin.do")
    R checkLogin(@RequestParam String token);

    @GetMapping("server/login/logout.do")
    R logout(@RequestParam String token);

    //找回密码 调用接口步骤：
     /*
     1、校验手机号是否存在、冻结
     2、发送短信
     3、校验短信验证码
     4、重置密码
      */
    @PostMapping("server/login/findpass.do")
    R findPass(@RequestBody LoginDto loginDto);

    // 检验账户是否被冻结
    @GetMapping("server/login/checkFreeze.do")
    R checkFreeze(@RequestParam String phone);
}
