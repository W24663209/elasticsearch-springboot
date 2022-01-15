package cn.bensun.elasticsearch.service;

import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.vo.requestLog.SaveRequestVO;

import java.util.Map;

/**
 * @Description 保存请求日志
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/14 20:36:52
 */
public interface SaveRequestLogService {
    /**
     * @Description 保存请求日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/14 20:38:56
     */
    Result save(SaveRequestVO req);
}
