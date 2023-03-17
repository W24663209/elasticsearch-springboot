package cn.bensun.elasticsearch.res;

import cn.bensun.elasticsearch.enums.TableFieldComment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 日通道余额对象 t_channel_balance_day
 *
 * @author wzt
 * @date 2022-11-10
 */
@Data
public class ChannelBalanceDay {
    private static final long serialVersionUID = 1L;

    @TableFieldComment("主键")
    private String id;

    @TableFieldComment("通道号")
    private String channelNo;

    @TableFieldComment("交易金额")
    private BigDecimal amount;

    @TableFieldComment("退款金额")
    private BigDecimal refundAmount;

    @TableFieldComment("实际收益")
    private BigDecimal realIncome;

    @TableFieldComment("应结金额")
    private BigDecimal realSettleAmount;

    @TableFieldComment("应结金额")
    private BigDecimal shouldSettleAmount;

    @TableFieldComment("手续费")
    private BigDecimal fee;

    @TableFieldComment("通道费率")
    private BigDecimal rate;

    @TableFieldComment("单笔手续费")
    private BigDecimal singleFee;

    @TableFieldComment("数量")
    private Long count;

    @TableFieldComment("收益")
    private BigDecimal income;

    @TableFieldComment("结算状态")
    private String settleStatus;

    @TableFieldComment("结算周期;参考-PayInSettleCycleEnum")
    private String settleCycle;

    @TableFieldComment("通道类型")
    private String type;

    @TableFieldComment("时间")
    private String date;

    @TableFieldComment("创建人")
    private String createdBy;

    @TableFieldComment("创建时间")
    private Date createdTime;

    @TableFieldComment("更新人")
    private String updatedBy;

    @TableFieldComment("更新时间")
    private Date updatedTime;

}
