package cn.bensun.elasticsearch.mapper;

import cn.bensun.elasticsearch.domain.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description 代收正式订单-es
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:58
 */
public interface CollectionRepository extends MongoRepository<Collection, String> {

}
