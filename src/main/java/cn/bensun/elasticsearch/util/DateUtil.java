package cn.bensun.elasticsearch.util;

import cn.bensun.elasticsearch.enums.TimeZoneEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtil extends cn.hutool.core.date.DateUtil {
    static {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        TimeZone.setDefault(timeZone);
    }

    private DateUtil() {

    }

    /**
     * @Description 时间戳转LocalDateTime
     * @CreatedBy weizongtang
     * @CreateTime 2022/11/09 22:16:37
     */
    public static LocalDateTime timestamToDatetime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * @Description LocalDateTime转时间戳
     * @CreatedBy weizongtang
     * @CreateTime 2022/11/09 22:16:50
     */
    public static long datatimeToTimestamp(LocalDateTime ldt) {
        return ldt.toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset() / 1000)).toEpochMilli();
    }

    /**
     * @Description LocalDateTime转时间戳加减
     * @CreatedBy weizongtang
     * @CreateTime 2022/11/16 15:55:40
     */
    public static long datatimeToTimestamp(LocalDateTime ldt, long addDay) {
        return ldt.toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset() / 1000)).toEpochMilli() + (60 * 60 * 24 * 1000 * addDay);
    }

    /**
     * @Description LocalDateTime转时间戳加减
     * @CreatedBy weizongtang
     * @CreateTime 2022/11/16 15:55:40
     */
    public static long dateToTimestamp(Date ldt, long addDay) {
        return ldt.getTime() + (60 * 60 * 24 * 1000 * addDay);
    }

    /**
     * @Description LocalDateTime转时间戳加减
     * @CreatedBy weizongtang
     * @CreateTime 2022/11/16 15:55:40
     */
    public static long timestampAdd(Long ldt, long addDay) {
        return ldt + (60 * 60 * 24 * addDay);
    }

    /**
     * @Description 时间转字符串
     * @author weizongtang
     * @CreateTime 2022/12/07 13:57:14
     */
    public static String date2str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    /**
     * @Description 时间转字符串
     * @author weizongtang
     * @CreateTime 2022/12/07 13:57:14
     */
    public static String date2YMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * @Description 字符串转时间
     * @author weizongtang
     * @CreateTime 2022/12/07 13:59:08
     */
    public static Date str2date(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description 字符串转时间
     * @author weizongtang
     * @CreateTime 2022/12/07 13:59:08
     */
    public static Date str2Time(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description 添加时间, 秒
     * @author weizongtang
     * @CreateTime 2023/02/07 15:45:00
     */
    public static LocalDateTime addTime(LocalDateTime localDateTime, long seconds) {
        return localDateTime.plusSeconds(seconds);
    }


    /**
     * @Description 获取时区时间戳
     * @author weizongtang
     * @CreateTime 2023/03/17 14:17:59
     */
    public static Long strToTime(String date, TimeZoneEnum timeZoneEnum) {
        if (TimeZoneEnum.中国.equals(timeZoneEnum)) {
            Date str2date = DateUtil.str2date(String.format("%s 00:00:00", date));
            return str2date.getTime() + TimeZoneEnum.印度.getTimeZone().getRawOffset() - timeZoneEnum.getTimeZone().getRawOffset();
        } else if (TimeZoneEnum.印度.equals(timeZoneEnum)) {
            Date str2date = DateUtil.str2date(String.format("%s 00:00:00", date));
            return str2date.getTime();
        }
        return 0L;
    }

    /**
     * @Description 获取时区时间戳
     * @author weizongtang
     * @CreateTime 2023/03/17 14:17:59
     */
    public static Long timeToTime(Long date, TimeZoneEnum timeZoneEnum) {
        if (TimeZoneEnum.中国.equals(timeZoneEnum)) {
            return date + TimeZoneEnum.印度.getTimeZone().getRawOffset() - timeZoneEnum.getTimeZone().getRawOffset();
        } else if (TimeZoneEnum.印度.equals(timeZoneEnum)) {
            return date;
        }
        return 0L;
    }

    /**
     * @Description 获取最近30天
     * @author weizongtang
     * @CreateTime 2023/03/17 14:27:04
     */
    public static List<Long> getRecentMonth() {
        List<Long> list = new ArrayList<>();
        String date2YMD = date2YMD(new Date());
        Date date = str2date(date2YMD);
        for (int i = 0; i < 31; i++) {
            list.add(dateToTimestamp(date, -i));
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(str2Time("2021-01-01 00:00:00.425").getTime());
    }
}
