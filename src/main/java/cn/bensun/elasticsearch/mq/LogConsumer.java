package cn.bensun.elasticsearch.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {

    @RabbitListener(queues = RabbitConfig.ALL_LOG, containerFactory = RabbitConfig.LOG_FACTORY)
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
        // 处理收到的消息逻辑
    }
}
