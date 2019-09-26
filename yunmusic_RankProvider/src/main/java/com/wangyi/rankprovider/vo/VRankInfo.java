package com.wangyi.rankprovider.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: yunmusic
 * @ClassName: VRankInfo
 * @description: 排行榜页面 只有八首歌的那种
 * @author: ccq
 * @create: 2019-09-21 10:43
 **/

@Data
public class VRankInfo {

   private int songlistid;
    /**
     * 排行榜名字
     */
    private String songListName;
    /**
     * 更新时间
     */
    private Date createTime;
    /**
     * 歌曲名字和歌手集合
     */
    private List<VRankSongInfo> rankSonglist;


}
