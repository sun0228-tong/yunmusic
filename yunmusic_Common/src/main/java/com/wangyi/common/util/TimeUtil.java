package com.wangyi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
    public static String getTime() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.format(new Date());
    }

    //获取指定时间
    public static Date getTime(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,seconds);
        return calendar.getTime();
    }

    //获取当前秒数(单位：秒)
    public static long getCurrentTime() {
        return System.currentTimeMillis()/1000;
    }

    //比较相差的天数 以24小时为基准
    public static int getDaysByHours(Date time) {
        return (int) (System.currentTimeMillis() - time.getTime())/(1000*60*60*24);
    }

    //比较相差的天数 以日期天数为基准
    public static int getDaysByDay(Date time) {
        LocalDate localDate=time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.DAYS.between(localDate,LocalDate.now());
    }


}
