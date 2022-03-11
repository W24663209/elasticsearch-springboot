package cn.bensun.elasticsearch.service;

import cn.bensun.elasticsearch.model.dto.QueryPlaceOrderTimeByChannelDTO;
import cn.bensun.elasticsearch.model.dto.QueryPlaceOrderTimeDTO;
import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.vo.QueryPlaceOrderTimeByChannelVO;

import java.util.List;

public interface OrderService {
    /**
     * @Description 商户订单比例
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/01 15:16:46
     * @param ids
     */
    Result queryMerchantProportion(List<Long> ids);

    /**
     * @Description 最近下单时间(集合)
     * @CreatedBy weizongtang
     * @CreateTime 2022/02/10 14:18:01
     */
    List<QueryPlaceOrderTimeDTO> queryPlaceOrderTimeList(List<Long> userIds);

    /**
     * @Description 最近下单时间(集合)通道
     * @CreatedBy weizongtang
     * @CreateTime 2022/03/11 14:20:52
     */
    List<QueryPlaceOrderTimeByChannelDTO> queryPlaceOrderTimeByChannelList(QueryPlaceOrderTimeByChannelVO list);
}
