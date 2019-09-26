package com.wangyi.user.service;

import com.wangyi.common.vo.R;
import com.wangyi.dto.UserDto;


public interface UserService {

    R save(UserDto userDto);

    R checkPhone(String phone);

    R checkEmail(String email);
}
