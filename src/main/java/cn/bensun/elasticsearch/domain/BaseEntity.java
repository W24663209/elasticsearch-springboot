package cn.bensun.elasticsearch.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Entity基类
 *
 * @author ruoyi
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    private int pageNumber = 0;
    private int pageSize = 10;

    private Long searchStartTime;

    private Long searchEndTime;

}
