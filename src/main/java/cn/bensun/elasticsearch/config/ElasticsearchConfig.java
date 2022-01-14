package cn.bensun.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

/**
 * @author uf801862 2021-11-22 10:57:14
 * <p>
 * ElasticsearchTemplate初始化时，持久化了一个ResultsMapper对象，
 * 该对象中的EntityMapper负责对象数据的json化等工作，
 * 而EntityMapper实现有两，
 * DefaultEntityMapper和ElasticsearchEntityMapper(从3.2版本后引入)，
 * 而平台初始化默认用的是DefaultEntityMapper实现。
 * elasticsearch版本从3.2以后版本才加入了@Field的name属性，引入自带的字段别名映射能力，
 * 而ElasticsearchEntityMapper正是用于解决该问题，因此，需要自定义注入ElasticsearchTemplate的bean对象
 */
@Configuration
public class ElasticsearchConfig {

    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate(RestHighLevelClient client, ElasticsearchConverter converter) {
        try {
            return new ElasticsearchRestTemplate(client,
                    new ElasticsearchEntityMapper(converter.getMappingContext(), null));
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}