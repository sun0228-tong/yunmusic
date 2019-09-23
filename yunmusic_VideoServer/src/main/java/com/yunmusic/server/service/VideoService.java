package com.yunmusic.server.service;

import com.wangyi.entity.Video;
import com.yunmusic.server.dto.VideoDto;


import java.util.List;

public interface VideoService {
    List<Video> selectByTag( String type);
    List<Video> selectAllTag();
    List<Video> selectAllType();
    List<Video> selectByType(String type);

    List<Video> selectMVByTag(String tag);

    List<Video> selectByPlaynum();

    List<Video> selectMVByPlaynumAndTag(String tag);

    Video selectOneVideo(Integer id);
}
