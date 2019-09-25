package com.wangyi.search.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @program: yunmusic
 * @description:
 * @author: suntong
 * @create: 2019-09-25 17:21
 */
@Document(indexName = "yunmusic",type = "news")
@Data
public class NewsIndex {
    private String id;
    private String title;
    private String time;
    private String url;
}
