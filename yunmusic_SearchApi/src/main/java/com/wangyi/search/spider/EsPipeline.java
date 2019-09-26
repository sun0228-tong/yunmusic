package com.wangyi.search.spider;


import com.wangyi.search.dao.NewsIndexDao;
import com.wangyi.search.model.NewsIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class EsPipeline implements Pipeline {

    @Autowired
    private NewsIndexDao newsIndexDao;

    @Override
    public void process(ResultItems resultItems, Task task) {

        List<NewsIndex> list=resultItems.get("news");
        System.out.println("处理器："+list.size());
        if(list!=null && list.size()>0) {
            newsIndexDao.saveAll(list);
        }
    }
}
