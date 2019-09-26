package com.wangyi.rankprovider.controller;

import com.wangyi.common.vo.R;
import com.wangyi.rankprovider.service.RankHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: RankHotController
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-20 20:09
 **/
@RestController
public class RankHotController {
    @Autowired
    private RankHotService rankHotService;

    /**
     * 云音乐热歌榜
     *
     * @return
     */
    @GetMapping("/rankprovider/rank/rankhot.do")
    public R rankHot() {
        return rankHotService.selectHotList();
    }

    /**
     * 最热歌曲(不属于榜单)
     *
     * @return
     */
    @GetMapping("/rankprovider/rank/songList.do")
    public R songHot() {
        return rankHotService.selectByPlayNum();
    }

    /**
     * 根据歌单id删除歌单歌曲
     * @param songlistid
     */
    @DeleteMapping("/rankprovider/rank/deleteByID.do")
    public void deleteBySongListId(int songlistid) {
        rankHotService.deleteById(songlistid);
    }

    /**
     * 根据歌单id查询歌单歌曲
     * @param songlistid
     * @return
     */
    @GetMapping("/rankprovider/rank/queryhotsongs.do")
    public R hotsongs(int songlistid){
        return rankHotService.selectHotSongs(songlistid);
    }

    /**
     * 根据歌单id批量插入歌曲
     * @param songlistid
     * @param songids
     */
    @PostMapping("/rankprovider/rank/addsongs.do")
    public void addBatch(int songlistid, int[] songids) {
        List<Integer> list = new ArrayList<>();
        for (int i : songids) {
            list.add(i);
        }
        rankHotService.insertSongBatch(songlistid, list);
    }
}
