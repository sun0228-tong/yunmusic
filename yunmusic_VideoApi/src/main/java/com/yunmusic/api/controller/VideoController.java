package com.yunmusic.api.controller;

import com.wangyi.common.vo.R;
import com.wangyi.entity.Comment;
import com.wangyi.entity.Recomment;
import com.yunmusic.api.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "视频操作",tags = "视频操作")
@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "通过标签查询视频信息",notes = "通过标签查询视频信息")
    @PostMapping("/server/video/findVideo.do")
    public R detail(@RequestParam(value = "tag" ,required = false) String tag){
        return videoService.detail(tag);
    }

    @ApiOperation(value = "展示视频的所有标签",notes = "展示视频的所有标签")
    @PostMapping ("/server/video/findAllTag.do")
    public R findAllTag(){
        return videoService.findAllTag();
    }

    @ApiOperation(value = "展示视频的所有类型",notes = "展示视频的所有类型")
    @PostMapping ("/server/video/findAllType.do")
    public R findAllType(){
        return videoService.findAllType();
    }

    @ApiOperation(value = "通过视频类型查找视频",notes = "通过视频类型查找视频")
    @PostMapping ("/server/video/findByType.do")
    public R findByType(@RequestParam(value = "type",required = false) String type){
        return videoService.findByType(type);
    }

    @ApiOperation(value = "根据tag从MV中查找视频",notes = "根据tag从MV中查找视频")
    @PostMapping("/server/video/selectMVByTag.do")
    public R selectMVByTag(@RequestParam(value = "tag" ,required = false) String tag){
        return videoService.selectMVByTag(tag);
    }

    @ApiOperation(value = "MV总排行榜",notes = "MV总排行榜")
    @PostMapping("/server/video/selectByPlaynum.do")
    public R selectByPlaynum(){
        return videoService.selectByPlaynum();
    }

    @ApiOperation(value ="MV分类排行")
    @PostMapping("/server/video/selectMVByPlaynumAndTag.do")
    public R selectMVByPlaynumAndTag(@RequestParam(value = "tag" ,required = false) String tag){
        return videoService.selectMVByPlaynumAndTag(tag);
    }

    @ApiOperation(value = "单个视频详情",notes = "单个视频详情")
    @PostMapping("/server/video/selectOneVideo.do")
    public R selectOneVideo(@RequestParam(value = "id" ,required = false) int id){
        return videoService.selectOneVideo(id);
    }

    @ApiOperation(value = "通过评论id查看该评论所有的回复",notes = "通过评论id查看该评论所有的回复")
    @PostMapping("/server/comment/findRecommentById.do")
    public R findRecommentById(@RequestParam(value = "id") int id){
        return videoService.findRecommentById(id);
    }

    @ApiOperation(value = "添加评论",notes = "添加评论")
    @PostMapping("/server/comment/addComment.do")
    public R addComment(@RequestBody Comment comment){
        return videoService.addComment(comment);
    }

    @ApiOperation(value = "回复评论",notes = "回复评论")
    @PostMapping("/server/comment/addRecomment.do")
    public R addRecomment(@RequestBody Recomment recomment){
        return videoService.addRecomment(recomment);
    }



    @ApiOperation(value = "展示最新MV",notes = "展示最新MV")
    @PostMapping("/server/comment/selectNewVideo.do")
    public R selectNewVideo(){
        return videoService.selectNewVideo();
    }
    @ApiOperation(value = "根据分类查找最新MV",notes = "根据分类查找最新MV")
    @PostMapping("/server/video/selectNewVideoByTag.do")
    public R selectNewVideoByTag(@RequestParam(value = "tag" ,required = false) String tag){
        return videoService.selectNewVideoByTag(tag);
    }

}
