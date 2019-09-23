package com.wangyi.rankprovider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;

import com.wangyi.rankprovider.cache.JedisUtil;
import com.wangyi.rankprovider.config.RedisKeyConfig;
import com.wangyi.rankprovider.dao.RankHotDao;
import com.wangyi.rankprovider.service.RankHotService;
import com.wangyi.rankprovider.vo.VRankInfo;
import com.wangyi.rankprovider.vo.VRankSongInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yunmusic
 * @ClassName: RankHotServiceImpl
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-20 19:44
 **/
@Service

public class RankHotServiceImpl extends ServiceImpl<RankHotDao, VRankInfo> implements RankHotService {

    @Autowired(required = false)
    private RankHotDao rankHotDao;
    private JedisUtil jedisUtil =JedisUtil.getInstance();
    @Override
    public R<List<VRankInfo>> selectHotList() {
        Map<String,String> map = jedisUtil.hgetall(RedisKeyConfig.HOTTOP);
        if (map ==null ||map.size() ==0){
            List<VRankInfo> hotList = rankHotDao.selectHotList();
            map = new LinkedHashMap<>();
            for (int i = 0; i <hotList.size() ; i++) {
                map.put(i+1+"", JSON.toJSONString(hotList.get(i)));
            }
            jedisUtil.hmset(RedisKeyConfig.HOTTOP,map);
            return RUtil.setOK("OK",hotList);
        }else {
            List<VRankInfo> hotList= new ArrayList<>();
            for (String k : map.keySet()) {
                String hs =map.get(k);
                hotList.add(JSON.parseObject(hs, VRankInfo.class));
             }
            return RUtil.setOK("OK",hotList);
        }

    }

    @Override
    public R<List<VRankSongInfo>> selectByPlayNum() {
        List<VRankSongInfo> sList = rankHotDao.selectByPlayNum();
        return RUtil.setOK("OK",sList);
    }

    @Override
    public int deleteById(int songlistid) {
        return rankHotDao.deleteById(songlistid);
    }

    @Override
    public int insertSongBatch(int songlistid, List<Integer> songids) {
        return rankHotDao.insertSongBatch(songlistid,songids);
    }

}
