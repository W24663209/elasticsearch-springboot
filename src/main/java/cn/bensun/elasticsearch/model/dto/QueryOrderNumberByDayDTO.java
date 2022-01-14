package cn.bensun.elasticsearch.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 根据时间查询订单数量
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 18:49:10
 */
@Data
public class QueryOrderNumberByDayDTO {
    @ApiModelProperty(value = "商家id")
    private Long merchantId;

    @ApiModelProperty(value = "支付通道(通道的标识key)")
    private String payChannel;

    @ApiModelProperty(value = "订单数量")
    private Integer orderNum;

}
