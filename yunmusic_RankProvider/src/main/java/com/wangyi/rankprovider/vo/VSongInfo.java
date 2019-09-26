package com.wangyi.rankprovider.vo;

import lombok.Data;

/**
 * @program: yunmusic
 * @ClassName: VSongInfo
 * @description: 热歌榜歌单歌曲信息
 * @author: ccq
 * @create: 2019-09-23 09:09
 **/
@Data
public class VSongInfo {

    /**
     * 歌手名字
     */
    private String singerName;
    /**
     * 歌曲名字
     */
    private String songName;
    /**
     * 歌曲id
     */
    private int songid;
    /**
     * 歌曲播放地址
     */
    private String songurl;

    /**
     * 专辑名
     */
    private String album;

    /**
     * 视频ID
     */
    private Integer videoid;

    /**
     * 时长
     */
    private Integer time;

    /**
     * 播放量
     */
    private Integer songplaynum;




}
