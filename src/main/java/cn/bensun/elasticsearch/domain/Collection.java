package cn.bensun.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "t_collection")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Collection extends BaseEntity {

    @Id
    private String id;//主键

    @Field(name = "order_no", value = "order_no", targetType = FieldType.STRING)
    private String orderNo;//平台订单号

    @Field(name = "superior_no", value = "superior_no", targetType = FieldType.STRING)
    private String superiorNo;//上游订单号

    @Field(name = "tr_no", value = "tr_no", targetType = FieldType.STRING)
    private String trNo;//tr

    @Field(name = "upi_id", value = "upi_id", targetType = FieldType.STRING)
    private String upiId;//upi id

    @Field(name = "utr", value = "utr", targetType = FieldType.STRING)
    private String utr;//utr

    @Field(name = "amount", value = "amount", targetType = FieldType.DOUBLE)
    private Double amount;//交易金额

    @Field(name = "should_amount", value = "should_amount", targetType = FieldType.DOUBLE)
    private Double shouldAmount;//应收金额

    @Field(name = "real_amount", value = "real_amount", targetType = FieldType.DOUBLE)
    private Double realAmount;//实收金额

    @Field(name = "status", value = "status", targetType = FieldType.STRING)
    private String status;//订单状态

    @Field(name = "channel_no", value = "channel_no", targetType = FieldType.STRING)
    private String channelNo;//通道号

    @Field(name = "channel_rate", value = "channel_rate", targetType = FieldType.DOUBLE)
    private Double channelRate;//通道费率

    @Field(name = "fee", value = "fee", targetType = FieldType.DOUBLE)
    private Double fee;//通道手续费

    @Field(name = "merchant_id", value = "merchant_id", targetType = FieldType.STRING)
    private String merchantId;//商家id

    @Field(name = "merchant_rate", value = "merchant_rate", targetType = FieldType.DOUBLE)
    private Double merchantRate;//商家费率

    @Field(name = "merchant_fee", value = "merchant_fee", targetType = FieldType.DOUBLE)
    private Double merchantFee;//商家手续费

    @Field(name = "merchant_order_no", value = "merchant_order_no", targetType = FieldType.STRING)
    private String merchantOrderNo;//商家订单号

    @Field(name = "merchant_settle", value = "merchant_settle", targetType = FieldType.DOUBLE)
    private Double merchantSettle;//商户结算金额

    @Field(name = "settle_cycle", value = "settle_cycle", targetType = FieldType.STRING)
    private String settleCycle;//结算周期;参考-PayInSettleCycleEnum

    @Field(name = "settle_status", value = "settle_status", targetType = FieldType.STRING)
    private String settleStatus;//

    @Field(name = "income", value = "income", targetType = FieldType.DOUBLE)
    private Double income;//收益

    @Field(name = "user_id", value = "user_id", targetType = FieldType.STRING)
    private String userId;//付款用户id

    @Field(name = "user_bank_account", value = "user_bank_account", targetType = FieldType.STRING)
    private String userBankAccount;//用户付款账号

    @Field(name = "user_name", value = "user_name", targetType = FieldType.STRING)
    private String userName;//付款用户名

    @Field(name = "user_phone", value = "user_phone", targetType = FieldType.STRING)
    private String userPhone;//付款用户手机

    @Field(name = "user_email", value = "user_email", targetType = FieldType.STRING)
    private String userEmail;//付款用户邮箱

    @Field(name = "fail_reason", value = "fail_reason", targetType = FieldType.STRING)
    private String failReason;//

    @Field(name = "attach", value = "attach", targetType = FieldType.STRING)
    private String attach;//其它参数

    @Field(name = "upi_url", value = "upi_url", targetType = FieldType.STRING)
    private String upiUrl;//

    @Field(name = "notify_url", value = "notify_url", targetType = FieldType.STRING)
    private String notifyUrl;//回调地址

    @Field(name = "return_url", value = "return_url", targetType = FieldType.STRING)
    private String returnUrl;//同步返回地址

    @Field(name = "pay_time", value = "pay_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private String payTime;//支付时间

    @Field(name = "call_time", value = "call_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private String callTime;//

    @Field(name = "request_time", value = "request_time", targetType = FieldType.DOUBLE)
    private Double requestTime;//

    @Field(name = "remark", value = "remark", targetType = FieldType.STRING)
    private String remark;//备注

    @Field(name = "threadId", value = "threadId", targetType = FieldType.STRING)
    private String threadId;//id

    @Field(name = "created_time", value = "created_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private String createdTime;//创建时间

    @Field(name = "updated_by", value = "updated_by", targetType = FieldType.STRING)
    private String updatedBy;//更新人

    @Field(name = "updated_time", value = "updated_time", targetType = FieldType.INT64)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private String updatedTime;//更新时间

    @Field(name = "superior_setlle_status", value = "superior_setlle_status", targetType = FieldType.STRING)
    private String superiorSetlleStatus;//
    @Field(name = "is_callback", value = "is_callback", targetType = FieldType.STRING)
    private String isCallback;
    @Field(name = "request_id", value = "request_id", targetType = FieldType.STRING)
    private String requestId;



//    public void setCreatedTime(Long createdTime) {
//        this.createdTime = Long.valueOf(String.valueOf(createdTime).substring(0,10));
//    }
}