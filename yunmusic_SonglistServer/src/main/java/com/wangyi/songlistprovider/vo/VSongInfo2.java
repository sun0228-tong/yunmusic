package com.wangyi.songlistprovider.vo;

import lombok.Data;

/**
 * 用户歌曲播放页面展示 歌曲信息
 */
@Data
public class VSongInfo2 {

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
     * 来源 歌单ID/专辑ID
     */
    private Integer sourceid;

    /**
     * 来源 歌单名/专辑名
     */
    private String sourcename;

    /**
     * 歌曲所属专辑的图片地址
     */
    private String albumimgurl;

}
