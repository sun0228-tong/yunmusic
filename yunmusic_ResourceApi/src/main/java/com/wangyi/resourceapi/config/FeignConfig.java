package com.wangyi.resourceapi.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private int connectTimeout=10000;//10秒
    private int readTimeout=10000;//10秒

    @Bean
    public Request.Options createOp() {
        return new Request.Options(connectTimeout,readTimeout);
    }

}
