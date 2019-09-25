package com.wangyi.search.service.impl;

import com.wangyi.search.dao.NewsIndexDao;
import com.wangyi.search.service.NewsIndexService;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NewsIndexServiceImpl implements NewsIndexService {

    @Autowired
    private NewsIndexDao newsIndexDao;

    @Override
    public R queryAll(int page, int size) {

        return RUtil.setOK("OK",newsIndexDao.findAll(PageRequest.of(page, size, Sort.by("id").ascending())));
    }
}
