package com.wangyi.rankapi.service;

import com.wangyi.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: RankApiService
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-24 11:55
 **/
@FeignClient("RankProvider")
public interface RankApiService {

    @GetMapping("/rankprovider/rank/rankhot.do")
    public R rankHot();

    /**
     * 最热歌曲(不属于榜单)
     *
     * @return
     */
    @GetMapping("/rankprovider/rank/songList.do")
    public R songHot();

    /**
     * 根据歌单id删除歌单歌曲
     * @param songlistid
     */
    @DeleteMapping("/rankprovider/rank/deleteByID.do")
    public void deleteBySongListId(@RequestParam(value = "songlistid") int songlistid);

    /**
     * 根据歌单id查询歌单歌曲
     * @param songlistid
     * @return
     */
    @GetMapping("/rankprovider/rank/queryhotsongs.do")
    public R hotsongs(@RequestParam(value = "songlistid")int songlistid);

    /**
     * 根据歌单id批量插入歌曲
     * @param songlistid
     * @param songids
     */
    @PostMapping("/rankprovider/rank/addsongs.do")
    public void addBatch(@RequestParam(value = "songlistid")int songlistid,@RequestParam(value = "songids") List<Integer> songids);


}
