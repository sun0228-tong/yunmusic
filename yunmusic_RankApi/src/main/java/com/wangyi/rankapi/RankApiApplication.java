package com.wangyi.rankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: yunmusic
 * @ClassName: RankApiApplication
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-24 11:49
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class RankApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RankApiApplication.class,args);
    }
}
