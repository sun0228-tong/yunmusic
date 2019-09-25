package com.wangyi.usercollectionprovider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyi.entity.Songlist;
import com.wangyi.usercollectionprovider.dao.UserSongListDao;
import com.wangyi.usercollectionprovider.service.UserSongListService;
import org.springframework.stereotype.Service;

@Service
public class UserSongListServiceImpl extends ServiceImpl<UserSongListDao, Songlist> implements UserSongListService {
}
