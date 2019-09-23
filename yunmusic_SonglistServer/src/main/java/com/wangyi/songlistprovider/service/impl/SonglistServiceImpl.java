package com.wangyi.songlistprovider.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyi.entity.Lyric;
import com.wangyi.entity.Songlist;
import com.wangyi.songlistprovider.common.Tag;
import com.wangyi.songlistprovider.dao.SonglistDao;
import com.wangyi.songlistprovider.service.SonglistService;
import com.wangyi.songlistprovider.vo.VSongInfo;
import com.wangyi.songlistprovider.vo.VSongInfo2;
import com.wangyi.songlistprovider.vo.VSonglistInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SonglistServiceImpl extends ServiceImpl<SonglistDao, Songlist> implements SonglistService {

    @Autowired(required = false)
    private SonglistDao songlistDao;

    @Override
    public List<Tag> queryAllTags() {
        return songlistDao.selectAllTags();
    }

    @Override
    public List<Songlist> queryAll(Page<Songlist> page, String tag) {
        return songlistDao.selectAll(page, tag);
    }

    @Override
    public VSonglistInfo selectOneSonglistWithSongs(int songlistid) {
        return songlistDao.selectOneSonglistWithSongs(songlistid);
    }

    @Override
    public VSonglistInfo selectOneAlbumWithSongs(int songlistid) {
        return songlistDao.selectOneAlbumWithSongs(songlistid);
    }

    @Override
    public List<VSongInfo> querySongsByKeyword(int songlistid, String keyword) {
        return songlistDao.selectSongsByKeyword(songlistid, keyword);
    }

    @Override
    public VSongInfo2 queryPlaySongById(int songid) {
        return songlistDao.selectPlayingSong(songid);
    }

    @Override
    public String querySonglistNameById(Integer songlistid) {
        return songlistDao.selectSonglistNameById(songlistid);
    }

    @Override
    public Lyric queryLyric(Integer songid) {
        return songlistDao.selectLyric(songid);
    }

}
