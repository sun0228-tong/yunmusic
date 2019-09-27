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
}
