package cn.bensun.elasticsearch.enums;

import cn.hutool.core.util.ObjectUtil;

import java.util.Arrays;

/**
 * @Description 代收结算状态
 * @CreatedBy weizongtang
 * @CreateTime 2022/10/27 22:03:19
 */
public enum PayInSettleStatusEnum {
    未结算("not_settle", "未结算"),
    结算中("settling", "结算中"),
    已结算("settled", "已结算"),
    ;

    private String code;
    private String msg;

    PayInSettleStatusEnum(String code, String msg) {
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
        PayInSettleStatusEnum payInSettleStatusEnum = Arrays.stream(values()).filter(e -> e.code.equals(code)).findAny().orElse(null);
        return ObjectUtil.isEmpty(payInSettleStatusEnum) ? null : payInSettleStatusEnum.msg;
    }
}
