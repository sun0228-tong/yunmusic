package com.wangyi.rankprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyi.rankprovider.vo.VRankInfo;
import com.wangyi.rankprovider.vo.VRankSongInfo;
import com.wangyi.rankprovider.vo.VSongLisTInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RankHotDao extends BaseMapper<VRankInfo> {
    /**
     * ccq
     * 查询榜单最热歌曲
     *
     * @return
     */
    List<VRankInfo> selectHotList();

    /**
     * 根据歌单id删除中间表的歌曲id
     *
     * @param songlistid 歌单id
     * @return
     */
    int deleteById(int songlistid);

    /**
     * 查询播放热度最高的歌曲（不根据热歌榜歌单查）
     *
     * @return
     */
    List<VRankSongInfo> selectByPlayNum();

    /**
     * 批量插入歌曲到歌单
     *
     * @param songlistid 歌单id
     * @param songids    歌曲id集合
     * @return
     */
    int insertSongBatch(@Param("songlistid") int songlistid, @Param("songids") List<Integer> songids);

    /**
     * 根据歌单id更新歌单更新时间
     *
     * @param songlistid 歌单id
     * @return
     */
    int updateByTime(int songlistid);

    /**
     *查出歌单歌曲
     * @return
     */
    List<VSongLisTInfo> selectHotSongs(int songlistid);
}
