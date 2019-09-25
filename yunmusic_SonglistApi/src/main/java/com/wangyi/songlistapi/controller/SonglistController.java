package com.wangyi.songlistapi.controller;

import com.wangyi.common.vo.R;
import com.wangyi.songlistapi.service.SonglistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "歌单模块", tags = "歌单模块")
@RestController
public class SonglistController {

    @Autowired
    private SonglistService songlistService;

    //------------------------- 歌单分类页 -------------------------

    @GetMapping("/api/songlist/queryAllTags.do")
    @ApiOperation(value = "查询所有歌单标签",notes = "查询所有歌单标签")
    public R queryAllTags() {
        return songlistService.queryAllTags();
    }

    @PostMapping("/api/songlist/queryAll.do")
    @ApiOperation(value = "查询所有歌单（播放量从高到低）",notes = "查询所有歌单（播放量从高到低）")
    public R queryAll(@RequestBody Map<String, Object> map) {
        return songlistService.queryAll(map);
    }

    //------------------------- 歌单页 -------------------------
    @GetMapping("/api/songlist/queryOneSonglistWithSongs.do")
    @ApiOperation(value = "查询单个歌单及其歌曲",notes = "查询单个歌单及其歌曲")
    public R queryOneSonglistWithSongs(@RequestParam int songlistid) {
        return songlistService.queryOneSonglistWithSongs(songlistid);
    }

    @GetMapping("/api/songlist/queryOneAlbumWithSongs.do")
    @ApiOperation(value = "查询单个专辑及其歌曲",notes = "查询单个专辑及其歌曲")
    public R queryOneAlbumWithSongs(@RequestParam int songlistid) {
        return songlistService.queryOneAlbumWithSongs(songlistid);
    }

    @GetMapping("/api/songlist/querySongsByKeyword.do")
    @ApiOperation(value = "根据关键字，查询某歌单内歌曲",notes = "根据关键字，查询某歌单内歌曲")
    public R querySongsByKeyword(@RequestParam("songlistid") int songlistid, @RequestParam("keyword") String keyword) {
        return songlistService.querySongsByKeyword(songlistid, keyword);
    }


}
