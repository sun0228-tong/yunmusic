package com.wangyi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    //获取今天剩余秒数
    public static int getLastSeconds() {
        Calendar calendar = Calendar.getInstance();
        String last = getTime() + " 23:59:59";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date lastDate = sdf.parse(last);
            return (int)(lastDate.getTime() - System.currentTimeMillis()) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 获得固定格式的当前时间字符串
    private static String getTime() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.format(new Date());
    }

    //获取指定时间
    public static Date getTime(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,seconds);
        return calendar.getTime();
    }


}
