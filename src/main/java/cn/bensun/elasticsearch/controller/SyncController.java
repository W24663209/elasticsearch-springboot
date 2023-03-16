package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.mapper.PaymentRepository;
import cn.hutool.http.HttpUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weizongtang
 * @Description 同步数据
 * @CreateTime 2023/03/16 12:56:29
 */
@RestController
@RequestMapping("/sync")
@Slf4j
public class SyncController {
    private static final String host = "http://canal-adapter:8081";
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private PaymentRepository paymentRepository;


    /**
     * @Description 同步代收
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/16 12:57:13
     */
    @GetMapping("/collection/{timestamp}")
    @ApiOperation(value = "同步代收")
    public String collection(@PathVariable Long timestamp) {
        Map<String, Object> data = new HashMap<>();
        data.put("params", timestamp);
        return HttpUtil.post(host + "/etl/es6/t_collection.yml", data);
    }

    /**
     * @Description 同步代收
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/16 12:57:13
     */
    @GetMapping("/payment/{timestamp}")
    @ApiOperation(value = "同步代收")
    public String payment(@PathVariable Long timestamp) {
        Map<String, Object> data = new HashMap<>();
        data.put("params", timestamp);
        return HttpUtil.post(host + "/etl/es6/t_payment.yml", data);
    }
}
