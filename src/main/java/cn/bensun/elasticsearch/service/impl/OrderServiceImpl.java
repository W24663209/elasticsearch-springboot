package cn.bensun.elasticsearch.service.impl;

import cn.bensun.elasticsearch.enums.AreaTimeEnum;
import cn.bensun.elasticsearch.mapper.sql.PolymerInsteadOrderMapper;
import cn.bensun.elasticsearch.mapper.sql.PolymerOrderMapper;
import cn.bensun.elasticsearch.mapper.sql.PolymerPaymentOrderMapper;
import cn.bensun.elasticsearch.mapper.sql.PolymerTempOrderMapper;
import cn.bensun.elasticsearch.model.dto.QueryMerchantProportionDTO;
import cn.bensun.elasticsearch.model.dto.QueryOrderNumberByDayDTO;
import cn.bensun.elasticsearch.model.dto.QueryPlaceOrderTimeDTO;
import cn.bensun.elasticsearch.model.dto.Result;
import cn.bensun.elasticsearch.service.OrderService;
import cn.bensun.elasticsearch.util.ResultUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 订单处理
 * @CreatedBy weizongtang
 * @CreateTime 2022/01/01 15:13:31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PolymerInsteadOrderMapper polymerInsteadOrderMapper;

    @Autowired
    private PolymerOrderMapper polymerOrderMapper;

    @Autowired
    private PolymerPaymentOrderMapper polymerPaymentOrderMapper;

    @Autowired
    private PolymerTempOrderMapper polymerTempOrderMapper;


    @Override
    public Result queryMerchantProportion(List<Long> ids) {
        Calendar currentTime = AreaTimeEnum.AreaTimeZone.YD.getCurrentTime(new HashMap<>());
        Calendar yesterdayCurrentTime = AreaTimeEnum.AreaTimeZone.YD.getCurrentTime(new HashMap<Integer, Integer>() {{
            put(Calendar.DATE, -1);
        }});
        Calendar sevenDaysBeforeCurrentTime = AreaTimeEnum.AreaTimeZone.YD.getCurrentTime(new HashMap<Integer, Integer>() {{
            put(Calendar.DATE, -7);
        }});
        long nowTime = currentTime.getTimeInMillis();
        long todayTime = AreaTimeEnum.AreaTimeZone.YD.getCalendar(currentTime.getTime(), AreaTimeEnum.FieldFormat.TIME_PATTERN_START).getTime();
        long yesterdayTime = AreaTimeEnum.AreaTimeZone.YD.getCalendar(yesterdayCurrentTime.getTime(), AreaTimeEnum.FieldFormat.TIME_PATTERN_START).getTime();
        long sevenDaysBeforeTime = AreaTimeEnum.AreaTimeZone.YD.getCalendar(sevenDaysBeforeCurrentTime.getTime(), AreaTimeEnum.FieldFormat.TIME_PATTERN_START).getTime();
        List<QueryOrderNumberByDayDTO> sevenDaysBeforeSellTempOrderNum = polymerInsteadOrderMapper.queryOrderNumberByDay(sevenDaysBeforeTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> sevenDaysBeforeSellTempOrderNumMap = sevenDaysBeforeSellTempOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> sevenDaysBeforeSellOrderNum = polymerPaymentOrderMapper.queryOrderNumberByDay(sevenDaysBeforeTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> sevenDaysBeforeSellOrderNumMap = sevenDaysBeforeSellOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> sevenDaysBeforePayOrderNum = polymerOrderMapper.queryOrderNumberByDay(sevenDaysBeforeTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> sevenDaysBeforePayOrderNumMap = sevenDaysBeforePayOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> sevenDaysBeforePayTempOrderNum = polymerTempOrderMapper.queryOrderNumberByDay(sevenDaysBeforeTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> sevenDaysBeforePayTempOrderNumMap = sevenDaysBeforePayTempOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));

        List<QueryOrderNumberByDayDTO> yesterdayTimeSellTempOrderNum = polymerInsteadOrderMapper.queryOrderNumberByDay(yesterdayTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> yesterdayTimeSellTempOrderNumMap = yesterdayTimeSellTempOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> yesterdayTimeSellOrderNum = polymerPaymentOrderMapper.queryOrderNumberByDay(yesterdayTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> yesterdayTimeSellOrderNumMap = yesterdayTimeSellOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> yesterdayTimePayOrderNum = polymerOrderMapper.queryOrderNumberByDay(yesterdayTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> yesterdayTimePayOrderNumMap = yesterdayTimePayOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> yesterdayTimePayTempOrderNum = polymerTempOrderMapper.queryOrderNumberByDay(yesterdayTime, todayTime);
        Map<Long, QueryOrderNumberByDayDTO> yesterdayTimePayTempOrderNumMap = yesterdayTimePayTempOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));

        List<QueryOrderNumberByDayDTO> todaySellTempOrderNum = polymerInsteadOrderMapper.queryOrderNumberByDay(todayTime, nowTime);
        Map<Long, QueryOrderNumberByDayDTO> todaySellTempOrderNumMap = todaySellTempOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> todaySellOrderNum = polymerPaymentOrderMapper.queryOrderNumberByDay(todayTime, nowTime);
        Map<Long, QueryOrderNumberByDayDTO> todaySellOrderNumMap = todaySellOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> todayPayOrderNum = polymerOrderMapper.queryOrderNumberByDay(todayTime, nowTime);
        Map<Long, QueryOrderNumberByDayDTO> todayPayOrderNumMap = todayPayOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));
        List<QueryOrderNumberByDayDTO> todayPayTempOrderNum = polymerTempOrderMapper.queryOrderNumberByDay(todayTime, nowTime);
        Map<Long, QueryOrderNumberByDayDTO> todayPayTempOrderNumMap = todayPayTempOrderNum.stream().collect(Collectors.toMap(QueryOrderNumberByDayDTO::getMerchantId, e -> e));

        List<QueryMerchantProportionDTO> queryMerchantProportionDTOS = new ArrayList<>();
        for (Long id : ids) {
            QueryMerchantProportionDTO build = QueryMerchantProportionDTO.builder().merchantId(id)
                    .sevenDaysBefore(getProportion(id, sevenDaysBeforeSellTempOrderNumMap, sevenDaysBeforeSellOrderNumMap, sevenDaysBeforePayTempOrderNumMap, sevenDaysBeforePayOrderNumMap))
                    .yesterday(getProportion(id, yesterdayTimeSellTempOrderNumMap, yesterdayTimeSellOrderNumMap, yesterdayTimePayTempOrderNumMap, yesterdayTimePayOrderNumMap))
                    .todayPay(getProportion(id, todaySellTempOrderNumMap, todaySellOrderNumMap, todayPayTempOrderNumMap, todayPayOrderNumMap)).build();
            queryMerchantProportionDTOS.add(build);
        }
        return ResultUtil.success(queryMerchantProportionDTOS);
    }

    /**
     * @Description 返回比例
     * @CreatedBy weizongtang
     * @CreateTime 2022/01/05 17:03:54
     */
    private BigDecimal getProportion(Long id, Map<Long, QueryOrderNumberByDayDTO> sellTempOrderNumMap,
                                     Map<Long, QueryOrderNumberByDayDTO> sellOrderNumMap,
                                     Map<Long, QueryOrderNumberByDayDTO> payTempOrderNumMap,
                                     Map<Long, QueryOrderNumberByDayDTO> payOrderNumMap) {
        QueryOrderNumberByDayDTO sellTempOrderNum = sellTempOrderNumMap.get(id);
        QueryOrderNumberByDayDTO sellOrderNum = sellOrderNumMap.get(id);
        QueryOrderNumberByDayDTO payTempOrderNum = payTempOrderNumMap.get(id);
        QueryOrderNumberByDayDTO payOrderNum = payOrderNumMap.get(id);
        int successNum = (ObjectUtil.isNotEmpty(sellOrderNum) ? sellOrderNum.getOrderNum() : 0) + (ObjectUtil.isNotEmpty(payOrderNum) ? payOrderNum.getOrderNum() : 0);
        int totalNum = (ObjectUtil.isNotEmpty(sellTempOrderNum) ? sellTempOrderNum.getOrderNum() : 0) + (ObjectUtil.isNotEmpty(payTempOrderNum) ? payTempOrderNum.getOrderNum() : 0);
        return BigDecimal.valueOf(successNum * 1.0 / (totalNum == 0 ? 1 : totalNum));
    }

    /**
     * @param userIds
     * @Description 最近下单时间(集合)
     * @CreatedBy weizongtang
     * @CreateTime 2022/02/10 14:18:01
     */
    @Override
    public List<QueryPlaceOrderTimeDTO> queryPlaceOrderTimeList(List<Long> userIds) {
        //值自然排序（倒序）
        List<QueryPlaceOrderTimeDTO> list = new ArrayList<>();
        for (Long userId : userIds) {
            Long polymerInsteadOrderTime = polymerInsteadOrderMapper.queryPlaceOrderTime(userId);
            Long polymerTempOrderTime = polymerTempOrderMapper.queryPlaceOrderTime(userId);
            Long time;
            if (ObjectUtil.isNotEmpty(polymerInsteadOrderTime) && ObjectUtil.isNotEmpty(polymerTempOrderTime)) {
                time = polymerInsteadOrderTime > polymerTempOrderTime ? polymerInsteadOrderTime : polymerTempOrderTime;
            } else if (ObjectUtil.isEmpty(polymerInsteadOrderTime)) {
                time = polymerTempOrderTime;
            } else {
                time = polymerInsteadOrderTime;
            }
            time = ObjectUtil.isEmpty(time) ? 0 : time;
            list.add(QueryPlaceOrderTimeDTO.builder().userId(userId).placeOrderTime(time).build());
        }
        System.out.println("最近下单时间(集合):\t"+list);
        return list;
    }

    public static void main(String[] args) {
        Map<Long, Long> map = new TreeMap<Long, Long>(Comparator.reverseOrder());
        map.put(34L, 1L);
        map.put(54L, 0L);
        map.put(24L, 7L);
        map.put(4L, 153L);
        System.out.println(map);
    }
}
