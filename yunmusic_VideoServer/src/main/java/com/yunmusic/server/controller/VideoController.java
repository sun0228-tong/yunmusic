package com.yunmusic.server.controller;

import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.yunmusic.server.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping ("/server/video/findVideo.do")
    public R detail(@RequestParam("tag")String tag){
        return RUtil.setOK("按照tag展示视频",videoService.selectByTag(tag));
    }
    @PostMapping ("/server/video/findAllTag.do")
    public R findAllTag(){
        return RUtil.setOK("展示视频的所有标签",videoService.selectAllTag());
    }

    @PostMapping ("/server/video/findAllType.do")
    public R findAllType(){
        return RUtil.setOK("展示视频的所有类型",videoService.selectAllType());
    }

    @PostMapping ("/server/video/findByType.do")
    public R findByType(@RequestParam("type")String type){
        return RUtil.setOK("按照type展示视频",videoService.selectByType(type));
    }

    @PostMapping ("/server/video/selectMVByTag.do")
    public R selectMVByTag(@RequestParam("tag")String tag){
        return RUtil.setOK("从MV中根据tag查找视频",videoService.selectMVByTag(tag));
    }

    @PostMapping("/server/video/selectByPlaynum.do")
    public R selectByPlaynum(){
        return RUtil.setOK("MV视频排行榜",videoService.selectByPlaynum());
    }

    @PostMapping("/server/video/selectMVByPlaynumAndTag.do")
    public R selectMVByPlaynumAndTag(@RequestParam("tag")String tag){
        return RUtil.setOK("MV分类排行榜",videoService.selectMVByPlaynumAndTag(tag));
    }

    @PostMapping("/server/video/selectOneVideo.do")
    public R selectOneVideo(@RequestParam("id")int id){
        return RUtil.setOK("单个MV详情",videoService.selectOneVideo(id));
    }

    @PostMapping("/server/video/selectNewVideo.do")
    public R selectNewVideo(){
        return RUtil.setOK("单个MV详情",videoService.selectNewVideo());
    }
    @PostMapping("/server/video/selectNewVideoByTag.do")
    public R selectNewVideoByTag(@RequestParam("tag") String tag){
        return RUtil.setOK("按照tag查找最新MV",videoService.selectNewVideoByTag(tag));
    }
}
