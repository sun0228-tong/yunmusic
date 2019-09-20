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
public class Songlistsong extends Model<Songlistsong> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌单-歌曲（中间表）ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌单ID
     */
    private Integer songlistid;

    /**
     * 歌曲ID
     */
    private Integer songid;


}
