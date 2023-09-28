package cn.bensun.elasticsearch.mq;

import cn.bensun.elasticsearch.domain.LogMsg;
import cn.bensun.elasticsearch.mapper.LogMsgRepository;
import cn.bensun.elasticsearch.util.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
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
            JSONObject jsonObject = JSONObject.parseObject(message);
            LogMsg logMsg = new LogMsg();
            logMsg.setCreatedTime(DateUtil.str2Time(jsonObject.getString("createdTime")).getTime());
            logMsg.setRequestId(jsonObject.getString("requestId"));
            logMsg.setDeviceName(jsonObject.getString("deviceName"));
            logMsg.setLevel(jsonObject.getString("level"));
            logMsg.setMethod(jsonObject.getString("method"));
            logMsg.setMsg(jsonObject.getString("msg"));
            logMsgRepository.save(logMsg);
        } catch (Exception e) {
            log.error("日志异常\t{}",message);
        }
    }
}
