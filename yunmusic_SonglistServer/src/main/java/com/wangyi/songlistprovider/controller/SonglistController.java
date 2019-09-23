package com.wangyi.songlistprovider.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.entity.Lyric;
import com.wangyi.entity.Songlist;
import com.wangyi.songlistprovider.common.Tag;
import com.wangyi.songlistprovider.service.SonglistService;
import com.wangyi.songlistprovider.vo.VSongInfo;
import com.wangyi.songlistprovider.vo.VSongInfo2;
import com.wangyi.songlistprovider.vo.VSonglistInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SonglistController {

    @Autowired(required = false)
    private SonglistService songlistService;

//------------------------- 歌单分类页 -------------------------
    /**
     * xc
     * 查看所有歌单标签
     * @return
     */
    @GetMapping("/songlist/queryAllTags.do")
    public R queryAll() {

        List<Tag> list = songlistService.queryAllTags();

        return RUtil.setOK("查询所有歌单标签成功！", list);
    }

    /**
     * xc
     * 查看所有歌单，分页，按播放量从高到低，可根据标签查询
     * @param map(page 页码, size 当前页展示数量, tag 标签)
     * @return
     */
    @PostMapping("/songlist/queryAll.do")
    public R queryAll(@RequestBody Map<String, Object> map) {
        // 分页
        Page<Songlist> page = new Page<>((int) map.get("page"), (int) map.get("size"));
        String tag = (String) map.get("tag");

        List<Songlist> list = songlistService.queryAll(page, tag);

        return RUtil.setOK("查询所有歌单成功！", list);
    }

//------------------------- 歌单页 -------------------------
    /**
     * xc
     * 查看某一“用户歌单”类型的歌单
     * @param songlistid 歌单ID
     * @return
     */
    @GetMapping("/songlist/queryOneSonglistWithSongs.do")
    public R queryOneSonglistWithSongs(int songlistid) {
        VSonglistInfo songlist = songlistService.selectOneSonglistWithSongs(songlistid);
        return RUtil.setOK("查询单张歌单成功！", songlist);
    }

    /**
     * xc
     * 查看某一“专辑”类型的歌单
     * @param songlistid 歌单ID
     * @return
     */
    @GetMapping("/songlist/queryOneAlbumWithSongs.do")
    public R queryOneAlbumWithSongs(int songlistid) {
        VSonglistInfo songlist = songlistService.selectOneAlbumWithSongs(songlistid);
        return RUtil.setOK("查询单张专辑成功！", songlist);
    }

    /**
     * xc
     * 对某歌单内歌曲，根据 歌名/歌手/专辑 进行搜索
     * @param songlistid 歌单ID
     * @param keyword 搜索关键字
     * @return
     */
    @GetMapping("/songlist/querySongsByKeyword.do")
    public R querySongsByKeyword(int songlistid, String keyword) {
        List<VSongInfo> list = songlistService.querySongsByKeyword(songlistid, keyword);
        return RUtil.setOK("搜索成功！", list);
    }

//------------------------- 歌曲播放页 -------------------------
    /**
     * xc
     * 查询 正在播放歌曲的信息
     * @param songid 歌曲ID
     * @param songlistid 来源歌单ID
     * @return
     */
    @GetMapping("/songlist/queryPlaySongById.do")
    public R querySonglistNameById(Integer songid, Integer songlistid) {
        VSongInfo2 song = songlistService.queryPlaySongById(songid);
        if (songlistid != null) {
            String songlistName = songlistService.querySonglistNameById(songlistid);
            song.setSourceid(songlistid);
            song.setSourcename(songlistName);
        }
        return RUtil.setOK("查询成功！", song);
    }

    /**
     * xc
     * 查询 当前播放歌曲的歌词
     * @param songid 歌曲ID
     * @return
     */
    @GetMapping("/songlist/queryLyric.do")
    public R queryLyric(Integer songid) {
        Lyric lyric = songlistService.queryLyric(songid);
        if (lyric != null) {
            return RUtil.setOK("查询成功！", lyric);
        } else {
            return RUtil.setOK("查询成功！", "暂无歌词");
        }
    }

}
