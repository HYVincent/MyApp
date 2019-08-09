package com.common.mylibrary.util;

import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Administrator Vincent
 * @version v1.0
 * @name SmartNecklace
 * @page com.common.mylibrary.util
 * @class describe
 * @date 2018/6/25 23:33
 */
public class DateUtil {

    private static final String TAG = "日期工具类";
    // 1分钟
    public final static long minute = 60 * 1000;
    /**
     * 1小时
     */
    public final static long hour = 60 * minute;
    // 1天
    public final static long day = 24 * hour;
    // 月
    public final static long month = 31 * day;
    // 年
    public final static long year = 12 * month;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 时间格式
     */
    public final static String DATE_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public final static String DATA_FORMAT_ALL_2 = "yyyy年MM月dd日 HH:mm:ss";
    public final static String DATE_FORMAT_HMS = "HH:mm";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY2 = "yyyyMMdd";
    public final static String DATA_FORMAT_YEAR_MONTH_DAY2 = "yyyy年MM月dd日";
    public final static String DATA_FORMAT_YEAR_MONTH_DAY3 = "yyyy年MM月";
    public final static String DATE_FORMAT_MONTH_DAY_2 = "MM/dd";
    public final static String DATA_FORMAT_YEAR = "yyyy";
    public final static String DATA_FORMAT_MONTH = "MM月";
    public final static String DATA_FORMAT_HOUR = "HH";
    public final static String DATA_FORMAT_MIN = "mm";
    public final static String DATA_FORMAT_S = "ss";
    public final static String DATA_FORMAT_DAY = "dd";

    /**
     * 格式化时间
     *
     * @param format 传入的格式
     * @param time   时间戳
     * @return
     */
    public static String getDateString(String format, long time) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date d = new Date(time);
        return sf.format(d);
    }

    public static long getDatelong(String format, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date d = sdf.parse(date);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static String getCurrentTime() {
        Date d = new Date();
        System.out.println(d);
        String dateNowStr = sdf.format(d);
//        System.out.println("格式化后的日期：" + dateNowStr);
        return dateNowStr;
    }

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    public static long getTime(int year, int month, int day, int hour, int min, int sec) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(year) + "-");
        stringBuffer.append(String.valueOf(month) + "-");
        stringBuffer.append(String.valueOf(day) + " ");
        stringBuffer.append(String.valueOf(hour) + ":");
        stringBuffer.append(String.valueOf(min) + ":");
        stringBuffer.append(String.valueOf(sec));
        return getDatelong(DATE_FORMAT_ALL, stringBuffer.toString());
    }

    /**
     * 获取今天零点零分零秒的毫秒数
     *
     * @return
     */
    public static long getTodayZeroTimestamp() {
        long current = System.currentTimeMillis();//当前时间毫秒数
        return current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }


    /**
     * 根据 年、月 获取对应的月份 的 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText2(Date date) {
        if (date == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - date.getTime();
        long r = 0;
        if (diff > month) {
            r = (diff / month);
            return getDateString(DATE_FORMAT_ALL, date.getTime());
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 获取当前小时
     *
     * @return
     */
    public static int getCurrentHour() {
        return Integer.valueOf(getDateString(DATA_FORMAT_HOUR, System.currentTimeMillis()));
    }

    /**
     * 获取当前分钟
     *
     * @return
     */
    public static int getCurrentMin() {
        return Integer.valueOf(getDateString(DATA_FORMAT_MIN, System.currentTimeMillis()));
    }

    /**
     * 根据传入的年月日获取时间戳
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long getTime(int year, int month, int day) {
        StringBuffer date = new StringBuffer();
        date.append(year);
        date.append("-");
        if (month > 9) {
            date.append(month);
        } else {
            date.append("0");
            date.append(month);
        }
        date.append("-");
        if (day > 9) {
            date.append(day);
        } else {
            date.append("0");
            date.append(day);
        }
        return getDatelong(DATE_FORMAT_YEAR_MONTH_DAY, date.toString());
    }

    /**
     * 获取当前s
     *
     * @return
     */
    public static int getCurrentS() {
        return Integer.valueOf(getDateString(DATA_FORMAT_S, System.currentTimeMillis()));
    }

    /**
     * date转为时间戳
     *
     * @param date
     * @return
     */
    public static long dateToTime(Date date) {
        return date.getTime();
    }

    /**
     * 把时间格式转为时间戳
     *
     * @param time 2018-02-27 15:25:56
     * @return
     */
    public static long strTolong(String time) {
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date date = dateFormat.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个时间之间的时间差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return String时间描述
     */
    public static String getTimeInterval(long startTime, long endTime) {
        return getTimeInterval(String.valueOf(startTime), String.valueOf(endTime));
    }

    /**
     * 计算两个时间之间的时间差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return String时间描述
     */
    public static String getTimeInterval(String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            throw new NullPointerException("startTime or endTime is null.");
        }
        long start = Long.valueOf(startTime);
        long end = Long.valueOf(endTime);
        long time = end - start;
        if (time == 0) {
            return "0s";
        }
        Log.d(TAG, "getTimeInterval: 时间差-->" + time);
        // 分别表示天，小时，分钟，秒
        long d, h, m, s;
        StringBuffer sb = new StringBuffer();
        d = time / day;
        if (d != 0) {
            sb.append(String.valueOf(d));
            sb.append("天");
        }
        long t1 = time % day;
        h = t1 / hour;
        if (h != 0) {
            sb.append(String.valueOf(h));
            sb.append("小时");
        }
        long t2 = t1 % hour;
        m = t2 / minute;
        if (m != 0) {
            sb.append(String.valueOf(m));
            sb.append("分钟");
        }
        long t3 = t2 % minute;
        s = t3 / 1000;
        if (s != 0) {
            sb.append(String.valueOf(s));
            sb.append("秒");
        }
        return sb.toString();
    }

    /**
     * 时间差
     *
     * @param ms
     * @return
     */
    public static String getTimeInterval(long ms) {
        if (ms < 1000) {
            return "1秒";
        } else if (ms > 1000 && ms < minute) {
            return String.valueOf((int) ms / 1000) + "秒";
        } else if (ms > minute && ms < hour) {
            return String.valueOf((int) ms / minute) + "分钟" + String.valueOf((int) (ms % minute) / 1000) + "秒";
        } else if (ms > hour && ms < day) {
            return String.valueOf((int) ms / hour) + "小时" + String.valueOf((int) (ms % hour) / minute) + "分钟" + String.valueOf((int) (ms % minute) / 1000) + "秒";
        } else {
            return String.valueOf((int) ms / day) + "天" + String.valueOf((int) (ms % day) / hour) + "小时" + String.valueOf((int) (ms % minute) / minute) + "分钟" + String.valueOf((int) (ms % minute) / 1000) + "秒";
        }
    }

    /**
     * 计算两个日期相差的月份数
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param pattern 日期1和日期2的日期格式
     * @return 相差的月份数
     * @throws ParseException
     */
    public static int countMonths(String date1, String date2, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        //开始日期若小月结束日期
        if (year < 0) {
            year = -year;
            return year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        }
        return year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(String formarDate, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formarDate);
        String result = format.format(today);
        Log.e(null, result);
        return result;
    }

    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }


    /**
     * 计算两个日期之间的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int dateDiff(String startDate, String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//输入日期的格式
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTime(date1);
        cal2.setTime(date2);
        double dayCount = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 3600 * 24);//从间隔毫秒变成间隔天数
        return (int) dayCount;
    }

    /**
     * 传入日期得到星期
     *
     * @param datetime yyyy-MM-dd example:2019-07-23
     * @return example:周二
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static Date getBeforeOrAfterDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();//获取日历
        calendar.setTime(date);//当date的值是当前时间，则可以不用写这段代码。
        calendar.add(Calendar.DATE, num);
        Date d = calendar.getTime();//把日历转换为Date
        return d;
    }

    /**
     * 根据String(2019-08-23)获取date
     *
     * @return
     */
    public static Date getDateForString(String date) throws Exception {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.parse(date);
    }
}
