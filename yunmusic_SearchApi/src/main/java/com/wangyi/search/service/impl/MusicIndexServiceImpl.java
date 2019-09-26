package com.wangyi.search.service.impl;

import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.search.dao.MusicIndexDao;
import com.wangyi.search.model.MusicIndex;
import com.wangyi.search.service.MusicIndexService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicIndexServiceImpl implements MusicIndexService {

    @Autowired
    private MusicIndexDao musicIndexDao;


    @Override
    public R<List<MusicIndex>> findAll(int page, int size) {
        // 创建分页对象
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").ascending());
        return RUtil.setOK("OK",musicIndexDao.findAll(pageable).iterator());
    }

    @Override
    public R<List<MusicIndex>> findLike(int page, int size, String msg) {
        //创建查询条件对象 id 进行模糊查询
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("singer","%"+msg+"%");
        //WildcardQueryBuilder wildcardQueryBuilder1 = QueryBuilders.wildcardQuery("name","%"+msg+"%");
        //WildcardQueryBuilder wildcardQueryBuilder2 = QueryBuilders.wildcardQuery("name","%"+msg+"%");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(wildcardQueryBuilder);
        //boolQueryBuilder.should(wildcardQueryBuilder1);
        //boolQueryBuilder.should(wildcardQueryBuilder2);

        return RUtil.setOK("OK",musicIndexDao.search(boolQueryBuilder,PageRequest.of(page,size,Sort.by("id").ascending())));
    }

    @Override
    public String save(MusicIndex musicIndex) {
        return musicIndexDao.save(musicIndex) != null ? "新增成功":"新增失败";
    }
}
