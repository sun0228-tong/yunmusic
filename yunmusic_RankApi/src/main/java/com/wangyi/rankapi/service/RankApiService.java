package com.wangyi.rankapi.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @program: yunmusic
 * @ClassName: RankApiService
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-24 11:55
 **/
@FeignClient("RankProvider")
public interface RankApiService {

}
