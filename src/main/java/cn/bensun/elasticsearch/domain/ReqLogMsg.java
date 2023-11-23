package cn.bensun.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "t_req_log_msg", type = "t_req_log_msg")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqLogMsg extends BaseEntity {
    @Id
    private String id;
    @Field(name = "created_time", value = "created_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long createdTime;//创建时间
    @Field(name = "deviceName", value = "deviceName", type = FieldType.Text, fielddata = true)
    private String deviceName;
    @Field(name = "method", value = "method", type = FieldType.Text, fielddata = true)
    private String method;
    @Field(name = "request_url", value = "request_url", type = FieldType.Text, fielddata = true)
    private String requestUrl;
    @Field(name = "request_method", value = "request_method", type = FieldType.Text, fielddata = true)
    private String requestMethod;
    @Field(name = "request_body", value = "request_body", type = FieldType.Text, fielddata = true)
    private String requestBody;
    @Field(name = "response_body", value = "response_body", type = FieldType.Text, fielddata = true)
    private String responseBody;

}
