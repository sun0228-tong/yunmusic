package com.wangyi.songlistprovider.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VSonglistInfo {

    /**
     * 歌单ID
     */
    private Integer id;

    /**
     * 歌单名
     */
    private String name;

    /**
     * 创建人名字
     */
    private String creator;

    /**
     * 所含歌曲集合
     */
    private List<VSongInfo> songsList;

    /**
     * 歌单简介
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 播放量
     */
    private Integer playnum;

    /**
     * 收藏量
     */
    private Integer starnum;


    /**
     * 歌曲数量
     */
    private Integer songnum;

    /**
     * 歌单标签。例：伤感
     */
    private String tag;

    /**
     * 歌单类型：专辑、歌单
     */
    private String type;

    /**
     * 歌单图片
     */
    private String imgurl;


}
