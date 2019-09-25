package com.wangyi.search.msg;

import com.alibaba.fastjson.JSON;
import com.wangyi.common.config.RedisKeyConfig;
import com.wangyi.search.dao.MusicIndexDao;
import com.wangyi.search.model.MusicIndex;
import com.wangyi.search.util.RedissonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: yunmusic
 * @description: 监听RabbitMQ 的队列信息的变化
 * @author: suntong
 */

@Component
public class EsMusicMsg {

    @Autowired
    private MusicIndexDao musicIndexDao;

    @RabbitListener(queues = "esyunmusic")
    public void receiveMsg(String msg) {
        System.out.println("收到消息" + msg);
        //从 Redis中获取内容  三个key(add, del, update)
        //获取数据(adds)
        List<String> adds = RedissonUtil.getList(RedisKeyConfig.ES_ADDKEY);

        if (adds != null && adds.size() > 0) {
            //有数据 新增到ES服务器
            //获取新增的数据源 转换为对象
            List<MusicIndex> musicIndices = new ArrayList<>();
            for(String s : adds) {
                musicIndices.add(JSON.parseObject(s,MusicIndex.class));
            }
            //将数据添加到ES服务器中
            musicIndexDao.saveAll(musicIndices);
            //清空List集合的数据
            RedissonUtil.delList(RedisKeyConfig.ES_ADDKEY);
        }

        //获取数据(dels)
        List<String> dels = RedissonUtil.getList(RedisKeyConfig.ES_DELKEY);
        if (dels != null && dels.size() > 0) {
            //有删除
            //删除ES中的数据
            for (String s : dels) {
                musicIndexDao.deleteById(Integer.parseInt(s));
            }
            //清空Redis 中的数据
            RedissonUtil.delList(RedisKeyConfig.ES_DELKEY);
        }

        //获取数据(updates)
        List<String> updates = RedissonUtil.getList(RedisKeyConfig.ES_UPDATEKEY);
        if (updates != null && updates.size() > 0) {
            List<MusicIndex> musicIndices = new ArrayList<>();
            for (String s : updates) {
                musicIndices.add(JSON.parseObject(s,MusicIndex.class));
            }
            musicIndexDao.saveAll(musicIndices);
            //清空Redis 中的数据
            RedissonUtil.delList(RedisKeyConfig.ES_UPDATEKEY);
        }
    }
}
