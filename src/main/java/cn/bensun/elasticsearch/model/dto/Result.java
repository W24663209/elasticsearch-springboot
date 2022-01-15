package cn.bensun.elasticsearch.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 统一返回
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/01 14:57:08
 */
@Data
public class Result<T> implements Serializable {
    private int code;
    private T data;
    private String msg;
    private boolean success;
}
