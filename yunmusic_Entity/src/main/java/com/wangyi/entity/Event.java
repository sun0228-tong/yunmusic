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
public class Event extends Model<Event> {

    private static final long serialVersionUID = 1L;

    /**
     * 动态ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createtime;

    /**
     * 图片地址
     */
    private String imgurl;

    /**
     * 视频/音频地址
     */
    private String mediaurl;

    /**
     * 点赞量
     */
    private Integer likenum;


}
