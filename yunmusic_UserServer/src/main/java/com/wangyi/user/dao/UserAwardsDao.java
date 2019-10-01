package com.wangyi.user.dao;

import com.wangyi.entity.UserAwards;
import org.apache.ibatis.annotations.Insert;

public interface UserAwardsDao {

    @Insert("insert into userawards(uid, depletemoney, result, extramoney) VALUES (#{uid},#{depletemoney},#{result},#{extramoney})")
    int save(UserAwards userAwards);
}
