package com.wangyi.entity;

import lombok.Data;

@Data
public class UserAwards {
    private int id;
    private int uid;
    //消耗的壹壳
    private int depletemoney;
    //抽奖的结果
    private String result;
    //奖励
    private String extramoney;
}
