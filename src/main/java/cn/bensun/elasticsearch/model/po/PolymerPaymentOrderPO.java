package cn.bensun.elasticsearch.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Description
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 19:47:04
 */
@Data
@Document(indexName = "t_polymer_payment_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolymerPaymentOrderPO implements Serializable {

    @Field(name = "agent_earn_rate", value = "agent_earn_rate")
    private BigDecimal agentEarnRate;//代理赚取费率

    @Field(name = "agent_margins", value = "agent_margins")
    private BigDecimal agentMargins;//代理收益

    @Field(name = "asyn_notify_url", value = "asyn_notify_url")
    private String asynNotifyUrl;//支付结束后异步回调通知商户的地址

    @Field(name = "bank_branch", value = "bank_branch")
    private String bankBranch;//支行名称,部分通道需要

    @Field(name = "bank_card_number", value = "bank_card_number")
    private String bankCardNumber;//银行卡号

    @Field(name = "bank_code", value = "bank_code")
    private String bankCode;//联行号,部分通道需要

    @Field(name = "bank_name", value = "bank_name")
    private String bankName;//银行名称

    @Field(name = "bill_id", value = "bill_id")
    private Long billId;//商户统计账单ID

    @Field(name = "cardholder", value = "cardholder")
    private String cardholder;//持卡者

    @Field(name = "channel_amount", value = "channel_amount")
    private BigDecimal channelAmount;//支付渠道成本

    @Field(name = "channel_bill_id", value = "channel_bill_id")
    private Long channelBillId;//渠道统计账单id

    @Field(name = "channel_charge", value = "channel_charge")
    private BigDecimal channelCharge;//支付渠道手续费

    @Field(name = "channel_fee", value = "channel_fee")
    private BigDecimal channelFee;//渠道代付单笔要的手续费

    @Field(name = "channel_rate", value = "channel_rate")
    private BigDecimal channelRate;//支付通道代付费率

    @Field(name = "channel_tax", value = "channel_tax")
    private BigDecimal channelTax;//通道税费

    @Field(name = "city", value = "city")
    private String city;//支行城市,部分通道需要

    @Field(name = "confirm_time", value = "confirm_time")
    private Timestamp confirmTime;//订单确认完成时间

    @Field(name = "create_days", value = "create_days")
    private Date createDays;//创建时间的天数

    @Field(name = "create_time", value = "create_time")
    private Timestamp createTime;//订单创建时间

    @Field(name = "error_status", value = "error_status")
    private Integer errorStatus;//异常状态(1.正常，2.异常，3申请异常处理)

    @Field(name = "escrow_trade_no", value = "escrow_trade_no")
    private String escrowTradeNo;//第三方支付流水号

    @Id
    private Long id;//订单ID

    @Field(name = "id_card", value = "id_card")
    private String idCard;//身份证号码,部分通道需要

    @Field(name = "merchant_amount", value = "merchant_amount")
    private BigDecimal merchantAmount;//商户成本(单位INR)

    @Field(name = "merchant_charge", value = "merchant_charge")
    private BigDecimal merchantCharge;//商户手续费

    @Field(name = "merchant_fee", value = "merchant_fee")
    private BigDecimal merchantFee;//商户代付单笔要的手续费

    @Field(name = "merchant_id", value = "merchant_id")
    private Long merchantId;//商户id

    @Field(name = "merchant_order_no", value = "merchant_order_no")
    private String merchantOrderNo;//商户订单号

    @Field(name = "merchant_rate", value = "merchant_rate")
    private BigDecimal merchantRate;//商户代付费率

    @Field(name = "merchant_user_id", value = "merchant_user_id")
    private Long merchantUserId;//商户用户id

    @Field(name = "notice_status", value = "notice_status")
    private Integer noticeStatus;//通知状态(1.未发送 2.已发送通知3.超时已通知交易员)

    @Field(name = "order_amount", value = "order_amount")
    private BigDecimal orderAmount;//订单交易金额(单位INR)

    @Field(name = "order_state", value = "order_state")
    private Integer orderState;//订单状态:OrderStateEnum(1:新订单,2:客户取消,3:交易中,4:订单超时5:已关闭,6:系统取消,7:交易失败,8:修改金额审核,9:订单交易完成)

    @Field(name = "otc_order_code", value = "otc_order_code")
    private String otcOrderCode;//otc订单编号

    @Field(name = "pay_channel", value = "pay_channel")
    private String payChannel;//支付通道(通道的标识key)

    @Field(name = "pay_coin_type", value = "pay_coin_type")
    private Integer payCoinType;//支付币种（1.INR）

    @Field(name = "pay_state", value = "pay_state")
    private Integer payState;//支付状态:PayStateEnum,0:已取消,1:待支付,2:已支付,3:支付失败,4:支付超时

    @Field(name = "pay_time", value = "pay_time")
    private Timestamp payTime;//支付完成时间

    @Field(name = "pay_type", value = "pay_type")
    private Integer payType;//订单支付类型,1:移动端,2:web端

    @Field(name = "payee_address", value = "payee_address")
    private String payeeAddress;//持卡人居住地址

    @Field(name = "payee_email", value = "payee_email")
    private String payeeEmail;//持卡人邮箱

    @Field(name = "payment_method", value = "payment_method")
    private Integer paymentMethod;//支付方式（1:t_payment_imps）

    @Field(name = "phone", value = "phone")
    private String phone;//银行预留手机号,部分通道需要

    @Field(name = "platform_margins", value = "platform_margins")
    private BigDecimal platformMargins;//平台收益金额(单位INR)

    @Field(name = "province", value = "province")
    private String province;//支行省份,部分通道需要

    @Field(name = "retry", value = "retry")
    private Integer retry;//是否待重发,0:否,1:是

    @Field(name = "user_id", value = "user_id")
    private String userId;//商家C端用户唯一标识

}