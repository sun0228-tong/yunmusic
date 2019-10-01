package com.wangyi.user.service;

import com.wangyi.common.vo.R;

public interface UserSignService {

    R save(String token);

    R checkSign(String token);

    R queryMonth(String token);

    R checkLotto(String token);

    R startLotto(String token);
}
