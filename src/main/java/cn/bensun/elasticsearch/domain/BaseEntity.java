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

    private int pageNumber = 1;
    private int pageSize = 10;

    private Long searchStartTime;

    private Long searchEndTime;

    public void setSearchStartTime(Long searchStartTime) {
        this.searchStartTime = searchStartTime != null ? Long.valueOf(String.valueOf(searchStartTime).substring(0, 10)) : searchStartTime;;
    }

    public void setSearchEndTime(Long searchEndTime) {
        this.searchEndTime = searchEndTime != null ? Long.valueOf(String.valueOf(searchEndTime).substring(0, 10)) : searchEndTime;;
    }
}
