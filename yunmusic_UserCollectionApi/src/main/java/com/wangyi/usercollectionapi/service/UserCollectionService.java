package com.wangyi.usercollectionapi.service;

import com.wangyi.common.vo.R;
import com.wangyi.entity.Songlist;
import com.wangyi.entity.Usersinger;
import com.wangyi.entity.Usersonglist;
import com.wangyi.entity.Uservideo;
import com.wangyi.usercollectionapi.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(value = "UserCollectionProvider",configuration = FeignMultipartSupportConfig.class)
public interface UserCollectionService {

    // 用户点击收藏歌单,新增用户歌单中间表
    @PostMapping("/provider/usercollection/saveusersonglist.do")
    R save(@RequestBody Usersonglist usersonglist);

    // 用户删除歌单
    @DeleteMapping("/provider/usercollection/delusersonglist.do")
    R del(@RequestParam Integer id);

    // 用户编辑已创建歌单的信息
    @RequestMapping(value = "/provider/usercollection/createSongList.do",method = {RequestMethod.POST})
    R createSongList(@RequestBody Map<String,Object> map);

    @GetMapping("/provider/usercollection/findSonglistById.do")
    R findSonglistById(@RequestParam int id);

    @GetMapping("/provider/usercollection/findSongListCollectorsById.do")
    R findSongListCollectorsById(@RequestParam("page") int page,@RequestParam("size") int size, @RequestParam("id") int id);

    @PostMapping("/provider/usercollection/userSaveSinger.do")
    R userSaveSinger(@RequestBody Usersinger usersinger);

    @PostMapping("/provider/usercollection/userSaveVideo.do")
    R userSaveVideo(@RequestBody Uservideo uservideo);

}
