package cn.bensun.elasticsearch.model.dto.requestLog;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/15 18:42:34
 */
@Data
public class QueryRequestLogDTO implements Serializable {
    private String orderNo;

    private Long merchantId;

    private String url;

    private String req;

    private String res;

    private Timestamp createTime;
}
