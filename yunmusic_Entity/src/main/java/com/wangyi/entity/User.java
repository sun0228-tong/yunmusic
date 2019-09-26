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
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号（账号）
     */
    private String telephone;

    /**
     * 邮箱（也可以为账号）
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 地区
     */
    private String address;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 头像地址
     */
    private String imgurl;


}
