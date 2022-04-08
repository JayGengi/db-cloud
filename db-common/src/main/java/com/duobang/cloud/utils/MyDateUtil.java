package com.duobang.cloud.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: iamli
 * @Time: 2021/5/12 下午4:40
 * @Description:
 */
public class MyDateUtil {

    /**
     * 根据差异获取日期
     *
     * @param date
     * @param days 计算差异日期， days 1 获取后一天， days -1 获取前一天
     * @return diffDate 返回差异日期
     */
    public static Date getDifferentDate(Date date, int days) {

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        c.add(Calendar.DAY_OF_MONTH, days);

        return c.getTime();
    }

    /**
     * 根据差异获取日期
     *
     * @param date
     * @param days 计算差异日期， days 1 获取后一天， days -1 获取前一天
     * @return diffDate 返回差异日期
     */
    public static java.sql.Date getDifferentDate(java.sql.Date date, int days) {

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        c.add(Calendar.DAY_OF_MONTH, days);

        return new java.sql.Date(c.getTime().getTime());
    }

    /**
     * Date转LocalDate
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }
    /**
     * 计算两个日期相差的秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calLastedTime(Date startDate, Date endDate) {
        if(null == endDate) {
            return 0;
        }
        long a = endDate.getTime();
        long b = startDate.getTime();
        int c = (int) ((a - b) / 1000);
        return c;
    }
}
