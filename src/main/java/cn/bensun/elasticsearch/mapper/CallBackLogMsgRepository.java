package cn.bensun.elasticsearch.mapper;

import cn.bensun.elasticsearch.domain.CallBackLogMsg;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description 回调日志-es
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:58
 */
public interface CallBackLogMsgRepository extends ElasticsearchRepository<CallBackLogMsg, String> {

}
