package com.wangyi.rankprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyi.entity.Songlist;

import java.util.List;


public interface RankHotDao extends BaseMapper<Songlist> {
    /**
     * ccq
     * 查询最热歌曲
     * @return
     */
    List<Songlist> selectHotList();
}
