package cn.bensun.elasticsearch.mapper;

import cn.bensun.elasticsearch.model.po.PolymerInsteadOrderPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description 代收正式订单-es
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:58
 */
public interface PolymerInsteadOrderRepository extends ElasticsearchRepository<PolymerInsteadOrderPO, String> {

}
