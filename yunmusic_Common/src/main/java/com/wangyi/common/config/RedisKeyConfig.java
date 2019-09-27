package com.wangyi.common.config;

public class RedisKeyConfig {

    //ES同步的key

    //记录新增
    public static final String ES_ADDKEY = "yunmusicADD";
    //记录删除
    public static final String ES_DELKEY = "yunmusicDEL";
    //记录修改内容
    public static final String ES_UPDATEKEY = "yunmusicUPDATE";

    //验证码
    //后面需要跟上手机号
    public static final String SMS_VCODE = "yunmusicSMSVCODE";

    //短信限制
    //1天的限制 后面加上手机号   次数限制为 10次
    public static final String SMS_DAY = "yunmusicDAYS";
    //1小时的限制 后面加上手机号   次数限制为 5次
    public static final String SMS_HOUR = "yunmusicHOURS";
    //1分钟的限制 后面加上手机号   次数限制为 1次
    public static final String SMS_MIN = "yunmusicMINS";

    //登陆相关的信息
    //user:手机号:设备类型 值为 令牌对象
    public static final String USER_TOKEN = "user";
    //令牌(token)值  值为 用户信息的 json对象
    public static final String TOKEN_USER = "usertoken";
    //记录被挤掉的信息 令牌(token)值 值为 新登陆的信息
    public static final String LOSE_TOKEN = "losetoken";

}
