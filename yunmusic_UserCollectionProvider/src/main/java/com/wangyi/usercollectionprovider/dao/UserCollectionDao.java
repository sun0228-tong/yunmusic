package com.wangyi.usercollectionprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyi.entity.User;
import com.wangyi.entity.Usersonglist;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserCollectionDao extends BaseMapper<Usersonglist> {

    @Select("select u.* from user u inner join usersonglist us on u.id = us.userid where us.songlistid = #{id} limit #{page},#{size}")
    List<User> pageList(@Param("size") int size, @Param("page") int page, @Param("id") int id);

    @Select("select count(*) from user u inner join usersonglist us on u.id = us.userid where us.songlistid = #{id}")
    int pageListCount(int id);
}
