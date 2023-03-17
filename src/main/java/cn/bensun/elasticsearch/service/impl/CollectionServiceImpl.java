package cn.bensun.elasticsearch.service.impl;

import cn.bensun.elasticsearch.mapper.CollectionRepository;
import cn.bensun.elasticsearch.mapper.sql.CollectionMapper;
import cn.bensun.elasticsearch.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/03/13 17:42:45
 */
@Service
public class CollectionServiceImpl implements ICollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private CollectionRepository collectionRepository;
}
