package cn.bensun.elasticsearch.mapper;

import cn.bensun.elasticsearch.model.po.SaveRequestPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description 保存请求日志
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:58
 */
public interface SaveRequestRepository extends ElasticsearchRepository<SaveRequestPO, String> {

}
