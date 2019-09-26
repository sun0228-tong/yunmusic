package com.wangyi.search.service;

import com.wangyi.common.vo.R;

public interface NewsIndexService {
    R queryAll(int page, int size);
}
