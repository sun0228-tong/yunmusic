package com.yunmusic.server.service.impl;

import com.wangyi.entity.Video;
import com.yunmusic.server.dao.VideoDao;
import com.yunmusic.server.dto.VideoDto;
import com.yunmusic.server.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired(required = false)
    private VideoDao videoDao;

    @Override
    public List<Video> selectByTag(String tag) {
        return videoDao.selectByTag(tag);
    }

    @Override
    public List<Video> selectAllTag() {
        return videoDao.selectAllTag();
    }

    @Override
    public List<Video> selectAllType() {
        return videoDao.selectAllType();
    }

    @Override
    public List<Video> selectByType(String type) {
        return videoDao.selectByType(type);
    }

    @Override
    public List<Video> selectMVByTag(String tag) {
        return videoDao.selectMVByTag(tag);
    }

    @Override
    public List<Video> selectByPlaynum() {
        return videoDao.selectByPlaynum();
    }

    @Override
    public List<Video> selectMVByPlaynumAndTag(String tag) {
        return videoDao.selectMVByPlaynumAndTag(tag);
    }

    @Override
    public Video selectOneVideo(Integer id) {
        Video video = null;
        try {
            video = videoDao.selectOneVideo(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return video;

    }
}
