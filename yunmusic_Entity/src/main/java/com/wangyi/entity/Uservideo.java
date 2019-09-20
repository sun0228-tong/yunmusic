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
public class Uservideo extends Model<Uservideo> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户-收藏视频（中间表）ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 视频ID
     */
    private Integer videoid;


}
