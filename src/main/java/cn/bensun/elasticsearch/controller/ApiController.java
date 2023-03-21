package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.domain.BaseEntity;
import cn.bensun.elasticsearch.domain.TableDataInfo;
import cn.bensun.elasticsearch.util.BeanUtil;
import cn.bensun.elasticsearch.util.DateUtil;
import cn.bensun.elasticsearch.util.SearchRequestUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/03/20 13:11:33
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    /**
     * @Description 列表查询
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/20 13:11:38
     */
    @PostMapping("/list/{table}")
    @ApiOperation(value = "列表查询")
    public TableDataInfo list(@PathVariable String table,@RequestBody JSONObject req) throws Exception {
        Class clazz = BeanUtil.getTable(table);
        BaseEntity obj =(BaseEntity) JSON.to(clazz, req);
        return SearchRequestUtil.searchList(BeanUtil.getTable(table), obj);
    }

    /**
     * @Description 分组查询
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/17 14:45:33
     */
    @PostMapping("/group/{table}")
    @ApiOperation(value = "分组查询")
    public BaseEntity group(@PathVariable String table,@RequestBody JSONObject req) throws Exception {
        Class clazz = BeanUtil.getTable(table);
        BaseEntity obj =(BaseEntity) JSON.to(clazz, req);
        obj.setSearchEndTime(DateUtil.timestampAdd(obj.getSearchStartTime(), 1));
        return SearchRequestUtil.searchAggregation(clazz, obj);
    }
}
