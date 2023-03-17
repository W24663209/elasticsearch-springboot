package cn.bensun.elasticsearch.enums;

import cn.hutool.core.util.ObjectUtil;

import java.util.Arrays;

/**
 * @Description 代收订单状态
 * @CreatedBy weizongtang
 * @CreateTime 2022/10/12 15:50:01
 */
public enum PayOutStatusEnum {
    已处理("PROCESSED", "已处理"),
    未提交("UN_SUBMITTED", "未提交"),
    提交失败("SUBMITTED_FAILED", "提交失败"),
    等待重提("AGAIN_SUBMITTED", "等待重提"),
    支付中("PAYING", "支付中"),
    支付失败("PAY_FAILED", "支付失败"),
    取消支付("CANCEL_PAY", "取消支付"),
    已支付("PAID", "已支付");

    private String code;
    private String msg;

    PayOutStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(String code) {
        PayOutStatusEnum payInStatusEnum = Arrays.stream(values()).filter(e -> e.code.equals(code)).findAny().orElse(null);
        return ObjectUtil.isEmpty(payInStatusEnum) ? null : payInStatusEnum.msg;
    }
}
