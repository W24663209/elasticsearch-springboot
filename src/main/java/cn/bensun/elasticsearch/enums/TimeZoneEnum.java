package cn.bensun.elasticsearch.enums;

import lombok.Getter;

import java.util.TimeZone;

/**
 * @author weizongtang
 * @Description 时区
 * @CreateTime 2023/03/17 13:34:28
 */
@Getter
public enum TimeZoneEnum {
    中国(TimeZone.getTimeZone("Asia/Shanghai")),
    印度(TimeZone.getTimeZone("Asia/Kolkata")),
    ;
    private TimeZone timeZone;

    TimeZoneEnum(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}
