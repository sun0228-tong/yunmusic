package com.wangyi.user.service;

import com.wangyi.common.vo.R;
import com.wangyi.dto.LoginDto;

public interface LoginService {

    //登录
    R login(LoginDto loginDto);
    //校验令牌是否有效
    R checkLogin(String token);
    //注销
    R logout(String token);
}
