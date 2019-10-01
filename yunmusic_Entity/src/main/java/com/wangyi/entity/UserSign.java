package com.wangyi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserSign {
    private int id;
    private int uid;
    private Date ctime;
    private int days;
    private int basemoney;
    private int extramoney;
}
