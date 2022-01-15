package cn.bensun.elasticsearch.service.impl;

import cn.bensun.elasticsearch.mapper.SaveRequestRepository;
import cn.bensun.elasticsearch.mapper.sql.SaveRequestMapper;
import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.model.po.SaveRequestPO;
import cn.bensun.elasticsearch.model.vo.requestLog.SaveRequestVO;
import cn.bensun.elasticsearch.service.SaveRequestLogService;
import cn.bensun.elasticsearch.util.ResultUtil;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class SaveRequestLogServiceImpl implements SaveRequestLogService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private SaveRequestMapper saveRequestMapper;

    @Autowired
    private SaveRequestRepository saveRequestRepository;

    /**
     * @Description 保存请求日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/14 20:38:56
     */
    @Override
    public Result save(SaveRequestVO vo) {
        if (!elasticsearchRestTemplate.indexExists(SaveRequestPO.class)) {
            elasticsearchRestTemplate.createIndex(SaveRequestPO.class);
        }
        SaveRequestPO saveRequestPO = SaveRequestPO.builder().build();
        BeanUtil.copyProperties(vo, saveRequestPO);
        saveRequestRepository.save(saveRequestPO);
        return ResultUtil.success();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("gffdsureq", 1);
            put("gffdsures", 2);
            put("resgfdsf", 3);
        }};

        Set<String> keys = map.keySet();
        for (String key : keys) {
            key = key.substring(0, key.length() - 3);
            System.out.println(key);
        }


        System.out.println(map);
    }
}
