package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.domain.BaseEntity;
import cn.bensun.elasticsearch.domain.TableDataInfo;
import cn.bensun.elasticsearch.util.BeanUtil;
import cn.bensun.elasticsearch.util.SearchRequestUtil;
import cn.bensun.elasticsearch.util.SpringContextUtils;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;

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
    public TableDataInfo list(@PathVariable String table, @RequestBody JSONObject req) throws Exception {
        Class clazz = BeanUtil.getTable(table);
        BaseEntity obj = ObjectUtil.isNotEmpty(req) ? (BaseEntity) JSON.to(clazz, req) : (BaseEntity) clazz.newInstance();
        return SearchRequestUtil.searchList(BeanUtil.getTable(table), obj);
    }

    /**
     * @Description 分组查询
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/17 14:45:33
     */
    @PostMapping("/group/{table}")
    @ApiOperation(value = "分组查询")
    public BaseEntity group(@PathVariable String table, @RequestBody JSONObject req) throws Exception {
        Class clazz = BeanUtil.getTable(table);
        BaseEntity obj = ObjectUtil.isNotEmpty(req) ? (BaseEntity) JSON.to(clazz, req) : (BaseEntity) clazz.newInstance();
        return SearchRequestUtil.searchAggregation(clazz, obj);
    }

    @PostMapping("/add/{table}")
    @ApiOperation(value = "添加")
    public String add(@PathVariable String table, @RequestBody Object req) throws Exception {
        Class clazz = BeanUtil.getTable(table);
        Object bean = SpringContextUtils.getBean(String.format("%sRepository", clazz.getSimpleName()));
        //            logMsgRepository.save(logMsg);
        Class<?> aClass = bean.getClass();
        Method method = Arrays.stream(aClass.getDeclaredMethods()).filter(e -> "save".equals(e.getName())).findAny().orElse(null);
        if (ObjectUtil.isEmpty(method)) {
            return null;
        }
        method.invoke(bean, req);
        return "保存成功";
    }

    public static void main(String[] args) {
        Class tLogMsg = BeanUtil.getTable("t_log_msg");
        System.out.println(tLogMsg);
    }
}
