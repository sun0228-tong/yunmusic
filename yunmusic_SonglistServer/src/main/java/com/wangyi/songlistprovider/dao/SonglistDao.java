package com.wangyi.songlistprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyi.entity.Lyric;
import com.wangyi.entity.Songlist;
import com.wangyi.songlistprovider.common.Tag;
import com.wangyi.songlistprovider.vo.VSongInfo;
import com.wangyi.songlistprovider.vo.VSongInfo2;
import com.wangyi.songlistprovider.vo.VSonglistInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SonglistDao extends BaseMapper<Songlist> {

    /**
     * xc
     * 查询所有歌单标签
     * @return
     */
    List<Tag> selectAllTags();

    /**
     * xc
     * 查询所有歌单，按播放量从高到底
     * @param tag 可根据标签搜索
     * @return
     */
    List<Songlist> selectAll(Page<Songlist> page, @Param("tag") String tag);

    /**
     * xc
     * 查询 某一歌单（用户歌单） 及其所所含歌曲
     * @param songlistid
     * @return
     */
    VSonglistInfo selectOneSonglistWithSongs(int songlistid);

    /**
     * xc
     * 查询 某一歌单（专辑） 及其所所含歌曲
     * @param songlistid
     * @return
     */
    VSonglistInfo selectOneAlbumWithSongs(int songlistid);

    /**
     * xc
     * 歌单内，根据（歌曲名/歌手/专辑)，对歌曲模糊查询
     * @param keyword 查询关键字
     * @return
     */
    List<VSongInfo> selectSongsByKeyword(@Param("songlistid") int songlistid, @Param("keyword") String keyword);

    /**
     * xc
     * 查询 正在播放歌曲的信息
     * @param songid 歌曲ID
     * @return
     */
    VSongInfo2 selectPlayingSong(int songid);

    /**
     * xc
     * 查询歌单名
     * @param songlistid 歌单ID
     * @return
     */
    String selectSonglistNameById(Integer songlistid);

    /**
     * xc
     * 查询歌曲对应歌词
     * @param songid
     * @return
     */
    Lyric selectLyric(Integer songid);
}
