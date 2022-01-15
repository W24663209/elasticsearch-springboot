package cn.bensun.elasticsearch.mapper.sql;

import cn.bensun.elasticsearch.model.dto.requestLog.QueryRequestLogDTO;
import cn.bensun.elasticsearch.model.vo.requestLog.QueryRequestLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RequestLogMapper {

    /**
     * @Description 查询请求日志
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/15 18:37:02
     */
    List<QueryRequestLogDTO> queryList(@Param("vo") QueryRequestLogVO vo);
}
