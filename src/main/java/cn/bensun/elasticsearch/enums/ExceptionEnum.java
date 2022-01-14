package cn.bensun.elasticsearch.enums;

import lombok.Getter;

/**
 * @Description 异常枚举
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 20:30:50
 */
@Getter
public enum ExceptionEnum {
    NO_DEPEND(500, "没有依赖"),
    SAVE_ORDER_FAIL(500, "保存订单失败\t%s"),
    ;
    private Integer code;
    private String desc;

    ExceptionEnum(String desc) {
        this.desc = desc;
    }

    ExceptionEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
