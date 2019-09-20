package com.wangyi.rankprovider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyi.common.vo.R;
import com.wangyi.entity.Songlist;

import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: RankHotService
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-20 19:42
 **/
public interface RankHotService extends IService<Songlist> {
    R<List<Songlist>> selectHotList();
}
