package cn.bensun.elasticsearch.mapper.sql;

import cn.bensun.elasticsearch.model.dto.QueryOrderNumberByDayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PolymerInsteadOrderMapper {
    /**
     * @Description 根据时间查询临时订单数量
     * @CreatedBy weizongtang
     * @CreateTime 2022/12/31 18:52:22
     */
    List<QueryOrderNumberByDayDTO> queryOrderNumberByDay(@Param("startTime") Long startTime, @Param("endTime") Long endTime);
}
