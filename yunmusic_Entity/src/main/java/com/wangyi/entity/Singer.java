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
public class Singer extends Model<Singer> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌手ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手名
     */
    private String name;

    /**
     * 歌手类型：男歌手/女歌手/乐队组合
     */
    private String type;

    /**
     * 语种
     */
    private String language;

    /**
     * 头像地址
     */
    private String imgurl;


}
