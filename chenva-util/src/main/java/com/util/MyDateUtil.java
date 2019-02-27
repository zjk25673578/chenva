package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 自定义的日期参数转换器
 *
 * @author Administrator
 */
public class MyDateUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

    /**
     * 返回当前月份的1号是周几
     *
     * @return
     */
    public static int getCurrMonthFirstDayWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 返回当前年份的指定月份的第一天是周几
     *
     * @param month
     * @return
     */
    public static int getMonthFirstDayWeek(int month) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("请指定合适的月份 !");
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 返回当前的年份
     *
     * @return
     */
    public static int getCurrYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回当前月份
     *
     * @return
     */
    public static int getCurrMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回当前月的日期
     *
     * @return
     */
    public static int getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回当前时
     *
     * @return
     */
    public static int getCurrHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回当前分
     *
     * @return
     */
    public static int getCurrMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回当前秒
     *
     * @return
     */
    public static int getCurrSecond() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取当前的日期标识
     *
     * @return
     */
    public static String getDateStr() {
        return getDateStr(getCurrYear(), getCurrMonth(), getCurrDate());
    }

    /**
     * 获取当前的时间标识
     *
     * @return
     */
    public static String getTimeStr() {
        return MyUtil.concat(getCurrHour(), ":", getCurrMinute(), ":", getCurrSecond());
    }

    /**
     * 获取指定的日期标识
     *
     * @return
     */
    public static String getDateStr(int year, int month, int day) {
        return MyUtil.concat(year, month, day);
    }

    /**
     * 返回当前月份总共有多少天
     *
     * @return
     */
    public static int getCurrentMonthDays() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return getCurrentMonthDays(year, month);
    }

    /**
     * 返回当年的指定月份总共有多少天
     *
     * @param month
     * @return
     */
    public static int getCurrentMonthDays(int month) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("请指定合适的月份 !");
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return getCurrentMonthDays(year, month);
    }

    /**
     * 返回指定年份和指定月份总共有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getCurrentMonthDays(int year, int month) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("请指定合适的月份 !");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = simpleDateFormat.format(calendar.getTime());
        return Integer.parseInt(last);
    }
}
