package com.wangyi.rankprovider.task;

import com.alibaba.fastjson.JSON;

import com.wangyi.rankprovider.cache.JedisUtil;
import com.wangyi.rankprovider.config.RedisKeyConfig;
import com.wangyi.rankprovider.dao.RankHotDao;
import com.wangyi.rankprovider.vo.VRankInfo;
import com.wangyi.rankprovider.vo.VRankSongInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yunmusic
 * @ClassName: RankHotTask
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-20 20:22
 **/
@Component
public class RankHotTask {
    @Autowired(required = false)
    private RankHotDao rankHotDao;
    private JedisUtil jedisUtil = JedisUtil.getInstance();

 //  @Scheduled(cron = " 0 0/1 * * * ?")

    public void hotTop() {
        //热歌榜更新
        //查询数据库----redis
        int songlistid = 0;

        List<Integer> songids = new ArrayList<>();
        List<VRankInfo> list = rankHotDao.selectHotList();
        songlistid = list.get(0).getSonglistid();
        List<VRankSongInfo> sList = rankHotDao.selectByPlayNum();
        for (int i = 0; i <sList.size() ; i++) {
           songids.add(i,sList.get(i).getSongid());
        }

        rankHotDao.deleteById(songlistid);
        rankHotDao.insertSongBatch(songlistid, songids);
       List<VRankInfo> list1 = rankHotDao.selectHotList();
       Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i + i + "", JSON.toJSONString(list1.get(i)));
        }
        jedisUtil.hmset(RedisKeyConfig.HOTTOP, map);
    }
}
