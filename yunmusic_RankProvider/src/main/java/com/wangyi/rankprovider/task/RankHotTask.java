package com.wangyi.rankprovider.task;

import com.alibaba.fastjson.JSON;
import com.wangyi.entity.Songlist;
import com.wangyi.rankprovider.cache.JedisUtil;
import com.wangyi.rankprovider.config.RedisKeyConfig;
import com.wangyi.rankprovider.dao.RankHotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private JedisUtil jedisUtil =JedisUtil.getInstance();
    @Scheduled(cron = " 0 0 2 * * ?")

    public void  hotTop(){
        //热歌榜更新
        //查询数据库----redis
        List<Songlist> list = rankHotDao.selectHotList();
        Map<String,String> map = new LinkedHashMap<>();
        for (int i = 0; i <list.size() ; i++) {
            map.put(i+i+"", JSON.toJSONString(list.get(i)));
        }
        jedisUtil.hmset(RedisKeyConfig.HOTTOP,map);
    }
}
