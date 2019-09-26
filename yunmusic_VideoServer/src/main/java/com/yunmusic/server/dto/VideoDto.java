package com.yunmusic.server.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wangyi.entity.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VideoDto {
    private static final long serialVersionUID = 1L;

    /**
     * 视频ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 时长
     */
    private Integer time;

    /**
     * 简介
     */
    private String content;

    /**
     * 发布时间
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
     * 标签。例：伤感
     */
    private String tag;

    /**
     * 视频分类：1MV、2普通视频
     */
    private String type;

    /**
     * 视频地址
     */
    private String videourl;

    /**
     * 评论内容
     */
    private String details;

    private Integer userid;

    private Date commonCT;
    private Integer likenum;

    private String nickname;
    private List<Comment> comments;


}
