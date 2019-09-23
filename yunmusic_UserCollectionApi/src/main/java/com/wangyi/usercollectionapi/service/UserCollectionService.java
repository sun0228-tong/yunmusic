package com.wangyi.usercollectionapi.service;

import com.wangyi.common.vo.R;
import com.wangyi.entity.Songlist;
import com.wangyi.entity.Usersonglist;
import com.wangyi.usercollectionapi.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
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
    R createSongList(@RequestBody Songlist songlist, @RequestParam MultipartFile file);
}
