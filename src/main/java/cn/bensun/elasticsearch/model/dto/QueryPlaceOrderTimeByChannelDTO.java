package cn.bensun.elasticsearch.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 最近下单时间(通道)
 * @CreatedBy weizongtang
 * @CreateTime 2022/03/11 14:17:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryPlaceOrderTimeByChannelDTO {
    @ApiModelProperty(value = "最近下单时间")
    private Long placeOrderTime;
    @ApiModelProperty(value = "支付通道(通道的标识key)")
    private String payChannel;
}
