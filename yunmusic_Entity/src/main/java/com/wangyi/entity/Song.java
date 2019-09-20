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
public class Song extends Model<Song> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌曲ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌名
     */
    private String name;

    /**
     * 歌手ID
     */
    private Integer singerid;

    /**
     * 视频ID
     */
    private Integer videoid;

    /**
     * 语种
     */
    private String language;

    /**
     * 时长
     */
    private Integer time;

    /**
     * 播放量
     */
    private Integer playnum;

    /**
     * 是否原创：1原创，2非原创
     */
    private Integer original;

    /**
     * 歌单标签。例：伤感
     */
    private String tag;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 歌曲地址
     */
    private String songurl;


}
