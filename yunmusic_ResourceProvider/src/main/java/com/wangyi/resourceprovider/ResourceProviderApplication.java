package com.wangyi.resourceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResourceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceProviderApplication.class,args);
    }
}
