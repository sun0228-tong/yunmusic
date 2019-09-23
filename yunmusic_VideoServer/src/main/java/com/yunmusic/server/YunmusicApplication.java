package com.yunmusic.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.yunmusic.server.dao")
@EnableDiscoveryClient //注册服务
public class YunmusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunmusicApplication.class,args);
    }
}
