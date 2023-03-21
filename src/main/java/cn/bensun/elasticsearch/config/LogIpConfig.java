package cn.bensun.elasticsearch.config;
 
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description
 * @author weizongtang
 * @CreateTime 2023/03/04 13:50:10
 */
public class LogIpConfig extends ClassicConverter {
 private static final Logger logger = LoggerFactory.getLogger(LogIpConfig .class);
    private static String webIP;
    static {
        try {
            webIP = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            logger.error("获取日志Ip异常", e);
            webIP = null;
        }
    }
 
    @Override
    public String convert(ILoggingEvent event) {
        return webIP;
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getLocalHost().getHostName());
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
    }
}