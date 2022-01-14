package cn.bensun.elasticsearch.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryMerchantProportionDTO implements Serializable {
    @ApiModelProperty(value = "商家id")
    private Long merchantId;
    @ApiModelProperty(value = "今日成功率")
    private BigDecimal todayPay;
    @ApiModelProperty(value = "昨日成功率")
    private BigDecimal yesterday;
    @ApiModelProperty(value = "近7日成功率")
    private BigDecimal sevenDaysBefore;

}
