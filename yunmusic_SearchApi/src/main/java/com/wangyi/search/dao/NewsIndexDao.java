package com.wangyi.search.dao;

import com.wangyi.search.model.NewsIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsIndexDao extends ElasticsearchRepository<NewsIndex,String> {

}
