package cn.bensun.elasticsearch.mq;

import cn.bensun.elasticsearch.domain.LogMsg;
import cn.bensun.elasticsearch.mapper.LogMsgRepository;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {
    @Autowired
    private LogMsgRepository logMsgRepository;

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
            LogMsg logMsg = JSONObject.parseObject(message,LogMsg.class);
            logMsgRepository.save(logMsg);
        } catch (Exception e) {
        }
    }
}
