package com.wangyi.usercollectionprovider.controller;

import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.entity.Songlist;
import com.wangyi.entity.Usersonglist;
import com.wangyi.resourceprovider.service.ResourceProviderService;
import com.wangyi.usercollectionprovider.service.UserCollectionService;
import com.wangyi.usercollectionprovider.service.UserSongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class UserCollectionController {

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired(required = false)
    private UserSongListService userSongListService;

    @Autowired(required = false)
    private ResourceProviderService resourceProviderService;

    // 用户点击收藏歌单,新增用户歌单中间表
    @PostMapping("/provider/usercollection/saveusersonglist.do")
    public R save(@RequestBody Usersonglist usersonglist) {
        return RUtil.setR(userCollectionService.save(usersonglist),"新增用户歌单中间表");
    }

    // 用户删除歌单
    @DeleteMapping("/provider/usercollection/delusersonglist.do")
    public R del(@RequestParam Integer id) {
        return RUtil.setR(userCollectionService.removeById(id),"删除成功");
    }

    // 用户编辑已创建歌单的信息
    @RequestMapping(value = "/provider/usercollection/createSongList.do",method = {RequestMethod.POST})
    public R createSongList(@RequestBody Songlist songlist,@RequestParam MultipartFile file) throws IOException {
//        Songlist songlist = (Songlist) map.get("songlist");
//        MultipartFile file = (MultipartFile) map.get("file");
        System.out.println(songlist);
        System.out.println(file);

        if (file != null) {
            R<String> r = resourceProviderService.uploadResource(file);
            String imgUrl = r.getData();
            songlist.setImgurl(imgUrl);
        }
        userSongListService.saveOrUpdate(songlist);
        System.out.println(songlist.getId());
        return RUtil.setR(true ,"编辑成功");
    }


}
