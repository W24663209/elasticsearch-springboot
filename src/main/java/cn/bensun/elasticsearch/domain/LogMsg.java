package cn.bensun.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/04/03 16:11:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "t_log_msg")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogMsg extends BaseEntity {
    @Id
    private String id;

    @Field(name = "requestId", value = "requestId", targetType = FieldType.STRING)
    private String requestId;
    @Field(name = "msg", value = "msg", targetType = FieldType.STRING)
    private String msg;

    @Field(name = "created_time", value = "created_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long createdTime;//创建时间
}