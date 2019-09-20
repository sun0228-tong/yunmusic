package com.wangyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Recomment extends Model<Recomment> {

    private static final long serialVersionUID = 1L;

    /**
     * 回复评论ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论ID
     */
    private Integer commentid;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞次数
     */
    private Integer likenum;

}
