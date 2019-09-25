package com.wangyi.usercollectionprovider.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.entity.*;
import com.wangyi.resourceprovider.service.ResourceProviderService;
import com.wangyi.usercollectionprovider.dao.UserCollectionDao;
import com.wangyi.usercollectionprovider.service.UserCollectionService;
import com.wangyi.usercollectionprovider.service.UserSingerService;
import com.wangyi.usercollectionprovider.service.UserSongListService;
import com.wangyi.usercollectionprovider.service.UserVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserCollectionController {

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired(required = false)
    private UserSongListService userSongListService;

    @Autowired(required = false)
    private ResourceProviderService resourceProviderService;

    @Autowired(required = false)
    private UserCollectionDao userCollectionDao;

    @Autowired(required = false)
    private UserSingerService userSingerService;

    @Autowired(required = false)
    private UserVideoService userVideoService;

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

    // 用户添加编辑已创建歌单的信息(未实现 shit)
    @RequestMapping(value = "/provider/usercollection/createSongList.do",method = {RequestMethod.POST})
    public R createSongList(@RequestBody Map<String, Object> map) throws IOException {
        MultipartFile file = (MultipartFile) map.get("file");
        Songlist songlist = (Songlist) map.get("songlist");
        if (file != null) {
            R<String> r = resourceProviderService.uploadResource(file);
            String imgUrl = r.getData();
            songlist.setImgurl(imgUrl);
        }
        userSongListService.saveOrUpdate(songlist);
        // 判断是否为新增歌单
        if (songlist.getId() == null) {
            Usersonglist usersonglist = new Usersonglist();
            usersonglist.setUserid(1);
            usersonglist.setSonglistid(songlist.getId());
            userCollectionService.save(usersonglist);
        }
        return RUtil.setR(userSongListService.saveOrUpdate(songlist),"编辑歌单");
    }

    // 查询歌单信息
    @GetMapping("/provider/usercollection/findSonglistById.do")
    public R findSonglistById(@RequestParam int id) {
        Songlist songlist = userSongListService.getById(id);
        R r;
        if (songlist != null) {
            r = RUtil.setOK("OK",songlist);
        } else {
            r = RUtil.setERROR("未找到相关歌单信息");
        }
        return r;
    }

    // 分页查看歌单收藏者
    @GetMapping("/provider/usercollection/findSongListCollectorsById.do")
    public R findSongListCollectorsById(@RequestParam("page") int page,@RequestParam("size") int size, @RequestParam("id") int id) {
        List<User> list = userCollectionDao.pageList(size,page,id);
        int count = userCollectionDao.pageListCount(id);
        // result 结果集
        Map<String, Object> result = new HashMap<>();
        result.put("pageList",list);
        result.put("totalCount",count);

        return RUtil.setOK("OK",result);
    }

    // 用户点击收藏歌手,新增用户歌手中间表
    @PostMapping("/provider/usercollection/userSaveSinger.do")
    public R userSaveSinger(@RequestBody Usersinger usersinger) {
        return RUtil.setR(userSingerService.save(usersinger),"收藏歌手");
    }

    // 用户点击收藏视频,新增用户视频中间表
    @PostMapping("/provider/usercollection/userSaveVideo.do")
    public R userSaveVideo(@RequestBody Uservideo uservideo) {
        return RUtil.setR(userVideoService.save(uservideo),"收藏视频");
    }


}
