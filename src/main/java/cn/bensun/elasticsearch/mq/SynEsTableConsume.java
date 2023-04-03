package cn.bensun.elasticsearch.mq;

import cn.bensun.elasticsearch.domain.Collection;
import cn.bensun.elasticsearch.domain.Payment;
import cn.bensun.elasticsearch.domain.SyncEsTable;
import cn.bensun.elasticsearch.enums.RocketMQTopicConstants;
import cn.bensun.elasticsearch.enums.SqlOperateEnum;
import cn.bensun.elasticsearch.mapper.CollectionRepository;
import cn.bensun.elasticsearch.mapper.PaymentRepository;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 同步到数据库
 * @author weizongtang
 * @CreateTime 2023/04/03 13:34:10
 */
@Component
@Slf4j
public class SynEsTableConsume {


    /**
     * @author weizongtang
     * @Description
     * @CreateTime 2023/03/11 19:06:09
     */
    @Service
    @RocketMQMessageListener(topic = RocketMQTopicConstants.同步es主题, consumerGroup = RocketMQTopicConstants.同步es订阅组)
    public class OrderPayOutTopic implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
        @Override
        public void prepareStart(DefaultMQPushConsumer consumer) {

        }

        @Autowired
        private CollectionRepository collectionRepository;

        @Autowired
        private PaymentRepository paymentRepository;

        @SneakyThrows
        @Override
        public void onMessage(String str) {
            log.info("开始同步es\t{}", str);
            JSONObject jsonObject = JSONObject.parseObject(str);
            SyncEsTable syncEsTable = JSON.to(SyncEsTable.class, jsonObject);
            JSONArray data = jsonObject.getJSONArray("data");
            if (syncEsTable.getTable().equalsIgnoreCase("t_collection")){
                List<Collection> collections = com.alibaba.fastjson.JSONArray.parseArray(data.toJSONString(), Collection.class);
                syncEsTable.setDate(collections);
                if (SqlOperateEnum.删除.getCode().equalsIgnoreCase(syncEsTable.getType())){
                    for (Collection collection : collections) {
                        collectionRepository.delete(collection);
                    }
                }else{
                    for (Collection collection : collections) {
                        collectionRepository.save(collection);
                    }
                }
            }else if (syncEsTable.getTable().equalsIgnoreCase("t_payment")){
                List<Payment> payments = com.alibaba.fastjson.JSONArray.parseArray(data.toJSONString(), Payment.class);
                syncEsTable.setDate(payments);
                if (SqlOperateEnum.删除.getCode().equalsIgnoreCase(syncEsTable.getType())){
                    for (Payment payment : payments) {
                        paymentRepository.delete(payment);
                    }
                }else {
                    for (Payment payment : payments) {
                        paymentRepository.save(payment);
                    }
                }
            }
            log.info("完成同步es");
        }
    }
}
