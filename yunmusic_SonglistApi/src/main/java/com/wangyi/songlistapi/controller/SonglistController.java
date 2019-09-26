package com.wangyi.songlistapi.controller;

import com.wangyi.common.vo.R;
import com.wangyi.songlistapi.service.SonglistService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "歌单/歌手模块", tags = "歌单/歌手模块")
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
    @ApiOperation(value = "查询所有歌单（播放量从高到低）",notes = "查询所有歌单（播放量从高到低），参数：page页码,size每页展示,tag标签(选填)}")
    public R queryAll(@RequestBody Map<String, Object> map) {
        return songlistService.queryAll(map);
    }

    //------------------------- 歌单页 -------------------------

    @GetMapping("/api/songlist/queryOneSonglistWithSongs.do")
    @ApiOperation(value = "查询单个歌单及其歌曲",notes = "查询单个歌单及其歌曲")
    public R queryOneSonglistWithSongs(@RequestParam("songlistid") int songlistid) {
        return songlistService.queryOneSonglistWithSongs(songlistid);
    }

    @GetMapping("/api/songlist/queryOneAlbumWithSongs.do")
    @ApiOperation(value = "查询单个专辑及其歌曲",notes = "查询单个专辑及其歌曲")
    public R queryOneAlbumWithSongs(@RequestParam("songlistid") int songlistid) {
        return songlistService.queryOneAlbumWithSongs(songlistid);
    }

    @GetMapping("/api/songlist/querySongsByKeyword.do")
    @ApiOperation(value = "根据关键字，查询某歌单内歌曲",notes = "根据关键字，查询某歌单内歌曲")
    public R querySongsByKeyword(@RequestParam("songlistid") int songlistid, @RequestParam("keyword") String keyword) {
        return songlistService.querySongsByKeyword(songlistid, keyword);
    }

    //------------------------- 歌曲播放页 -------------------------

    @GetMapping("/server/songlist/queryPlaySongById.do")
    @ApiOperation(value = "查询正在播放歌曲的信息",notes = "查询正在播放歌曲的信息")
    public R querySonglistNameById(@RequestParam("songid") Integer songid,@RequestParam("songlistid") Integer songlistid){
        return songlistService.querySonglistNameById(songid, songlistid);
    }

    @GetMapping("/server/songlist/queryLyric.do")
    @ApiOperation(value = "查询当前播放歌曲的歌词",notes = "查询当前播放歌曲的歌词")
    public R queryLyric(@RequestParam("songid") Integer songid) {
        return songlistService.queryLyric(songid);
    }

    //------------------------- 歌手分类页 -------------------------

    @PostMapping("/server/songlist/queryAllSinger.do")
    @ApiOperation(value = "按条件查询所有歌手，按热度从高到低",notes = "map(page 页码, language 语种, type 歌手分类, firstPinyin 当前页展示数量)")
    public R queryAllSinger(@RequestBody Map<String, Object> map) {
        return songlistService.queryAllSinger(map);
    }

    //------------------------- 歌手页 -------------------------

    @GetMapping("/server/songlist/queryTop50BySingerId.do")
    @ApiOperation(value = "查询某歌手所有歌曲的TOP50",notes = "查询某歌手所有歌曲的TOP50")
    public R queryTop50BySingerId(@RequestParam("singerid") Integer singerid) {
        return songlistService.queryTop50BySingerId(singerid);
    }

    @GetMapping("/server/songlist/queryAllAlbumWithSongBySingerId.do")
    @ApiOperation(value = "查询歌手的所有专辑及专辑对应歌曲",notes = "查询歌手的所有专辑及专辑对应歌曲")
    public R queryAllAlbumWithSong(@RequestParam("singerid") Integer singerid) {
        return songlistService.queryAllAlbumWithSong(singerid);
    }
}

