package com.wangyi.resourceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication //开关类
@EnableSwagger2 //启用Swagger
@EnableDiscoveryClient //发现服务
@EnableFeignClients //启用Feign实现服务消费
public class  ResourceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApiApplication.class,args);
    }
}
