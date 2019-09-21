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
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论对象ID
     */
    private Integer objectid;

    /**
     * 评论对象类型：1歌单、2视频、3歌曲、4动态
     */
    private Integer type;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 点赞量
     */
    private Integer likenum;

    /**
     * 评论时间
     */
    private Date createtime;

    /**
     * 评论内容
     */
    private String details;
}
