package cn.bensun.elasticsearch.mapper.sql;


import cn.bensun.elasticsearch.model.dto.QueryOrderNumberByDayDTO;
import cn.bensun.elasticsearch.model.po.PolymerTempOrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 代收临时订单-es集成sql
 * @CreatedBy weizongtang
 * @CreateTime 2022/12/31 13:23:37
 */
@Mapper
public interface PolymerTempOrderMapper {

    PolymerTempOrderPO testSql();

    /**
     * @Description 根据时间查询临时订单数量
     * @CreatedBy weizongtang
     * @CreateTime 2022/12/31 18:52:22
     */
    List<QueryOrderNumberByDayDTO> queryOrderNumberByDay(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    /**
     * @Description 最近下单时间
     * @CreatedBy weizongtang
     * @CreateTime 2022/02/09 20:16:55
     */
    Long queryPlaceOrderTime(Long userId);

    /**
     * @Description 最近下单时间(通道)
     * @CreatedBy weizongtang
     * @CreateTime 2022/03/11 14:36:39
     */
    Long queryPlaceOrderTimeByChannel(@Param("userId") Long userId, @Param("payChannel") String payChannel);
}
