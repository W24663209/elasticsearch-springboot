package cn.bensun.elasticsearch.mapper;

import cn.bensun.elasticsearch.domain.LogMsg;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description 请求日志-es
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:58
 */
public interface ReqLogMsgRepository extends ElasticsearchRepository<LogMsg, String> {

}
