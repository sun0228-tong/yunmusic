package com.wangyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author suntong
 * @since 2019-09-20
 */
@Data
public class Video extends Model<Video> {

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

}
