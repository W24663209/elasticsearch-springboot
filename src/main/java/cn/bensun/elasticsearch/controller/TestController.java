package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.domain.Collection;
import cn.bensun.elasticsearch.mapper.CollectionRepository;
import cn.bensun.elasticsearch.mapper.sql.CollectionMapper;
import cn.bensun.elasticsearch.util.PageableUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String test() {
        List<Collection> collections = collectionRepository.findAll(PageableUtil.getPageable(0, 10, Collection::getCreatedTime)).toList();
        return JSONObject.toJSONString(collections);
    }
}