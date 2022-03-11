package cn.bensun.elasticsearch.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 最近下单时间(通道)
 * @CreatedBy weizongtang
 * @CreateTime 2022/03/11 14:17:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryPlaceOrderTimeByChannelVO {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "支付通道(通道的标识key)")
    private List<String> payChannel;
}
