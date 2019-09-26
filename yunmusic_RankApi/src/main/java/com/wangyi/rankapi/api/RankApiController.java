package com.wangyi.rankapi.api;

import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.rankapi.service.RankApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: RankApiController
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-24 11:54
 **/
@Api(value = "热歌榜操作",tags = "热歌榜操作")
@RestController
public class RankApiController {
    @Autowired
    private RankApiService rankApiService;


    @ApiOperation(value = "查询云音乐热歌榜",notes = "查询云音乐热歌榜")
    @GetMapping("/rankapi/rank/rankhot.do")
    public R rankHot() {
        return rankApiService.rankHot();
    }


    @ApiOperation(value = "查询云音乐最热歌曲(不属于榜单)",notes = "查询云音乐最热歌曲(不属于榜单)")
    @GetMapping("/rankapi/rank/songList.do")
    public R songHot() {
        return rankApiService.songHot();
    }


    @ApiOperation(value = "根据歌单id删除歌单歌曲",notes = "根据歌单id删除歌单歌曲")
    @DeleteMapping("/rankapi/rank/deleteByID.do")
    public void deleteBySongListId(@RequestParam(value = "songlistid")int songlistid) {
        rankApiService.deleteBySongListId(songlistid);
    }


    @ApiOperation(value = "根据歌单id查询歌单歌曲",notes = "根据歌单id查询歌单歌曲")
    @GetMapping("/rankapi/rank/queryhotsongs.do")
    public R hotsongs(@RequestParam(value = "songlistid")int songlistid){
        return rankApiService.hotsongs(songlistid);
    }

    @ApiOperation(value = "根据歌单id批量插入歌曲",notes = "根据歌单id批量插入歌曲")
    @PostMapping("/rankapi/rank/addsongs.do")
    public void addBatch(@RequestParam(value = "songlistid")int songlistid,@RequestParam(value = "songids") int[] songids){

            List<Integer> list= new ArrayList<>();
            for(int i:songids){
                list.add(i);
            }
        rankApiService.addBatch(songlistid, list);
    }
}


