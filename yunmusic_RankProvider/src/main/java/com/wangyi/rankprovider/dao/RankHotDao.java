package com.wangyi.rankprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.wangyi.rankprovider.vo.VRankInfo;
import com.wangyi.rankprovider.vo.VRankSongInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RankHotDao extends BaseMapper<VRankInfo> {
    /**
     * ccq
     * 查询榜单最热歌曲
     * @return
     */
    List<VRankInfo> selectHotList();

    int deleteById(int songlistid);

    List<VRankSongInfo> selectByPlayNum();

   int insertSongBatch(@Param("songlistid") int songlistid, @Param("songids")List<Integer> songids);
}
