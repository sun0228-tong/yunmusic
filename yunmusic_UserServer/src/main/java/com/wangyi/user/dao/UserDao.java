package com.wangyi.user.dao;

import com.wangyi.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    //新增
    @Insert("insert into user(telephone,email,password) values (#{telephone},#{email},#{password})")
    int insert(User user);

    //查询 手机号或邮箱号
    @Select("select * from user where telephone = #{name} or email = #{name}")
    User selectByName(String name);
}
