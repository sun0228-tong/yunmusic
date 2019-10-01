package com.wangyi.user.dao;

import com.wangyi.entity.UserSign;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserSignDao {

    @Insert("insert into usersign(basemoney, extramoney, days, ctime, uid) VALUES (#{basemoney},#{extramoney},#{days},now(),#{uid})")
    int save(UserSign userSign);

    //查询最近的签到数据
    @Select("select * from usersign where uid = #{uid} order by ctime desc limit 1")
    UserSign selectByUid(int uid);

    //查询当前月的签到数据
    @Select("select * from usersign where uid = #{uid} and date_format(ctime,'%y-%m') = date_format(current_date,'%y-%m')")
    List<UserSign> selectMonths(int uid);




}
