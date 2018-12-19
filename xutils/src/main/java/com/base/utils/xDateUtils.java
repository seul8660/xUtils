package com.base.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期操作工具类.
 *
 * @author shimiso
 */

public class xDateUtils {

    public final static String FORMAT_TIME = "HH:mm";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_MONTH_DAY_TIME = "MM-dd HH:mm";
    public final static String FORMAT_DATE = "yyyy-MM-dd";

    public static String getFormatToday(String dateFormat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(currentTime);
    }


    public static Date stringToDate(String dateStr, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
    public static String dateToString(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    public static String getWeekOfDate(String date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(stringToDate(date,FORMAT_DATE));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     * @param date 给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static String getFormatDateAdd(Date date, int amount, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDateTime(cal.getTime(), format);
    }
    public static String getFormatDateAdd(String date, int amount, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(stringToDate(date,FORMAT_DATE));
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDateTime(cal.getTime(), format);
    }
    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串。最为通用。
     * @param date 指定的日期
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getYear(String date){
        String[] dates =date.split("-");
        String year="";
        if(!dates[2].isEmpty()){
            year = dates[0];

        }
        return year;
    }

    public static String getYear(){
        String[] dates =dateToString(new Date(),FORMAT_DATE).split("-");
        String year="";
        if(!dates[2].isEmpty()){
            year = dates[0];

        }
        return year;
    }

    public static String getYear(Date date){
        String[] dates =dateToString(date,FORMAT_DATE).split("-");
        String year="";
        if(!dates[2].isEmpty()){
            year = dates[0];

        }
        return year;
    }

    public static String getMonth(String date){
        String[] dates =date.split("-");
        String year="";
        if(!dates[1].isEmpty()){
            year = xUtils.convertToStr(xUtils.convertToInt(dates[1])-1);
        }
        return year;
    }

    public static String getMonth(){
        String[] dates =dateToString(new Date(),FORMAT_DATE).split("-");
        String year="";
        if(!dates[1].isEmpty()){
            year = xUtils.convertToStr(xUtils.convertToInt(dates[1])-1);

        }
        return year;
    }

    public static String getMonth(Date date){
        String[] dates =dateToString(date,FORMAT_DATE).split("-");
        String year="";
        if(!dates[1].isEmpty()){
            year = xUtils.convertToStr(xUtils.convertToInt(dates[1])-1);

        }
        return year;
    }


    public static String getDay(String date){
        String[] dates =date.split("-");
        String year="";
        if(!dates[2].isEmpty()){
            year = dates[2];

        }
        return year;
    }


    public static String getHour(){

        String[] dates =dateToString(new Date(),FORMAT_TIME).split(":");
        String hour = "";
        if(!dates[0].isEmpty()){
            hour = dates[0];

        }
        return hour;


    }

    public static String getDay(){
        String[] dates =dateToString(new Date(),FORMAT_DATE).split("-");
        String year="";
        if(!dates[2].isEmpty()){
            year = dates[2];

        }
        return year;
    }
    public static String getDay(Date date){
        String[] dates =dateToString(date,FORMAT_DATE).split("-");
        String year="";
        if(!dates[2].isEmpty()){
            year = dates[2];

        }
        return year;
    }


    /*获取六个小时前的日期*/
    public static Date getLastSixTime(){

         Date currentTime = new Date();
         return new Date(currentTime.getTime() - 6*60*60*1000) ;

    }


    public static <T> String getTimeBySecond(T second){
        int temp = xUtils.convertToInt(second);
        int hour = (int) Math.floor(temp/3600);
        int minute = (int) Math.floor((temp -(hour*3600))/60);
        int seconds = temp - hour*3600 -minute*60;

        return (hour==0?"":xUtils.getZero(hour)+":") + xUtils.getZero(minute)+":"+ (seconds<=0?"00":xUtils.getZero(seconds));
    }


}