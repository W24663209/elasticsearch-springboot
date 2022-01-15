package cn.bensun.elasticsearch.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.sql.Timestamp;

/**
 * @Description 保存请求日志
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/15 14:00:55
 */
@Data
@Document(indexName = "t_request_log", type = "_doc")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogPO {
    @Id
    private String orderNo;

    @Field(name = "merchantId", value = "merchantId")
    private Long merchantId;

    @Field(name = "url", value = "url")
    private String url;

    @Field(name = "req", value = "req")
    private String req;

    @Field(name = "res", value = "res")
    private String res;

    @Field(name = "createTime", value = "createTime")
    private Timestamp createTime;

}
