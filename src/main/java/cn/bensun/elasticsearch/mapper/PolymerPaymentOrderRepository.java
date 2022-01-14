package cn.bensun.elasticsearch.mapper;

import cn.bensun.elasticsearch.model.po.PolymerPaymentOrderPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description 代收临时订单-es
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:58
 */
public interface PolymerPaymentOrderRepository extends ElasticsearchRepository<PolymerPaymentOrderPO, String> {

}
