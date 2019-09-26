package com.wangyi.usercollectionapi.controller;

import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.entity.Songlist;
import com.wangyi.entity.Usersinger;
import com.wangyi.entity.Usersonglist;
import com.wangyi.entity.Uservideo;
import com.wangyi.usercollectionapi.service.UserCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "用户收藏操作",tags = "用户收藏操作")
public class UserCollectionController {

    @Autowired
    private UserCollectionService userCollectionService;

    @PostMapping("/api/usercollection/saveuserlist.do")
    @ApiOperation(value = "用户收藏歌单",notes = "用户收藏歌单")
    public R save(@RequestBody Usersonglist usersonglist) {
        return userCollectionService.save(usersonglist);
    }

    @DeleteMapping("/api/usercollection/delusersonglist.do")
    @ApiOperation(value = "用户删除歌单",notes = "用户删除歌单")
    public R del(@RequestParam Integer id) {
        return userCollectionService.del(id);
    }

    @RequestMapping(value = "/api/usercollection/createsonglist.do",method = {RequestMethod.POST},consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "用户创建或编辑歌单",notes = "用户创建或编辑歌单")
    public R createSongList(Songlist songlist, @RequestParam MultipartFile file) {
        Map<String,Object> map = new HashMap<>();
        map.put("file",file);
        map.put("songlist",songlist);
        return userCollectionService.createSongList(map);
    }

    @GetMapping("/api/usercollection/findSonglistById.do")
    @ApiOperation(value = "根据 ID查询歌单信息",notes = "根据 ID查询歌单信息")
    public R findSonglistById(@RequestParam int id) {
        return userCollectionService.findSonglistById(id);
    }

    @GetMapping("/api/usercollection/findSongListCollectorsById.do")
    @ApiOperation(value = "根据歌单 ID查询用户自己歌单的收藏者信息",notes = "根据歌单 ID查询用户自己歌单的收藏者信息")
    public R findSongListCollectorsById(@RequestParam("page") int page,@RequestParam("size") int size, @RequestParam("id") int id) {
        return userCollectionService.findSongListCollectorsById(page,size,id);
    }

    @PostMapping("/api/usercollection/userSaveSinger.do")
    @ApiOperation(value = "用户收藏歌手",notes = "用户收藏歌手")
    public R userSaveSinger(@RequestBody Usersinger usersinger) {
        return userCollectionService.userSaveSinger(usersinger);
    }

    @PostMapping("/api/usercollection/userSaveVideo.do")
    @ApiOperation(value = "用户收藏视频",notes = "用户收藏视频")
    public R userSaveVideo(@RequestBody Uservideo uservideo) {
        return userCollectionService.userSaveVideo(uservideo);
    }

}
