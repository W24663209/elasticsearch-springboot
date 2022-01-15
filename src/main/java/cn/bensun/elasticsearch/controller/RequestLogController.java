package cn.bensun.elasticsearch.controller;


import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.dto.requestLog.QueryRequestLogDTO;
import cn.bensun.elasticsearch.model.vo.requestLog.QueryRequestLogVO;
import cn.bensun.elasticsearch.model.vo.requestLog.SaveRequestVO;
import cn.bensun.elasticsearch.service.RequestLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 请求日志
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/14 20:29:45
 */
@RestController
@RequestMapping("/requestLog")
public class RequestLogController {

    @Autowired
    private RequestLogService requestLogService;

    /**
     * @Description 保存请求记录
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/14 20:31:56
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存请求记录")
    public Result save(@RequestBody SaveRequestVO vo){
        return requestLogService.save(vo);
    }

    /**
     * @Description 查询日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/15 18:30:56
     */
    @PostMapping("/query")
    @ApiOperation(value = "查询")
    public List<QueryRequestLogDTO> queryList(@RequestBody QueryRequestLogVO vo) {
        return requestLogService.queryList(vo);
    }
}
