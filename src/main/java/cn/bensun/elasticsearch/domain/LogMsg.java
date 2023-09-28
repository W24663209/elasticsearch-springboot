package cn.bensun.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/04/03 16:11:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "t_log_msg", type = "t_log_msg")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogMsg extends BaseEntity {
    @Id
    private String id;

    @Field(name = "created_time", value = "created_time", type = FieldType.Long, fielddata = true)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long createdTime;//创建时间
    @Field(name = "requestId", value = "requestId", type = FieldType.Text, fielddata = true)
    private String requestId;
    @Field(name = "deviceName", value = "deviceName", type = FieldType.Text, fielddata = true)
    private String deviceName;
    @Field(name = "level", value = "level", type = FieldType.Text, fielddata = true)
    private String level;
    @Field(name = "method", value = "method", type = FieldType.Text, fielddata = true)
    private String method;
    @Field(name = "msg", value = "msg", type = FieldType.Text, fielddata = true)
    private String msg;
}