package com.wangyi.search.task;

import com.wangyi.search.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MusicTask {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //间隔2小时
    @Scheduled(cron = "0 0/1 * * * ?")
    public void execMsg() {
        //基于RabbitMQ发送消息
        amqpTemplate.convertAndSend(RabbitMQConfig.qName,"同步ES开始：" + System.currentTimeMillis()/1000);
    }
}
