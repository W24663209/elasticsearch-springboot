package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.domain.Collection;
import cn.bensun.elasticsearch.mapper.CollectionRepository;
import cn.bensun.elasticsearch.mapper.sql.CollectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    public BigDecimal test() {
        Pageable pageable=new Pageable() {
            @Override
            public int getPageNumber() {
                return 10;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return Sort.by("created_time");
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        Collection collection = collectionRepository.findById("0000027AAAE848748D1C12AC17DB3EB4").orElse(null);
        System.out.println(collection);
        Double test = collectionMapper.test();
        return BigDecimal.valueOf(test);
    }
}