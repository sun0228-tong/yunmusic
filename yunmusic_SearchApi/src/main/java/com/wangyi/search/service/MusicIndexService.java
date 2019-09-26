package com.wangyi.search.service;

import com.wangyi.common.vo.R;
import com.wangyi.search.model.MusicIndex;

import java.util.List;

public interface MusicIndexService {

    R<List<MusicIndex>> findAll(int page, int size);

    R<List<MusicIndex>> findLike(int page, int size, String msg);

    String save(MusicIndex musicIndex);
}
