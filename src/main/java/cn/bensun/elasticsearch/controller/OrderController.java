package cn.bensun.elasticsearch.controller;


import cn.bensun.elasticsearch.model.dto.Result;
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
     * @Description 最近下单时间
     * @CreatedBy weizongtang
     * @CreateTime 2022/02/09 20:05:51
     */
    @PostMapping("/queryPlaceOrderTime/{userId}")
    @ApiOperation(value = "queryPlaceOrderTime")
    public Result queryPlaceOrderTime(@PathVariable Long userId) {
        return orderService.queryPlaceOrderTime(userId);
    }
}
