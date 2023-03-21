package cn.bensun.elasticsearch.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @Description
 * @author weizongtang
 * @CreateTime 2023/03/04 13:49:59
 */
public class ThreadIdConfig extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
//        return ThreadLocalUtil.get();
        return null;
    }
}