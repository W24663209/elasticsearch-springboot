package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.enums.DependEnum;
import cn.bensun.elasticsearch.enums.ExceptionEnum;
import cn.bensun.elasticsearch.util.MyExceptionUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 订单保存
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:26:14
 */
@RestController
@RequestMapping("/polymerOrder")
public class SaveOrderController {

    /**
     * @Description 保存订单
     * @CreatedBy weizongtang
     * @CreateTime 2022/12/31 20:02:03
     */
    @PostMapping("/save/{className}")
    @ApiOperation(value = "保存订单")
    public boolean save(@PathVariable String className, @RequestBody Object vo) {
        DependEnum dependEnum = DependEnum.getInstance(className);
        Object o = JSON.parseObject(JSON.toJSONString(vo), dependEnum.getPo());
        MyExceptionUtil.throwExIsNull(dependEnum, ExceptionEnum.NO_DEPEND);
        try {
            dependEnum.getRepositoryObj().save(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            MyExceptionUtil.throwEx(ExceptionEnum.SAVE_ORDER_FAIL, vo);
            return false;
        }
    }

}
