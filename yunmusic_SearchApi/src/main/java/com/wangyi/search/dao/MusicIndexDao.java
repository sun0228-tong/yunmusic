package com.wangyi.search.dao;

import com.wangyi.search.model.MusicIndex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MusicIndexDao extends ElasticsearchRepository<MusicIndex,Integer> {
}
