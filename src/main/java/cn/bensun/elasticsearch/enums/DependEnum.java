package cn.bensun.elasticsearch.enums;

import cn.bensun.elasticsearch.mapper.PolymerInsteadOrderRepository;
import cn.bensun.elasticsearch.mapper.PolymerOrderRepository;
import cn.bensun.elasticsearch.mapper.PolymerPaymentOrderRepository;
import cn.bensun.elasticsearch.mapper.PolymerTempOrderRepository;
import cn.bensun.elasticsearch.mapper.sql.PolymerInsteadOrderMapper;
import cn.bensun.elasticsearch.mapper.sql.PolymerOrderMapper;
import cn.bensun.elasticsearch.mapper.sql.PolymerPaymentOrderMapper;
import cn.bensun.elasticsearch.mapper.sql.PolymerTempOrderMapper;
import cn.bensun.elasticsearch.model.po.PolymerInsteadOrderPO;
import cn.bensun.elasticsearch.model.po.PolymerOrderPO;
import cn.bensun.elasticsearch.model.po.PolymerPaymentOrderPO;
import cn.bensun.elasticsearch.model.po.PolymerTempOrderPO;
import cn.bensun.elasticsearch.service.PolymerInsteadOrderService;
import cn.bensun.elasticsearch.service.PolymerOrderService;
import cn.bensun.elasticsearch.service.PolymerPaymentOrderService;
import cn.bensun.elasticsearch.service.PolymerTempOrderService;
import cn.bensun.elasticsearch.util.SpringContextUtils;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Arrays;

/**
 * @Description 依赖
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 20:10:42
 */
public enum DependEnum {
    POLYMER_INSTEAD_ORDER("PolymerInsteadOrderPO", PolymerInsteadOrderService.class, PolymerInsteadOrderPO.class, PolymerInsteadOrderRepository.class, PolymerInsteadOrderMapper.class),
    POLYMER_PAYMENT_ORDER("PolymerPaymentOrderPO", PolymerPaymentOrderService.class, PolymerPaymentOrderPO.class, PolymerPaymentOrderRepository.class, PolymerPaymentOrderMapper.class),
    POLYMER_TEMP_ORDER("PolymerTempOrderPO", PolymerTempOrderService.class, PolymerTempOrderPO.class, PolymerTempOrderRepository.class, PolymerTempOrderMapper.class),
    POLYMER_ORDER("PolymerOrderPO", PolymerOrderService.class, PolymerOrderPO.class, PolymerOrderRepository.class, PolymerOrderMapper.class),

    ;

    private String name;
    private Class service;
    private Class po;
    private Class repository;
    private Class mapper;

    DependEnum(String name, Class service, Class po, Class repository, Class mapper) {
        this.name = name;
        this.service = service;
        this.po = po;
        this.repository = repository;
        this.mapper = mapper;
    }

    public Object getServiceObj() {
        return SpringContextUtils.getBean(this.service);
    }

    public ElasticsearchRepository getRepositoryObj() {
        return (ElasticsearchRepository) SpringContextUtils.getBean(this.repository);
    }

    public Object getMapperObj() {
        return SpringContextUtils.getBean(this.mapper);
    }

    public Class getPo() {
        return po;
    }

    public static DependEnum getInstance(String clazzName) {
        return Arrays.stream(values()).filter(e -> e.name.equals(clazzName)).findAny().orElse(null);
    }
}
