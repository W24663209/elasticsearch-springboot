package cn.bensun.elasticsearch.model.vo.requestLog;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 请求记录
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/14 20:34:45
 */
@Data
public class SaveRequestVO implements Serializable {
    private String orderNo;
    private Long merchantId;
    private String url;
    private String req;
    private String res;
}
