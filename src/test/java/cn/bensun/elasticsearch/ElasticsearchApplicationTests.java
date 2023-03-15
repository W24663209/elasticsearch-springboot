package cn.bensun.elasticsearch;

import cn.bensun.elasticsearch.domain.Collection;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ElasticsearchApplication.class)
class ElasticsearchApplicationTests {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private RestClient restClient;
//    @Autowired
//    private TestRepository testRepository;


    @Test
    void contextLoads() throws IOException {
//        Optional<PolymerPaymentOrderPO> byId = polymerTempOrderRepository.findById(6014099799024369L);
//        polymerTempOrderRepository.deleteById(6014099799024369L);
//        PolymerPaymentOrderPO polymerTempOrderPO = polymerTempOrderRepository.findByOtcOrderCode("PSO2021120918301104330425");
//        System.out.println(JSON.toJSONString(polymerTempOrderPO));
//        polymerTempOrderPO = polymerTempOrderRepository.findByOtcOrderCode("PSO2021120918304178930780");
//        System.out.println(JSON.toJSONString(polymerTempOrderPO));
//        PolymerPaymentOrderPO polymerTempOrderPO = new PolymerPaymentOrderPO();
//        polymerTempOrderPO.setOtcOrderCode("PSO2021120918305500030822");
//        polymerTempOrderPO.setMerchantOrderNo("421412412412");
//        polymerTempOrderRepository.save(polymerTempOrderPO);
//        polymerTempOrderPO = polymerTempOrderRepository.findByOtcOrderCode("PSO2021120918304028930697");
//        System.out.println(JSON.toJSONString(polymerTempOrderPO));
//        Optional<PolymerPaymentOrderPO> byId = polymerTempOrderRepository.findById("QkQ2-n0B5WMkqp3T8z4v");
//        System.out.println(JSON.toJSONString(byId.get()));
//        testRepository.save(TestPO.builder().name("haha").age(100).id("131238912").build());
//        testRepository.deleteById("131238912");
//        polymerTempOrderRepository.save(PolymerPaymentOrderPO.builder().id(43235436456L).merchantOrderNo("hehe").otcOrderCode("grdsfsdjfksd").build());
//        polymerTempOrderMapper.testSql();
//        insert();
//        List<QueryOrderNumberByDayDTO> queryOrderNumberByDayDTOS = polymerTempOrderMapper.queryOrderNumberByDay();
//        System.out.println(queryOrderNumberByDayDTOS);
    }



    @Test
    public void insert() throws IOException {
        elasticsearchRestTemplate.createIndex(Collection.class);
//        elasticsearchRestTemplate.createIndex(Collection.class);
    }

}
