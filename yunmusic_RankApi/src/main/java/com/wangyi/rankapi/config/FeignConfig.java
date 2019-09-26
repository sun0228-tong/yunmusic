package com.wangyi.rankapi.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: yunmusic
 * @description:
 * @author: ccq
 * @create: 2019-09-10 16:12
 */
@Configuration
public class FeignConfig {
    private int connecttimeout=10000;//10秒
    private int readtimeout=10000;//10秒
    @Bean
    public Request.Options createOp(){
        return new Request.Options(connecttimeout,readtimeout);
    }
}
