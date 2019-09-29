package com.wangyi.user.dao;

import com.wangyi.dto.LoginDto;
import com.wangyi.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao {

    //新增
    @Insert("insert into user(telephone,email,password) values (#{telephone},#{email},#{password})")
    int insert(User user);

    //查询 手机号或邮箱号
    @Select("select * from user where telephone = #{name} or email = #{name}")
    User selectByName(String name);

    //通过手机号修改密码
    @Update("update user set password = #{pass} where telephone = #{phone}")
    int updatePass(LoginDto loginDto);
}
