package com.hk.learn.utils;

import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private DateUtil(){}

    /**
     * 格式化当前时间
     * @return
     */
    public static String getNowFormat()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    /**
     * 格式化当前时间，带毫秒
     * @return
     */
    public static String getNowFormatM()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        return sdf.format(date);
    }

    /**
     * 时间戳，到毫秒
     * @return
     */
    public static String getSessionId()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(date);
    }

    public static Calendar toCalendar(Date t) {
        if(t==null)return null;
        Calendar c = Calendar.getInstance();
        c.setTime(t);
        return c;
    }

    public static Date setTime(Date date, int hourOfDay, int minute, int second) {
        if(date==null)return date;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return c.getTime();
    }

    public static boolean isBetween(Date date, Date start, Date end) {
        if(date==null)return true;
        return (start==null||start.getTime()<=date.getTime())
                &&(end==null||date.getTime()<=end.getTime());
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    public static int dayForWeek(Calendar c){
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
}
