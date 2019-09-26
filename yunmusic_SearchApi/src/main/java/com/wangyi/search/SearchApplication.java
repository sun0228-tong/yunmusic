package com.wangyi.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //启用定时任务
@EnableScheduling
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}
