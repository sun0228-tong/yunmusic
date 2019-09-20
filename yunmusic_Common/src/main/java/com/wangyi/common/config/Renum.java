package com.wangyi.common.config;
/**
 * 枚举 类型
 * 统一结果返回码
 * 200 成功
 * 400 失败*/
public enum Renum {
    SUCCESS(200),ERROR(400);
    private int code;
    private Renum(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }
}
