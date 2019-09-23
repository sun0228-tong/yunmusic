package com.wangyi.songlistprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.wangyi.songlistprovider.dao")
@EnableDiscoveryClient
public class SonglistServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SonglistServerApplication.class, args);
    }
}
