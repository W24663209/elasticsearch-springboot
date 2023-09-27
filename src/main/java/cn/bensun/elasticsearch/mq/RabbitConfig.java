package cn.bensun.elasticsearch.mq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public final static String ALL_LOG = "ALL_LOG";
    public final static String LOG_FACTORY = "LOG_FACTORY";

    @Bean(LOG_FACTORY)
    public SimpleRabbitListenerContainerFactory logFactoryLoan(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(50);
        factory.setConcurrentConsumers(200);
        return factory;
    }
}
