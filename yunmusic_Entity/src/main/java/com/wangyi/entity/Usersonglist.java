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
public class Usersonglist extends Model<Usersonglist> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户-收藏歌单（中间表）ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 歌单ID
     */
    private Integer songlistid;


}
