package cn.bensun.elasticsearch.controller;


import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.vo.requestLog.SaveRequestVO;
import cn.bensun.elasticsearch.service.SaveRequestLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 保存请求日志
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/14 20:29:45
 */
@RestController
@RequestMapping("/requestLog")
public class SaveRequestLogController {

    @Autowired
    private SaveRequestLogService saveRequestLogService;

    /**
     * @Description 保存请求记录
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/14 20:31:56
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存请求记录")
    public Result save(@RequestBody SaveRequestVO vo){
        return saveRequestLogService.save(vo);
    }
}
