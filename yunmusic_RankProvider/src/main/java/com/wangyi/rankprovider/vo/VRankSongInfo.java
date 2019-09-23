package com.wangyi.rankprovider.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: VRankSongInfo
 * @description: TODO
 * @author: ccq
 * @create: 2019-09-21 10:46
 **/
@Data
public class VRankSongInfo {
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
}
