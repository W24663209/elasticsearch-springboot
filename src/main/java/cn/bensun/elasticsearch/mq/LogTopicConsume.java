package cn.bensun.elasticsearch.mq;

import cn.bensun.elasticsearch.domain.LogMsg;
import cn.bensun.elasticsearch.enums.RocketMQTopicConstants;
import cn.bensun.elasticsearch.mapper.LogMsgRepository;
import cn.bensun.elasticsearch.util.DateUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author weizongtang
 * @Description 日志主题
 * @CreateTime 2023/04/03 13:33:55
 */
@Component
@Slf4j
public class LogTopicConsume {


    @Autowired
    private LogMsgRepository logMsgRepository;
    /**
     * @author weizongtang
     * @Description
     * @CreateTime 2023/04/03 13:34:48
     */
    @Service
    @RocketMQMessageListener(topic = RocketMQTopicConstants.日志主题, consumerGroup = RocketMQTopicConstants.日志主题订阅组)
    public class LogTopic implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
        @Override
        public void prepareStart(DefaultMQPushConsumer consumer) {

        }

        @Override
        public void onMessage(String str) {
            log.info("日志主题\t{}", str);
            LogMsg logMsg = null;
            try {
                logMsg = JSON.parseObject(str, LogMsg.class);
                String createTime = JSON.parseObject(str).getString("create_time");
                if (createTime != null) {
                    logMsg.setCreatedTime(DateUtil.str2Time(createTime).getTime());
                }
                logMsgRepository.save(logMsg);
            } catch (Exception e) {

            }
        }
    }
}
