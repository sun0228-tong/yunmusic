package com.wangyi.user.service.impl;

import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.dto.UserDto;
import com.wangyi.entity.User;
import com.wangyi.user.dao.UserDao;
import com.wangyi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public R save(UserDto userDto) {
        Lock lock = new ReentrantLock();

        try {
            //加锁 防止多线程情况下出现用户名(邮箱，手机号)重复现象
            lock.lock();

            // 判断原因：防止直接使用接口进行注册，恶意攻击
            User user1 = userDao.selectByName(userDto.getEmail());
            User user2 = userDao.selectByName(userDto.getPhone());
            if (user1 == null && user2 == null) {
                User user = new User();
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
                user.setTelephone(userDto.getPhone());
                return RUtil.setR(userDao.insert(user) > 0,"注册账号");
            } else {
                return RUtil.setERROR("用户名已经存在");
            }
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    //可用（不存在） 返回成功
    @Override
    public R checkPhone(String phone) {
        User user = userDao.selectByName(phone);
        return RUtil.setR(user == null,"校验手机号");
    }

    @Override
    public R checkEmail(String email) {
        User user=userDao.selectByName(email);
        return RUtil.setR(user == null,"校验邮箱");
    }
}
