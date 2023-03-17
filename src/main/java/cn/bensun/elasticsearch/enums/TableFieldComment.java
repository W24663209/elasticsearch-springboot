package cn.bensun.elasticsearch.enums;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableFieldComment {
    String value();//备注
}
