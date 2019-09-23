package com.wangyi.usercollectionprovider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyi.entity.Usersonglist;
import com.wangyi.usercollectionprovider.dao.UserCollectionDao;
import com.wangyi.usercollectionprovider.service.UserCollectionService;
import org.springframework.stereotype.Service;

@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionDao, Usersonglist> implements UserCollectionService {
}
