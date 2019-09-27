package com.wangyi.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String phone;
    private String pass;
    //设备类型：APP、PC、SR(小程序)、DRE(穿戴设备)
    private String facility;
    //设备的详细信息
    private String facinfo;
}
