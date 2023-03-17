package cn.bensun.elasticsearch.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "t_collection", type = "t_collection")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Collection extends BaseEntity {

    @Id
    private String id;//主键

    @Field(name = "order_no", value = "order_no", type = FieldType.Keyword)
    private String orderNo;//平台订单号

    @Field(name = "superior_no", value = "superior_no", type = FieldType.Text)
    private String superiorNo;//上游订单号

    @Field(name = "tr_no", value = "tr_no", type = FieldType.Text)
    private String trNo;//tr

    @Field(name = "upi_id", value = "upi_id", type = FieldType.Text)
    private String upiId;//upi id

    @Field(name = "utr", value = "utr", type = FieldType.Text)
    private String utr;//utr

    @Field(name = "amount", value = "amount", type = FieldType.Double)
    private Double amount;//交易金额

    @Field(name = "should_amount", value = "should_amount", type = FieldType.Double)
    private Double shouldAmount;//应收金额

    @Field(name = "real_amount", value = "real_amount", type = FieldType.Double)
    private Double realAmount;//实收金额

    @Field(name = "status", value = "status", type = FieldType.Text)
    private String status;//订单状态

    @Field(name = "channel_no", value = "channel_no", type = FieldType.Text)
    private String channelNo;//通道号

    @Field(name = "channel_rate", value = "channel_rate", type = FieldType.Double)
    private Double channelRate;//通道费率

    @Field(name = "fee", value = "fee", type = FieldType.Double)
    private Double fee;//通道手续费

    @Field(name = "merchant_id", value = "merchant_id", type = FieldType.Text)
    private String merchantId;//商家id

    @Field(name = "merchant_rate", value = "merchant_rate", type = FieldType.Double)
    private Double merchantRate;//商家费率

    @Field(name = "merchant_fee", value = "merchant_fee", type = FieldType.Double)
    private Double merchantFee;//商家手续费

    @Field(name = "merchant_order_no", value = "merchant_order_no", type = FieldType.Text)
    private String merchantOrderNo;//商家订单号

    @Field(name = "merchant_settle", value = "merchant_settle", type = FieldType.Double)
    private Double merchantSettle;//商户结算金额

    @Field(name = "settle_cycle", value = "settle_cycle", type = FieldType.Text)
    private String settleCycle;//结算周期;参考-PayInSettleCycleEnum

    @Field(name = "settle_status", value = "settle_status", type = FieldType.Text)
    private String settleStatus;//

    @Field(name = "income", value = "income", type = FieldType.Double)
    private Double income;//收益

    @Field(name = "user_id", value = "user_id", type = FieldType.Text)
    private String userId;//付款用户id

    @Field(name = "user_bank_account", value = "user_bank_account", type = FieldType.Text)
    private String userBankAccount;//用户付款账号

    @Field(name = "user_name", value = "user_name", type = FieldType.Text)
    private String userName;//付款用户名

    @Field(name = "user_phone", value = "user_phone", type = FieldType.Text)
    private String userPhone;//付款用户手机

    @Field(name = "user_email", value = "user_email", type = FieldType.Text)
    private String userEmail;//付款用户邮箱

    @Field(name = "fail_reason", value = "fail_reason", type = FieldType.Text)
    private String failReason;//

    @Field(name = "attach", value = "attach", type = FieldType.Text)
    private String attach;//其它参数

    @Field(name = "upi_url", value = "upi_url", type = FieldType.Text)
    private String upiUrl;//

    @Field(name = "notify_url", value = "notify_url", type = FieldType.Text)
    private String notifyUrl;//回调地址

    @Field(name = "return_url", value = "return_url", type = FieldType.Text)
    private String returnUrl;//同步返回地址

    @Field(name = "pay_time", value = "pay_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long payTime;//支付时间

    @Field(name = "call_time", value = "call_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long callTime;//

    @Field(name = "request_time", value = "request_time", type = FieldType.Double)
    private Double requestTime;//

    @Field(name = "remark", value = "remark", type = FieldType.Text)
    private String remark;//备注

    @Field(name = "threadId", value = "threadId", type = FieldType.Text)
    private String threadId;//id

    @Field(name = "created_time", value = "created_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long createdTime;//创建时间

    @Field(name = "updated_by", value = "updated_by", type = FieldType.Text)
    private String updatedBy;//更新人

    @Field(name = "updated_time", value = "updated_time", type = FieldType.Long)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Long updatedTime;//更新时间

    @Field(name = "superior_setlle_status", value = "superior_setlle_status", type = FieldType.Text)
    private String superiorSetlleStatus;//

}