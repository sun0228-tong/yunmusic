package com.wangyi.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.wangyi.user.dao")
@EnableDiscoveryClient
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class,args);
    }
}
