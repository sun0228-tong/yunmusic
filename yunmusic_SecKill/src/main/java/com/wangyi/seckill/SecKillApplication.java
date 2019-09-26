package com.wangyi.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangyi.seckill.dao")
public class SecKillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecKillApplication.class,args);
    }
}
