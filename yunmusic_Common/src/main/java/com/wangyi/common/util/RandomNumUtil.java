package com.wangyi.common.util;

import java.util.Random;

/**
 * @program: yunmusic
 * @description:
 * @author: suntong
 * @create: 2019-09-26 10:20
 */
public class RandomNumUtil {
    //随机生成指定位数的数字
    public static int createNum(int len){
        Random random=new Random();
        //2 10-99 3 100-999  4 1000-9999
        return random.nextInt((int)Math.pow(10,len)-(int)Math.pow(10,len-1))+(int)Math.pow(10,len-1);
    }
}
