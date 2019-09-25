package com.wangyi.songlistprovider.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyi.entity.Lyric;
import com.wangyi.entity.Singer;
import com.wangyi.entity.Song;
import com.wangyi.entity.Songlist;
import com.wangyi.songlistprovider.common.Tag;
import com.wangyi.songlistprovider.vo.VSongInfo;
import com.wangyi.songlistprovider.vo.VSongInfo2;
import com.wangyi.songlistprovider.vo.VSonglistInfo;

import java.util.List;

public interface SonglistService extends IService<Songlist> {

    List<Tag> queryAllTags();

    List<Songlist> queryAll(Page<Songlist> page, String tag);

    VSonglistInfo selectOneSonglistWithSongs(int songlistid);

    VSonglistInfo selectOneAlbumWithSongs(int songlistid);

    List<VSongInfo> querySongsByKeyword(int songlistid, String keyword);

    VSongInfo2 queryPlaySongById(int songid);

    String querySonglistNameById(Integer songlistid);

    Lyric queryLyric(Integer songid);

    List<Singer> queryAllSinger(Page<Songlist> page, String language, String type, String firstPinyin);

    List<Song> queryTop50BySingerId(int singerid);

    List<VSonglistInfo> queryAllAlbumWithSong(int singerid);

    String querySingerName(int singerid);

    int querySingerAlbumCount(int singerid);

    int querySingerVideoCount(int singerid);

    String querySingerDetail(int singerid);

}
