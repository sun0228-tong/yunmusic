package com.wangyi.search.task;

import com.wangyi.search.spider.DaheNewsPage;
import com.wangyi.search.spider.EsPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class NewsTask {

    @Autowired
    private EsPipeline esPipeline;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void startNews() {
        //启动爬虫
        System.out.println("爬虫启动");
        new Spider(new DaheNewsPage()).addPipeline(esPipeline).
                addUrl("https://news.dahe.cn/yl/").run();
    }
}
