package com.wangyi.userapi.service;

import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;
import com.wangyi.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("UserProvider")
public interface UserService {

    @GetMapping("server/user/checkPhone.do")
    R checkPhone(@RequestParam("phone") String phone);

    @GetMapping("server/user/checkEmail.do")
    R checkEmail(@RequestParam("email") String email);

    @PostMapping("server/user/save.do")
    R save(@RequestBody UserDto userDto);

    @PostMapping("server/user/changePass")
    R changePass(@RequestBody LoginDto loginDto);

}
