package com.wangyi.rankprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: yunmusic
 * @ClassName: RankApplication
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-20 16:02
 **/
@SpringBootApplication //开关类
@MapperScan("com.wangyi.rankprovider.dao") //扫描dao层接口
@EnableDiscoveryClient //注册服务
@EnableScheduling //启用定时任务
public class RankApplication {
    public static void main(String[] args) {
        SpringApplication.run(RankApplication.class,args);
    }
}
