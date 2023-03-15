package cn.bensun.elasticsearch.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil extends cn.hutool.core.date.DateUtil {
    static {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        TimeZone.setDefault(timeZone);
    }

    private DateUtil() {

    }

    /**
     * @Description 时间转字符串
     * @author weizongtang
     * @CreateTime 2022/12/07 13:57:14
     */
    public static String date2YMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        return sdf.format(date);
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
     * @Description 添加时间, 秒
     * @author weizongtang
     * @CreateTime 2023/02/07 15:45:00
     */
    public static LocalDateTime addTime(LocalDateTime localDateTime, long seconds) {
        return localDateTime.plusSeconds(seconds);
    }


    public static void main(String[] args) {
        System.out.println(timestamToDatetime(1669896029000L));
    }

}
