package com.wangyi.msg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *@Author suntong
 *@Date Created in 2019/7/8 16:08
 */
@Configuration
public class SwaggerConfig {

    private ApiInfo createAI(){
        return new ApiInfoBuilder().title("网易云音乐平台数据接口").
                description("基于SpringCloud实现的网易云音乐微服务项目，该产品是一款很受欢迎的音乐软件！")
                .version("0.1")
                .contact(new Contact("Feri","http://111","xingfei@163.com")).build();
    }
    @Bean
    public Docket createD(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI()).select().apis
                (RequestHandlerSelectors.basePackage("com.wangyi.msg.api")).build();
    }
}
