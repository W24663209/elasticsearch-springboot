package cn.bensun.elasticsearch.service;

import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.dto.requestLog.QueryRequestLogDTO;
import cn.bensun.elasticsearch.model.vo.requestLog.QueryRequestLogVO;
import cn.bensun.elasticsearch.model.vo.requestLog.SaveRequestVO;

import java.util.List;

/**
 * @Description 保存请求日志
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/14 20:36:52
 */
public interface RequestLogService {
    /**
     * @Description 保存请求日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/14 20:38:56
     */
    Result save(SaveRequestVO req);

    /**
     * @Description 查询请求日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/15 18:33:11
     */
    List<QueryRequestLogDTO> queryList(QueryRequestLogVO vo);
}
