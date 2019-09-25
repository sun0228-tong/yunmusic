package com.wangyi.search.api;

import com.alibaba.fastjson.JSON;
import com.wangyi.common.config.RedisKeyConfig;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.search.model.MusicIndex;
import com.wangyi.search.service.MusicIndexService;
import com.wangyi.search.util.RedissonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value ="搜索音乐相关内容索引" ,tags = "搜索音乐相关内容索引")
public class MusicSearchController {

    @Autowired
    private MusicIndexService musicIndexService;

    //全部查询  分页
    @GetMapping("/api/search/queryAll.do")
    @ApiOperation(value = "实现整个音乐内容查询",notes = "实现整个音乐内容查询 分页")
    public R queryAll(int page, int size) {
        return musicIndexService.findAll(page, size);
    }

    //模糊查询  分页
    @GetMapping("/api/search/queryLike.do")
    @ApiOperation(value = "实现整个音乐内容的模糊查询",notes = "实现整个音乐内容的模糊查询 分页")
    public R queryLike(int page, int size, String msg){
        return musicIndexService.findLike(page, size, msg);
    }

    @PostMapping("/api/search/save.do")
    @ApiOperation(value = "实现音乐添加进 ES(实际为测试redis)中",notes = "实现音乐添加进 ES(实际为测试)中")
    public R save(@RequestBody MusicIndex musicIndex) {

        // 模拟向redis中添加 (mysql数据库添加)信息
        List<String> list = new ArrayList<>();
        list.add(JSON.toJSONString(musicIndex));
        RedissonUtil.saveList(RedisKeyConfig.ES_ADDKEY, list);
        return RUtil.setOK();
        //return RUtil.setOK("OK",musicIndexService.save(musicIndex));
    }
}
