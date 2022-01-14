package cn.bensun.elasticsearch.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname BaseAreaTimeEnum
 * @Description 区域时间枚举
 * @Date 2021/5/24 17:11:51
 * @Created by weizongtang
 */
public interface AreaTimeEnum {

    /**
     * @Description 地区时间
     * @CreatedBy weizongtang
     * @CreateTime 2021/11/19 16:44:53
     */
    @Getter
    @AllArgsConstructor
    enum AreaTimeZone {
        YD("GMT+5:30", "印度时间"),
        CN("GMT+8:00", "中国时间"),
        EN("GMT+0", "UTC时间");
        private String timeZone;

        private String desc;

        /**
         * @Description 获取时间(指定地区)
         * @CreatedBy weizongtang
         * @CreateTime 2021/11/19 16:53:12
         */
        public Calendar getAreaTime() {
            //当前地区时间偏移
            int localRawOffset = Calendar.getInstance().getTimeZone().getRawOffset();
            //目标地区时间偏移
            int targetRawOffset = TimeZone.getTimeZone(timeZone).getRawOffset();
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
            instance.set(Calendar.MILLISECOND, instance.get(Calendar.MILLISECOND) - localRawOffset + targetRawOffset);
            return instance;
        }

        /**
         * @Description 获取时间偏移
         * @CreatedBy weizongtang
         * @CreateTime 2022/01/05 15:55:35
         */
        public int getRawOffset() {
            //当前地区时间偏移
            int localRawOffset = Calendar.getInstance().getTimeZone().getRawOffset();
            //目标地区时间偏移
            int targetRawOffset = TimeZone.getTimeZone(timeZone).getRawOffset();
            return localRawOffset - targetRawOffset;
        }

        /**
         * @Description 获取格式化后的时间
         * @CreatedBy weizongtang
         * @CreateTime 2022/01/05 15:44:43
         */
        public String format(FieldFormat fieldFormat, Map<Integer, Integer> addTime) {
            Calendar calendar = getAreaTime();
            if (ObjectUtil.isNotEmpty(addTime)) {
                addTime.forEach((k, v) -> {
                    calendar.set(k, calendar.get(k) + v);
                });
            }
            Date calendarTime = calendar.getTime();
            return fieldFormat.pattern.format(calendarTime);
        }

        /**
         * @Description 获取当前时间
         * @CreatedBy weizongtang
         * @CreateTime 2022/01/05 15:44:53
         */
        public Calendar getCurrentTime(Map<Integer, Integer> addTime) {
            Calendar calendar = getAreaTime();
            if (ObjectUtil.isNotEmpty(addTime)) {
                addTime.forEach((k, v) -> {
                    calendar.set(k, calendar.get(k) + v);
                });
            }
            return calendar;
        }

        /**
         * @Description 格式化时间
         * @CreatedBy weizongtang
         * @CreateTime 2021/08/22 11:08:29
         */
        public Date getCalendar(Date date, FieldFormat fieldFormat) {
            try {
                String format = fieldFormat.pattern.format(date);
                Date parse = fieldFormat.pattern.parse(format);
                return new Date(parse.getTime() + YD.getRawOffset());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }


        public static void main(String[] args) throws ParseException {
            Date calendar = AreaTimeZone.YD.getCalendar(new Date(), FieldFormat.TIME_PATTERN_START);
            System.out.println(calendar.getTime());

        }
    }

    /**
     * @Description 字段格式化专用
     * @CreatedBy weizongtang
     * @CreateTime 2021/11/19 16:45:10
     */
    @Getter
    @AllArgsConstructor
    enum FieldFormat {
        DAY_PATTERN(new SimpleDateFormat("yyyy-MM-dd")),
        TIME_PATTERN(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),
        TIME_PATTERN_START(new SimpleDateFormat("yyyy-MM-dd 00:00:00")),
        SAVE_FILE_TIME_PATTERN(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")),
        WEB_TIME_PATTERN(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US)),//icicibank 订单时间 02/11/2021 05:03:54 PM
        WEB_DATA_PATTERN(new SimpleDateFormat("dd/MM/yyyy", Locale.US));//icicibank 订单日期 02/11/2021
        private SimpleDateFormat pattern;
    }

}
