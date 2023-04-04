package cn.bensun.elasticsearch.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "t_payment", type = "t_payment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity{

    @Id
    private String id;//主键

    @Field(name = "order_no", value = "order_no", type = FieldType.Text)
    private String orderNo;//平台订单号

    @Field(name = "superior_no", value = "superior_no", type = FieldType.Text)
    private String superiorNo;//上游订单号

    @Field(name = "amount", value = "amount", type = FieldType.Double)
    private Double amount;//交易金额

    @Field(name = "should_amount", value = "should_amount", type = FieldType.Double)
    private Double shouldAmount;//应付金额

    @Field(name = "real_amount", value = "real_amount", type = FieldType.Double)
    private Double realAmount;//实付金额

    @Field(name = "status", value = "status", type = FieldType.Text)
    private String status;//订单状态

    @Field(name = "channel_no", value = "channel_no", type = FieldType.Text)
    private String channelNo;//通道号

    @Field(name = "channel_rate", value = "channel_rate", type = FieldType.Double)
    private Double channelRate;//通道费率

    @Field(name = "channel_fee", value = "channel_fee", type = FieldType.Double)
    private Double channelFee;//通道手续费

    @Field(name = "channel_single_fee", value = "channel_single_fee", type = FieldType.Double)
    private Double channelSingleFee;//单笔手续费

    @Field(name = "merchant_id", value = "merchant_id", type = FieldType.Text)
    private String merchantId;//商家id

    @Field(name = "merchant_rate", value = "merchant_rate", type = FieldType.Double)
    private Double merchantRate;//商家费率

    @Field(name = "merchant_fee", value = "merchant_fee", type = FieldType.Double)
    private Double merchantFee;//商家手续费

    @Field(name = "merchant_single_fee", value = "merchant_single_fee", type = FieldType.Double)
    private Double merchantSingleFee;//单笔手续费

    @Field(name = "merchant_order_no", value = "merchant_order_no", type = FieldType.Text)
    private String merchantOrderNo;//商家订单号

    @Field(name = "merchant_settle", value = "merchant_settle", type = FieldType.Double)
    private Double merchantSettle;//商户结算金额

    @Field(name = "income", value = "income", type = FieldType.Double)
    private Double income;//收益

    @Field(name = "user_id", value = "user_id", type = FieldType.Text)
    private String userId;//付款用户id

    @Field(name = "pay_type", value = "pay_type", type = FieldType.Text)
    private String payType;//支付类型;参考-PayTypeEnum

    @Field(name = "ifsc_code", value = "ifsc_code", type = FieldType.Text)
    private String ifscCode;//ifsc code

    @Field(name = "user_bank_account", value = "user_bank_account", type = FieldType.Text)
    private String userBankAccount;//用户收款账号

    @Field(name = "user_name", value = "user_name", type = FieldType.Text)
    private String userName;//收款用户名

    @Field(name = "user_phone", value = "user_phone", type = FieldType.Text)
    private String userPhone;//收款用户手机

    @Field(name = "user_email", value = "user_email", type = FieldType.Text)
    private String userEmail;//收款用户邮箱

    @Field(name = "bank_code", value = "bank_code", type = FieldType.Text)
    private String bankCode;//收款银行代码

    @Field(name = "receive_name", value = "receive_name", type = FieldType.Text)
    private String receiveName;//收款银行户名

    @Field(name = "fail_reason", value = "fail_reason", type = FieldType.Text)
    private String failReason;//

    @Field(name = "attach", value = "attach", type = FieldType.Text)
    private String attach;//其它参数

    @Field(name = "notify_url", value = "notify_url", type = FieldType.Text)
    private String notifyUrl;//回调地址

    @Field(name = "msg_id", value = "msg_id", type = FieldType.Text)
    private String msgId;//mq消费id

    @Field(name = "mq_is_consume", value = "mq_is_consume", type = FieldType.Text)
    private String mqIsConsume;//mq

    @Field(name = "remark", value = "remark", type = FieldType.Text)
    private String remark;//

    @Field(name = "threadId", value = "threadId", type = FieldType.Text)
    private String threadId;//id

    @Field(name = "pay_time", value = "pay_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long payTime;//支付时间

    @Field(name = "submit_time", value = "submit_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long submitTime;//

    @Field(name = "recount", value = "recount", type = FieldType.Integer)
    private Integer recount;//

    @Field(name = "request_time", value = "request_time", type = FieldType.Double)
    private Double requestTime;//

    @Field(name = "created_time", value = "created_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long createdTime;//创建时间

    @Field(name = "updated_by", value = "updated_by", type = FieldType.Text)
    private String updatedBy;//更新人

    @Field(name = "updated_time", value = "updated_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long updatedTime;//更新时间

    @Field(name = "is_refund", value = "is_refund", type = FieldType.Text)
    private String isRefund;//;-SwitchStatusEnum
    @Field(name = "is_callback", value = "is_callback", type = FieldType.Text)
    private String isCallback;
    @Field(name = "request_id", value = "request_id", type = FieldType.Text)
    private String requestId;


    public void setCreatedTime(Long createdTime) {
        this.createdTime = Long.valueOf(String.valueOf(createdTime).substring(0,10));
    }

}