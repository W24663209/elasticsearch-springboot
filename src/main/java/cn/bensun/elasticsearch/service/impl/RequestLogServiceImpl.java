package cn.bensun.elasticsearch.service.impl;

import cn.bensun.elasticsearch.mapper.RequestLogRepository;
import cn.bensun.elasticsearch.mapper.sql.RequestLogMapper;
import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.dto.requestLog.QueryRequestLogDTO;
import cn.bensun.elasticsearch.model.po.RequestLogPO;
import cn.bensun.elasticsearch.model.vo.requestLog.QueryRequestLogVO;
import cn.bensun.elasticsearch.model.vo.requestLog.SaveRequestVO;
import cn.bensun.elasticsearch.service.RequestLogService;
import cn.bensun.elasticsearch.util.ResultUtil;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private RequestLogMapper requestLogMapper;

    @Autowired
    private RequestLogRepository requestLogRepository;

    /**
     * @Description 保存请求日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/14 20:38:56
     */
    @Override
    public Result save(SaveRequestVO vo) {
        RequestLogPO requestLogPO = RequestLogPO.builder().build();
        requestLogPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        BeanUtil.copyProperties(vo, requestLogPO);
        requestLogRepository.save(requestLogPO);
        return ResultUtil.success();
    }

    /**
     * @param vo
     * @Description 查询请求日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/15 18:33:11
     */
    @Override
    public List<QueryRequestLogDTO> queryList(QueryRequestLogVO vo) {
        return requestLogMapper.queryList(vo);
    }
}
