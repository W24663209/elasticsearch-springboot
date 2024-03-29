package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.domain.Payment;
import cn.bensun.elasticsearch.domain.TableDataInfo;
import cn.bensun.elasticsearch.util.SearchRequestUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/03/14 18:45:19
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    /**
     * @Description 查询代收列表
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/14 18:45:16
     */
    @GetMapping("/list")
    @ApiOperation("查询代收列表")
    public TableDataInfo list(Payment payment) throws Exception {
        return SearchRequestUtil.searchList(Payment.class, payment);
    }
}