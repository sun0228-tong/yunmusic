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

 // @Scheduled(cron = " 0 0/1 * * * ?")

    public void hotTop() {
        //热歌榜更新
        //查询数据库----redis
        int songlistid = 0;
        List<Integer> songids = new ArrayList<>();
        List<VRankInfo> list = rankHotDao.selectHotList();
        //得到热歌榜歌单id
        songlistid = list.get(0).getSonglistid();
        //查询播放热度最高的歌曲（不属于榜单）
        List<VRankSongInfo> sList = rankHotDao.selectByPlayNum();
        //得到歌曲id的集合
        for (int i = 0; i <sList.size() ; i++) {
           songids.add(i,sList.get(i).getSongid());
        }
        //从songlistsong表中删除已存的歌曲
        rankHotDao.deleteById(songlistid);
        //批量插入热歌到热歌榜
        rankHotDao.insertSongBatch(songlistid, songids);
        //更新排行榜更新时间
        rankHotDao.updateByTime(songlistid);
        //获取新的热歌榜排行
       List<VRankInfo> list1 = rankHotDao.selectHotList();
       Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i + i + "", JSON.toJSONString(list1.get(i)));
        }
        jedisUtil.hmset(RedisKeyConfig.HOTTOP, map);
    }
}
