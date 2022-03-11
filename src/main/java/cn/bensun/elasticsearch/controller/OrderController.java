package cn.bensun.elasticsearch.controller;


import cn.bensun.elasticsearch.model.dto.QueryPlaceOrderTimeByChannelDTO;
import cn.bensun.elasticsearch.model.dto.QueryPlaceOrderTimeDTO;
import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.vo.QueryPlaceOrderTimeByChannelVO;
import cn.bensun.elasticsearch.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 查询订单
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/01 14:54:13
 */
@RestController
@RequestMapping("/polymerOrder/query")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @Description 商户交易成功率
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/01 14:54:25
     */
    @PostMapping("/merchantProportion")
    @ApiOperation(value = "商户交易成功率")
    public Result queryMerchantProportion(@RequestBody List<Long> ids) {
        return orderService.queryMerchantProportion(ids);
    }

    /**
     * @Description 最近下单时间(集合)
     * @CreatedBy weizongtang
     * @CreateTime 2022/02/10 14:16:34
     */
    @PostMapping("/queryPlaceOrderTimeList")
    @ApiOperation(value = "最近下单时间(集合)")
    public List<QueryPlaceOrderTimeDTO> queryPlaceOrderTimeList(@RequestBody List<Long> userIds) {
        return orderService.queryPlaceOrderTimeList(userIds);
    }

    /**
     * @Description 最近下单时间(集合)通道
     * @CreatedBy weizongtang
     * @CreateTime 2022/03/11 14:16:12
     */
    @PostMapping("/queryPlaceOrderTimeByChannelList")
    @ApiOperation(value = "最近下单时间(集合)通道")
    public List<QueryPlaceOrderTimeByChannelDTO> queryPlaceOrderTimeByChannelList(@RequestBody QueryPlaceOrderTimeByChannelVO vo) {
        return orderService.queryPlaceOrderTimeByChannelList(vo);
    }

}
