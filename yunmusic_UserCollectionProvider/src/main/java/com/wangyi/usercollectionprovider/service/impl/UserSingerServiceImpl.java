package com.wangyi.usercollectionprovider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyi.entity.Usersinger;
import com.wangyi.usercollectionprovider.dao.UserSingerDao;
import com.wangyi.usercollectionprovider.service.UserSingerService;
import org.springframework.stereotype.Service;

@Service
public class UserSingerServiceImpl extends ServiceImpl<UserSingerDao, Usersinger> implements UserSingerService {
}
