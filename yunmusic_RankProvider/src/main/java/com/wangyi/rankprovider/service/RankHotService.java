package com.wangyi.rankprovider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyi.common.vo.R;

import com.wangyi.rankprovider.vo.VRankInfo;
import com.wangyi.rankprovider.vo.VRankSongInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: RankHotService
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-20 19:42
 **/
public interface RankHotService extends IService<VRankInfo> {

    R<List<VRankInfo>> selectHotList();

    R<List<VRankSongInfo>> selectByPlayNum();

    int deleteById(int songlistid);

    int insertSongBatch(@Param("songlistid") int songlistid, @Param("songids")List<Integer> songids);
}
