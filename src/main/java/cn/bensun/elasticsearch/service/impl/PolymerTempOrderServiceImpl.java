package cn.bensun.elasticsearch.service.impl;

import cn.bensun.elasticsearch.mapper.PolymerTempOrderRepository;
import cn.bensun.elasticsearch.mapper.sql.PolymerTempOrderMapper;
import cn.bensun.elasticsearch.service.PolymerTempOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代收临时订单
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 20:19:19
 */
@Service
public class PolymerTempOrderServiceImpl implements PolymerTempOrderService {
    @Autowired
    private PolymerTempOrderMapper polymerTempOrderMapper;

    @Autowired
    private PolymerTempOrderRepository polymerTempOrderRepository;


}
