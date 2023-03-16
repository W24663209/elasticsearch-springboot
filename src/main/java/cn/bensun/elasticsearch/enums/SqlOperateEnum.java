package cn.bensun.elasticsearch.enums;

import lombok.Getter;

/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/03/16 16:39:42
 */
@Getter
public enum SqlOperateEnum {
    删除("DELETE"),

    更新("UPDATE"),

    新增("INSERT"),
    ;

    private String code;

    SqlOperateEnum(String code) {
        this.code = code;
    }
}
