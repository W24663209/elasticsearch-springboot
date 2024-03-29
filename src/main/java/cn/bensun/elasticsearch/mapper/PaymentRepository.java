package cn.bensun.elasticsearch.mapper;

import cn.bensun.elasticsearch.domain.Payment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description 代收正式订单-es
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:58
 */
public interface PaymentRepository extends ElasticsearchRepository<Payment, String> {

}
