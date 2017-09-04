package com.oa.core.utils.date;

import com.oa.core.constant.RegexConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具类
 * <p/>
 * Created by [Zy]
 * 2017/8/10 11:03
 */
public class DateUtil {

    // 毫秒
    private static final int MILLISECOND = 1000;
    // 分钟
    private static final int MINUTE = 60;
    private static String default_pattern = "yyyy-MM-dd HH:mm:ss";

    public static final int C_A_D_B = 1;
    public static final int A_C_B_D = 2;
    public static final int A_C_D_B = 3;
    public static final int C_A_B_D = 4;

    private DateUtil() {}

    /**
     * 将字符串转换为日期
     *
     * @param time 待转换的日期字符串
     * @return 转换后的日期，如果转换失败返回 Null
     */
    public static Date parse(String time) {
        try {
            return new SimpleDateFormat(default_pattern).parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将日期字符串转换为指定日期格式的日期对象
     *
     * @param time   待转换的日期字符串
     * @param patten 指定的日期格式
     * @return 转换后的日期对象，转换失败返回 Null
     */
    public static Date parse(String time, String patten) {
        try {
            return new SimpleDateFormat(patten).parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断日期是否合法
     *
     * @param date 字符串日期
     * @return 合法返回 true 非法返回 false
     */
    public static boolean isLegal(String date) {
        try {
            if (date != null && !"".equalsIgnoreCase(date.trim())) {
                return date.matches(RegexConstant.DATE_YYYY_MM_DD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将日期转换为日期字符串，默认转换格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param date 待转换的日期对象
     * @return 转换后的日期字符串，转换失败返回空串
     */
    public static String format(Date date) {
        return format(date, default_pattern);
    }

    /**
     * 将日期时间转换为日期字符串
     *
     * @param timeMillis 日期时间
     * @return 转换后的日期字符串，转换失败返回空串，默认转换格式为 yyyy-MM-dd HH:mm:ss
     */
    public static String format(long timeMillis) {
        try {
            return new SimpleDateFormat(default_pattern).format(timeMillis);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 将日期转换为指定格式字符串
     *
     * @param date    待转换的日期对象
     * @param pattern 指定的日期格式
     * @return 转换后的日期字符串，转换失败返回空串
     */
    public static String format(Date date, String pattern) {
        try {
            if (date == null) {
                return "";
            }

            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当天现在时间是几点
     */
    public static int getCurrentDayHourNumber() {
        Calendar ca = Calendar.getInstance();
        return ca.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当天是本月的几号
     */
    public static int getCurrentDayDayNumber() {
        Calendar ca = Calendar.getInstance();
        return ca.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当月的最大天数
     */
    public static int getCurrentMonthMaxDay() {
        Calendar ca = Calendar.getInstance();
        return ca.getMaximum(Calendar.DATE);
    }

    /**
     * 获取起始日期到截止日期间隔多少秒，必须是带时分秒的日期对象
     * @param beginTime 起始日期，带时分秒的日期对象
     * @param endTime 截止日期，带时分秒的日期对象
     * @return 起始日期到截止日期间隔多少秒，转换失败返回-1
     */
    public static long getTimeIntervalSecond(Date beginTime, Date endTime) {
        try {
            long milSecond = endTime.getTime() - beginTime.getTime();
            return milSecond / MILLISECOND;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取起始日期到截止日期间隔多分钟，必须是带时分秒的日期对象
     * @param beginTime 起始日期，带时分秒的日期对象
     * @param endTime 截止日期，带时分秒的日期对象
     * @return 起始日期到截止日期间隔多少分钟，转换失败返回-1
     */
    public static long getTimeIntervalMinute(Date beginTime, Date endTime) {
        try {
            return getTimeIntervalSecond(beginTime, endTime) / MINUTE;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取起始日期到截止日期间隔多分钟，必须是带时分秒的日期对象
     * @param beginTime 起始日期，带时分秒的日期对象
     * @param endTime 截止日期，带时分秒的日期对象
     * @return 起始日期到截止日期间隔多少分钟，转换失败返回-1
     */
    public static long getTimeIntervalMinute(String beginTime, String endTime) {
        try {
            return getTimeIntervalMinute(parse(beginTime), parse(endTime));
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取起始日期到截止日期间隔多长时间(带单位)，必须是带时分秒的日期对象
     * @param beginTime 起始日期，带时分秒的日期对象
     * @param endTime 截止日期，带时分秒的日期对象
     * @return 起始日期到截止日期间隔多少时间，转换失败返回-1
     */
    public static String getTimeIntervalMinuteStr(Date beginTime, Date endTime) {
        long minute = getTimeIntervalMinute(beginTime, endTime);
        return formatMinute(minute);
    }

    /**
     * 计算分钟，以天时分为单位转换结果
     * @param minute 分钟数
     * @return 转换后的带单位的结果
     */
    public static String formatMinute(long minute) {
        long day = 0;
        long hour = 0;
        if (minute >= 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour > 24) {
            day = hour / 24;
            hour = hour % 24;
        }
        if (day > 0) {
            return day + "天" + hour + "小时" + minute + "分钟";
        }
        if (hour > 0) {
            return hour + "小时" + minute + "分钟";
        }
        return minute + "分钟";
    }



    /**
     * 获取指定日期格式的当前日期
     *
     * @param pattern 格式
     */
    public static String getCurrentDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 获取默认格式的当前日期，默认转换格式为 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateFormat() {
        return new SimpleDateFormat(default_pattern).format(new Date());
    }

    /**
     * 将日期对象转换为默认日期格式的日期字符串，默认格式yyyy-MM-dd HH:mm:ss
     *
     * @param date 待转换的日期对象
     * @return 返回转换为默认日期格式的日期字符串
     */
    public static String getDateFormat(Date date) {
        return new SimpleDateFormat(default_pattern).format(date);
    }

    /**
     * 将日期对象转换为指定日期格式的日期字符串，如果指定的日期格式为空
     * 则使用默认格式 yyyy-MM-dd HH:mm:ss 进行转换
     *
     * @param date    待转换的日期对象
     * @param pattern 指定要转换的日期格式
     * @return 转换后的日期字符串
     */
    public static String getDateFormat(Date date, String pattern) {
        if (pattern != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }

        return getDateFormat(date);
    }


    /**
     * 获取当年年份
     *
     * @return 返回当前年份
     */
    public static Integer getCurrentYear() {
        // 获取日历对象（获取本地信息）
        Calendar calendar = Calendar.getInstance();
        // 返回本地年份
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 当前日期修改
     */
    public static Date modifyDate(int year, int month, int day, int hour, int minute, int second) {
        return modifyDate(new Date(), year, month, day, hour, minute, second);
    }

    /**
     * 当前日期修改
     */
    public static Date modifyDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.YEAR, year);
        ca.add(Calendar.MONTH, month);
        ca.add(Calendar.DATE, day);
        ca.add(Calendar.HOUR, hour);
        ca.add(Calendar.MINUTE, minute);
        ca.add(Calendar.SECOND, second);
        return ca.getTime();
    }

    /**
     * 获取本周的第一天
     */
    public static Date getCurrentWeekBegin() {
        Calendar c = Calendar.getInstance();
        //当天减当天在本周的第几天+1
        c.add(Calendar.DAY_OF_WEEK, (-1) * c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    /**
     * 获取本周的最后一天
     */
    public static Date getCurrentWeekEnd() {
        Calendar c = Calendar.getInstance();
        //当天 + (7 - 本周的第几天)
        c.add(Calendar.DAY_OF_WEEK, 7 - c.get(Calendar.DAY_OF_WEEK));
        return c.getTime();
    }

    /**
     * 日期比较
     *
     * @return date1 > date2  返回true
     */
    public static boolean compare(Date date1, Date date2) {
        return date1.compareTo(date2) > 0;
    }


    public static List<String> getDaysOfTwoDay(String startTime, String endTime) {

        String pattern = "yyyy-MM-dd";
        Date begin = parse(startTime, pattern);
        Date end = parse(endTime, pattern);

        List<String> list = new ArrayList<String>();

//        if (begin.getTime() == end.getTime()) {
//            list.add(format(begin, pattern));
//            return list;
//        }

        if (begin != null && end != null) {
            while (begin.getTime() <= end.getTime()) {
                list.add(format(begin, pattern));
                begin = modifyDate(begin, 0, 0, 1, 0, 0, 0);
            }
        }

        return list;
    }

    /**
     * 获取周几
     */
    public static int weekDay(Date currentDate) {
        String pattern = "E";
        String currentTimeStr = new SimpleDateFormat(pattern, Locale.CHINESE).format(currentDate);
        switch (currentTimeStr.substring(2).toCharArray()[0]) {
            case '一':
                return 1;
            case '二':
                return 2;
            case '三':
                return 3;
            case '四':
                return 4;
            case '五':
                return 5;
            case '六':
                return 6;
            case '天':
                return 7;
            case '日':
                return 7;

        }
        return 1;
    }


    /**
     * 判断字符串是否是日期字符串，如果是日期格式的字符串返回 true，否则返回 fale
     * @param time 日期字符串
     * @return 是返回 true，否则返回 false
     */
    public static boolean isDateString(String time) {
        return isDateString(time, default_pattern);
    }

    /**
     * 判断字符串是否是日期字符串，如果是日期格式的字符串返回 true，否则返回 fale
     * @param time 日期字符串
     * @param pattern 指定格式
     * @return 是返回 true，否则返回 false
     */
    public static boolean isDateString(String time, String pattern) {
        return parse(time, pattern) != null;
    }

    /**
     * 判断是否有交集
     */
    public static boolean isIntersection(Date start_time, Date end_time, Date sTime, Date eTime) {

        boolean state = true;

        if (start_time == null || end_time == null || sTime == null) {
            state = false;
        }

        if (start_time != null && sTime != null && end_time != null) {
            if (start_time.getTime() < sTime.getTime() && end_time.getTime() < sTime.getTime()) {
                state = false;
            }
        }

        if (start_time != null && eTime != null && end_time != null) {
            if (start_time.getTime() > eTime.getTime() && end_time.getTime() > eTime.getTime()) {
                state = false;
            }
        }

        return state;
    }

    /**
     * @param start_time --> A
     * @param end_time   --> B
     * @param sTime      --> C
     * @param eTime      --> D
     * @return 交际的类型(时间轴)
     * <p/>
     * 1.  为 C_A_D_B
     * 2.  为 A_C_B_D
     * 3.  为 A_C_D_B
     * 4.  为 C_A_B_D
     * 0   为 无交集
     */
    public static int getIntersectionType(Date start_time, Date end_time, Date sTime, Date eTime) {

        if (!isIntersection(start_time, end_time, sTime, eTime))
            return 0;

        long a = start_time.getTime();
        long b = end_time.getTime();
        long c = sTime.getTime();
        long d = eTime.getTime();

        //开始结束时间错误
        if (a > b || c > d)
            return 0;

        //以下代码存在包含关系，判断顺序不能改变

        if (a < c && b > d)
            return A_C_D_B;

        if (a > c && b < d)
            return C_A_B_D;

        if (a > c & a < d)
            return C_A_D_B;

        if (a < c && b > c)
            return A_C_B_D;

        return 0;
    }


    /**
     * 根据日期和指定天数，判断指定日期是否在当前日期之后
     *
     * @param date 要比较的日期
     * @param day  要比较日期的几天前或前天后，可以是负数
     * @return true|false
     */
    public static boolean isDateAfter(Date date, int day) {

        // 获取当前系统日期
        Date currentDate = new Date();

        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        // 将当前日期设置到日历
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, day);

        currentDate = calendar.getTime();

        return date.after(currentDate);
    }

    /**
     * 获取指定日期前几天
     *
     * @param date 指定日期
     * @param num  前1天还是几天？指定
     * @return 符合的指定日期
     */
    public static String getDateBefore(String date, int num) {
        Calendar c = Calendar.getInstance();
        Date tempDate = parse(date, "yyyy-MM-dd");
        if (tempDate != null) {
            c.setTime(tempDate);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day - num);

            return format(c.getTime(), "yyyy-MM-dd");
        }
        return null;
    }


    /**
     * 获取指定日期后几天
     *
     * @param date 指定日期
     * @param num  后1天还是几天？指定
     * @return 符合的指定日期
     */
    public static String getDateAfter(String date, int num) {
        Calendar c = Calendar.getInstance();
        Date tempDate = parse(date, "yyyy-MM-dd");
        if (tempDate != null) {
            c.setTime(tempDate);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day + num);

            return format(c.getTime(), "yyyy-MM-dd");
        }
        return null;
    }

}
