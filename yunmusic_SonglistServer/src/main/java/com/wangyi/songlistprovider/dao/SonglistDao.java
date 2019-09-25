package com.wangyi.songlistprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyi.entity.Lyric;
import com.wangyi.entity.Singer;
import com.wangyi.entity.Song;
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

    /**
     * xc
     * 按热度从高到低，查询所有歌手
     * @param page 分页信息
     * @param language 歌手语种
     * @param type 歌手类型
     * @param firstPinyin 歌手名首字母
     * @return
     */
    List<Singer> selectAllSinger(Page<Songlist> page, @Param("language") String language, @Param("type") String type, @Param("firstPinyin") String firstPinyin);

    /**
     * xc
     * 查询歌手的最热门的50首歌
     * @param singerid
     * @return
     */
    List<Song> selectTop50BySingerId(int singerid);

    /**
     * xc
     * 查询歌手的所有专辑以及专辑内对应歌曲
     * @param singerid
     * @return
     */
    List<VSonglistInfo> selectAllAlbumWithSongBySingerId(int singerid);

    /**
     * xc
     * 查询歌手名
     * @param singerid
     * @return
     */
    String selectSingerName(int singerid);

    /**
     * xc
     * 查询歌手专辑数
     * @param singerid
     * @return
     */
    int selectSingerAlbumCount(int singerid);

    /**
     * xc
     * 查询歌手MV数
     * @param singerid
     * @return
     */
    int selectSingerVideoCount(int singerid);

    /**
     * xc
     * 查询歌手详情
     * @param singerid
     * @return
     */
    String selectSingerDetail(int singerid);
}
