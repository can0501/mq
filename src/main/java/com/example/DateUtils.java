package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转字符串
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 字符串转日期
     *
     * @param timeStr 日期字符串（仅支持两个格式："yyyy-MM-dd HH:mm:ss" or "yyyy-MM-dd"）
     * @return
     */
    public static Date stringToDate(String timeStr, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取两个时间的最小值
     *
     * @param time1
     * @param time2
     * @return
     */
    public static LocalDateTime min(LocalDateTime time1, LocalDateTime time2) {
        return time1.compareTo(time2) < 0 ? time1 : time2;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String[] strings = generalShardingDateRange(10, i, DateTimeUnit.Hour, 0, 10);
            System.out.println(Arrays.toString(strings));
            System.out.println("*************");
            // String[] strings1 = shardTime(10, i);
            // System.out.println(Arrays.toString(strings1));
        }

    }

    private static  String[] shardTime(int shardingTotal,int shardingItem) {
        LocalDateTime starTime = LocalDateTime.of(LocalDate.now().plusMonths(-shardingTotal), LocalTime.MIN);
        // DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime shardTime = LocalDateTime.of(starTime.toLocalDate().plusMonths(shardingItem), LocalTime.MAX);
        Date starTimeDate = Date.from(starTime.atZone(ZoneId.systemDefault()).toInstant());
        Date shardTimeDate = Date.from(shardTime.atZone(ZoneId.systemDefault()).toInstant());
        return new String[]{DateUtils.dateToString(starTimeDate,"yyyy-MM-dd HH:mm:ss")
                , DateUtils.dateToString(shardTimeDate,"yyyy-MM-dd HH:mm:ss")};
    }

    public enum DateTimeUnit {
        Day,
        Hour,
        Minute,
    }

    /**
     * 通用的获取分片处理时间区域方法
     *
     * @param shardingTotal        分片总数
     * @param currentShardingIndex 当前分片
     * @param unit                 时间单位
     * @param beforeTime           多久之前（unit）
     * @param totalShardingTime    总分片所要扫描数据的最大时间 （unit）
     * @return 取值：[beforeTime-totalShardingTime，beforeTime]
     */
    public static String[] generalShardingDateRange(int shardingTotal, int currentShardingIndex,
                                                    DateTimeUnit unit, int beforeTime, int totalShardingTime) {
        int befer = -beforeTime - totalShardingTime;
        int range = totalShardingTime / shardingTotal;
        int start = befer + (currentShardingIndex * range);
        int end = start + range;
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now();
        DateTimeFormatter formatter;
        switch (unit) {
            case Minute:
                startDateTime = startDateTime.plusMinutes(start);
                endDateTime = endDateTime.plusMinutes(end);
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
                return new String[]{startDateTime.format(formatter), endDateTime.format(formatter)};
            case Hour:
                startDateTime = startDateTime.plusHours(start);
                endDateTime = endDateTime.plusHours(end);
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00:00");
                return new String[]{startDateTime.format(formatter), endDateTime.format(formatter)};
            case Day:
                startDateTime = startDateTime.plusDays(start);
                endDateTime = endDateTime.plusDays(end);
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return new String[]{startDateTime.format(formatter), endDateTime.format(formatter)};
            default:
                throw new RuntimeException("不支持的时间单位！");
        }
    }

    /**
     * 通用的获取分片处理时间区域方法
     *
     * @param shardingTotal        分片总数
     * @param currentShardingIndex 当前分片
     * @param unit                 时间单位
     * @param beforeTime           多久之前（unit）
     * @param totalShardingTime    总分片所要扫描数据的最大时间 （unit）
     * @return 取值：[beforeTime-totalShardingTime，beforeTime]
     */
    public static LocalDateTime[] generalShardingDateRangeLocalDateTime(int shardingTotal, int currentShardingIndex,
                                                                        DateTimeUnit unit, int beforeTime, int totalShardingTime) {
        int befer = -beforeTime - totalShardingTime;
        int range = totalShardingTime / shardingTotal;
        int start = befer + (currentShardingIndex * range);
        int end = start + range;
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now();
        switch (unit) {
            case Minute:
                startDateTime = startDateTime.plusMinutes(start).withSecond(0);
                endDateTime = endDateTime.plusMinutes(end).withSecond(0);
                break;
            case Hour:
                startDateTime = startDateTime.plusHours(start).withMinute(0).withSecond(0);
                endDateTime = endDateTime.plusHours(end).withMinute(0).withSecond(0);
                break;
            case Day:
                startDateTime = startDateTime.plusDays(start).withHour(0).withMinute(0).withSecond(0);
                endDateTime = endDateTime.plusDays(end).withHour(0).withMinute(0).withSecond(0);
                break;
            default:
                throw new RuntimeException("不支持的时间单位！");
        }
        return new LocalDateTime[]{startDateTime, endDateTime};
    }

}
