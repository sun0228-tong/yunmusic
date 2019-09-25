package com.wangyi.songlistapi.service;

import com.wangyi.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("SonglistProviderr")
public interface SonglistService {

    //------------------------- 歌单分类页 -------------------------

    @GetMapping("/server/songlist/queryAllTags.do")
    public R queryAllTags();

    @PostMapping("/server/songlist/queryAll.do")
    public R queryAll(@RequestBody Map<String, Object> map);

    //------------------------- 歌单页 -------------------------

    @GetMapping("/server/songlist/queryOneSonglistWithSongs.do")
    public R queryOneSonglistWithSongs(@RequestParam int songlistid);

    @GetMapping("/server/songlist/queryOneAlbumWithSongs.do")
    public R queryOneAlbumWithSongs(@RequestParam int songlistid);

    @GetMapping("/server/songlist/querySongsByKeyword.do")
    public R querySongsByKeyword(@RequestParam("songlistid") int songlistid, @RequestParam("keyword") String keyword);

}
