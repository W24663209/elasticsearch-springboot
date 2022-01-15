package cn.bensun.elasticsearch.model.vo.requestLog;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 查询请求日志
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/15 18:32:08
 */
@Data
public class QueryRequestLogVO implements Serializable {
    private String orderNo;
    private Long merchantId;
}
