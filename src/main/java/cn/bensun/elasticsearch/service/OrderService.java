package cn.bensun.elasticsearch.service;

import cn.bensun.elasticsearch.model.dto.Result;

import java.util.List;

public interface OrderService {
    /**
     * @Description 商户订单比例
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/01 15:16:46
     * @param ids
     */
    Result queryMerchantProportion(List<Long> ids);
}
