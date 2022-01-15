package cn.bensun.elasticsearch.controller;


import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
