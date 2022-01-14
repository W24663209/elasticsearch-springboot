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
import java.sql.Timestamp;

/**
 * @Description
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 19:47:23
 */
@Data
@Document(indexName = "t_polymer_temp_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolymerTempOrderPO implements Serializable {

    @Field(name = "asyn_notify_url", value = "asyn_notify_url")
    private String asynNotifyUrl;//支付结束后异步回调通知商户的地址

    @Field(name = "attach", value = "attach")
    private String attach;//商户传过来的订单附加参数,必返回商户

    @Field(name = "bank_code", value = "bank_code")
    private String bankCode;//银行ifsc编码

    @Field(name = "cancel_time", value = "cancel_time")
    private Timestamp cancelTime;//用户取消时间

    @Field(name = "cf_vpa", value = "cf_vpa")
    private String cfVpa;//cashfree收款upi账号

    @Field(name = "channel_fee", value = "channel_fee")
    private BigDecimal channelFee;//渠道支付单笔要的手续费

    @Field(name = "channel_rate", value = "channel_rate")
    private BigDecimal channelRate;//支付通道支付费率

    @Field(name = "channel_tax", value = "channel_tax")
    private BigDecimal channelTax;//通道税费

    @Field(name = "close_time", value = "close_time")
    private Timestamp closeTime;//订单超时关闭时间

    @Field(name = "confirm_time", value = "confirm_time")
    private Timestamp confirmTime;//订单确认完成时间

    @Field(name = "create_time", value = "create_time")
    private Timestamp createTime;//订单创建时间

    @Field(name = "email", value = "email")
    private String email;//支付用户的邮箱

    @Field(name = "error_message", value = "error_message")
    private String errorMessage;//支付返回信息

    @Field(name = "error_status", value = "error_status")
    private Integer errorStatus;//异常状态（1.正常，2.异常，3申请异常处理）

    @Field(name = "escrow_trade_no", value = "escrow_trade_no")
    private String escrowTradeNo;//第三方支付订单流水号

    @Field(name = "first_name", value = "first_name")
    private String firstName;//支付用户的姓名

    @Id
    private Long id;//订单ID

    @Field(name = "merchant_fee", value = "merchant_fee")
    private BigDecimal merchantFee;//商户支付单笔要的手续费

    @Field(name = "merchant_id", value = "merchant_id")
    private Long merchantId;//商户id

    @Field(name = "merchant_order_no", value = "merchant_order_no")
    private String merchantOrderNo;//商户订单号

    @Field(name = "merchant_rate", value = "merchant_rate")
    private BigDecimal merchantRate;//商户支付费率

    @Field(name = "merchant_user_id", value = "merchant_user_id")
    private Long merchantUserId;//商户用户id

    @Field(name = "notice_status", value = "notice_status")
    private Integer noticeStatus;//通知状态(1.未发送 2.已发送通知3.超时已通知交易员)

    @Field(name = "order_amount", value = "order_amount")
    private BigDecimal orderAmount;//订单交易金额

    @Field(name = "order_name", value = "order_name")
    private String orderName;//订单名称

    @Field(name = "order_pay_way", value = "order_pay_way")
    private Integer orderPayWay;//支付方式(1.UPI)

    @Field(name = "order_snapshot", value = "order_snapshot")
    private String orderSnapshot;//订单快照

    @Field(name = "order_state", value = "order_state")
    private Integer orderState;//订单状态:OrderStateEnum(1:新订单,2:客户取消,3:交易中,4:订单超时5:已关闭,6:系统取消,7:交易失败,8:修改金额审核,9:订单交易完成)

    @Field(name = "otc_order_code", value = "otc_order_code")
    private String otcOrderCode;//otc订单编号

    @Field(name = "over_time", value = "over_time")
    private Timestamp overTime;//订单超时时间

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

    @Field(name = "payer_account", value = "payer_account")
    private String payerAccount;//

    @Field(name = "payment_mode", value = "payment_mode")
    private String paymentMode;//支付类型

    @Field(name = "phone", value = "phone")
    private String phone;//支付用户的手机号

    @Field(name = "product_info", value = "product_info")
    private String productInfo;//购买的商品信息

    @Field(name = "remark", value = "remark")
    private String remark;//订单备注

    @Field(name = "request_state", value = "request_state")
    private Integer requestState;//请求三方接口状态

    @Field(name = "retry", value = "retry")
    private Integer retry;//是否待重发,0:否,1:是

    @Field(name = "sync_return_url", value = "sync_return_url")
    private String syncReturnUrl;//支付结束同步回显的商户系统页面地址

    @Field(name = "third_payment_url", value = "third_payment_url")
    private String thirdPaymentUrl;//三方支付收银台页面

    @Field(name = "user_id", value = "user_id")
    private String userId;//支付用户的id或唯一标识

    @Field(name = "utr", value = "utr")
    private String utr;//utr

}