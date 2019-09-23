package com.wangyi.songlistprovider.vo;

import lombok.Data;

/**
 * 用于歌单页面内展示的 歌曲 信息
 */
@Data
public class VSongInfo {


    /**
     * 歌曲ID
     */
    private Integer id;

    /**
     * 歌名
     */
    private String name;

    /**
     * 歌手名
     */
    private String singer;

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
    private Integer playnum;

    /**
     * 歌曲地址
     */
    private String songurl;

}
