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
public class Songlist extends Model<Songlist> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌单名
     */
    private String name;

    /**
     * 创建人id
     */
    private Integer uid;

    /**
     * 歌单简介
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 歌曲数量
     */
    private Integer songnum;

    /**
     * 歌单标签。例：伤感
     */
    private String tag;

    /**
     * 歌单类型：1专辑、2歌单
     */
    private String type;

    /**
     * 歌单图片
     */
    private String imgurl;


}
