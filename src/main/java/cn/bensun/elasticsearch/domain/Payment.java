package cn.bensun.elasticsearch.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "t_payment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity{

    @Id
    private String id;//主键

    @Field(name = "order_no", targetType = FieldType.STRING)
    private String orderNo;//平台订单号

    @Field(name = "superior_no", targetType = FieldType.STRING)
    private String superiorNo;//上游订单号

    @Field(name = "amount", targetType = FieldType.DOUBLE)
    private Double amount;//交易金额

    @Field(name = "should_amount", targetType = FieldType.DOUBLE)
    private Double shouldAmount;//应付金额

    @Field(name = "real_amount", targetType = FieldType.DOUBLE)
    private Double realAmount;//实付金额

    @Field(name = "status", targetType = FieldType.STRING)
    private String status;//订单状态

    @Field(name = "channel_no", targetType = FieldType.STRING)
    private String channelNo;//通道号

    @Field(name = "channel_rate", targetType = FieldType.DOUBLE)
    private Double channelRate;//通道费率

    @Field(name = "channel_fee", targetType = FieldType.DOUBLE)
    private Double channelFee;//通道手续费

    @Field(name = "channel_single_fee", targetType = FieldType.DOUBLE)
    private Double channelSingleFee;//单笔手续费

    @Field(name = "merchant_id", targetType = FieldType.STRING)
    private String merchantId;//商家id

    @Field(name = "merchant_rate", targetType = FieldType.DOUBLE)
    private Double merchantRate;//商家费率

    @Field(name = "merchant_fee", targetType = FieldType.DOUBLE)
    private Double merchantFee;//商家手续费

    @Field(name = "merchant_single_fee", targetType = FieldType.DOUBLE)
    private Double merchantSingleFee;//单笔手续费

    @Field(name = "merchant_order_no", targetType = FieldType.STRING)
    private String merchantOrderNo;//商家订单号

    @Field(name = "merchant_settle", targetType = FieldType.DOUBLE)
    private Double merchantSettle;//商户结算金额

    @Field(name = "income", targetType = FieldType.DOUBLE)
    private Double income;//收益

    @Field(name = "user_id", targetType = FieldType.STRING)
    private String userId;//付款用户id

    @Field(name = "pay_type", targetType = FieldType.STRING)
    private String payType;//支付类型;参考-PayTypeEnum

    @Field(name = "ifsc_code", targetType = FieldType.STRING)
    private String ifscCode;//ifsc code

    @Field(name = "user_bank_account", targetType = FieldType.STRING)
    private String userBankAccount;//用户收款账号

    @Field(name = "user_name", targetType = FieldType.STRING)
    private String userName;//收款用户名

    @Field(name = "user_phone", targetType = FieldType.STRING)
    private String userPhone;//收款用户手机

    @Field(name = "user_email", targetType = FieldType.STRING)
    private String userEmail;//收款用户邮箱

    @Field(name = "bank_code", targetType = FieldType.STRING)
    private String bankCode;//收款银行代码

    @Field(name = "receive_name", targetType = FieldType.STRING)
    private String receiveName;//收款银行户名

    @Field(name = "fail_reason", targetType = FieldType.STRING)
    private String failReason;//

    @Field(name = "attach", targetType = FieldType.STRING)
    private String attach;//其它参数

    @Field(name = "notify_url", targetType = FieldType.STRING)
    private String notifyUrl;//回调地址

    @Field(name = "msg_id", targetType = FieldType.STRING)
    private String msgId;//mq消费id

    @Field(name = "mq_is_consume", targetType = FieldType.STRING)
    private String mqIsConsume;//mq

    @Field(name = "remark", targetType = FieldType.STRING)
    private String remark;//

    @Field(name = "threadId", targetType = FieldType.STRING)
    private String threadId;//id

    @Field(name = "pay_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long payTime;//支付时间

    @Field(name = "submit_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long submitTime;//

    @Field(name = "recount", targetType = FieldType.INT32)
    private Integer recount;//

    @Field(name = "request_time", targetType = FieldType.DOUBLE)
    private Double requestTime;//

    @Field(name = "created_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long createdTime;//创建时间

    @Field(name = "updated_by", targetType = FieldType.STRING)
    private String updatedBy;//更新人

    @Field(name = "updated_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long updatedTime;//更新时间

    @Field(name = "is_refund", targetType = FieldType.STRING)
    private String isRefund;//;-SwitchStatusEnum
    @Field(name = "is_callback", targetType = FieldType.STRING)
    private String isCallback;
    @Field(name = "request_id", targetType = FieldType.STRING)
    private String requestId;


    public void setCreatedTime(Long createdTime) {
        this.createdTime = Long.valueOf(String.valueOf(createdTime).substring(0,10));
    }

}