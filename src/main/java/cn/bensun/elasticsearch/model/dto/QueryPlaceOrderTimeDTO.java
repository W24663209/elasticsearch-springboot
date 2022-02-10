package cn.bensun.elasticsearch.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 最近下单时间
 * @CreatedBy weizongtang
 * @CreateTime 2022/02/10 18:48:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryPlaceOrderTimeDTO {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "最近下单时间")
    private Long placeOrderTime;
}
