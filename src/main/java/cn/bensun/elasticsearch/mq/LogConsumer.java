package cn.bensun.elasticsearch.mq;

import cn.bensun.elasticsearch.domain.LogMsg;
import cn.bensun.elasticsearch.mapper.LogMsgRepository;
import cn.bensun.elasticsearch.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
@Component
public class LogConsumer {
    @Autowired
    private LogMsgRepository logMsgRepository;
    //    "requestId":"%X{requestId:-${setRequestId}}","deviceName":"${deviceName}","createdTime":"%d{yyyy-MM-dd HH:mm:ss.SSS}","level":"%-5level","method":"%logger{full} - [%method,%line]","msg":"%msg"
    private static Pattern pattern = Pattern.compile("requestId:\":");

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = RabbitConfig.ALL_LOG),
                    exchange = @Exchange(value = RabbitConfig.LOG_FACTORY),
                    key = RabbitConfig.ALL_LOG_KEY
            )
    )
    public void receiveMessage(String message) {
        // 处理收到的消息逻辑
        try {
            String[] split = message.split("====");
            LogMsg logMsg = new LogMsg();
            logMsg.setCreatedTime(Objects.requireNonNull(DateUtil.str2Time(split[2])).getTime());
            logMsg.setRequestId(split[0]);
            logMsg.setDeviceName(split[1]);
            logMsg.setLevel(split[3]);
            logMsg.setMethod(split[4]);
            logMsg.setMsg(split[5]);
            logMsgRepository.save(logMsg);
        } catch (Exception e) {
            log.error("日志异常\t{}", message);
//            throw e;
        }
    }
}
