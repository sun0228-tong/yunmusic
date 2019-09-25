package com.wangyi.usercollectionprovider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyi.entity.Uservideo;
import com.wangyi.usercollectionprovider.dao.UserVideoDao;
import com.wangyi.usercollectionprovider.service.UserVideoService;
import org.springframework.stereotype.Service;

@Service
public class UserVideoServiceImpl extends ServiceImpl<UserVideoDao, Uservideo> implements UserVideoService {
}
