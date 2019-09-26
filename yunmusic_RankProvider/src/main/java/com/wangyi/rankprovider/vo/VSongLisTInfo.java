package com.wangyi.rankprovider.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: VSongLisTInfo
 * @description: 热歌榜歌单信息
 * @author: ccq
 * @create: 2019-09-23 09:11
 **/
@Data
public class VSongLisTInfo {


    /**
     * 歌单id
     */
    private int songlistid;
    /**
     * 排行榜名字
     */
    private String songListName;
    /**
     * 歌单图片
     */
    private String imgurl;
    /**
     * 创建者头像
     */
    private String userimgurl;

    /**
     * 更新时间
     */
    private Date createTime;

    /**
     * 歌单类型（专辑或歌单）
     */
    private String type;
    /**
     * 歌曲数
     */
    private int songnum;
    /**
     * 播放数
     */
    private int playnum;
    /**
     * 收藏数
     */
    private int starnum;
    /**
     * 创建者
     */
    private String userName;

    /**
     * 歌单简介
     */
    private String content;

    /**
     * 所含歌曲集合
     */
    private List<VSongInfo> songsList;

}
