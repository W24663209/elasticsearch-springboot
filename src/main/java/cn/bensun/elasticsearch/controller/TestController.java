package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.domain.Collection;
import cn.bensun.elasticsearch.enums.PayInStatusEnum;
import cn.bensun.elasticsearch.mapper.CollectionRepository;
import cn.bensun.elasticsearch.mapper.sql.CollectionMapper;
import cn.bensun.elasticsearch.util.SearchRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/03/14 18:45:19
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private CollectionRepository collectionRepository;

    /**
     * @Description
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/14 18:45:16
     */
    @GetMapping("/test")
    public Object test() throws Exception {

        Collection collection = new Collection();
//        collection.setSearchStartTime(1677945600000L);
//        collection.setSearchEndTime(DateUtil.timestampAdd(1677945600000L, 1));
        collection.setStatus(PayInStatusEnum.已支付.getCode());
        collection.setChannelNo("DS1112");
        return SearchRequestUtil.search(collection,Collection::getCreatedTime);
    }
}