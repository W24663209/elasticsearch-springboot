package cn.bensun.elasticsearch.controller;

import cn.bensun.elasticsearch.domain.Collection;
import cn.bensun.elasticsearch.domain.TableDataInfo;
import cn.bensun.elasticsearch.enums.PayInStatusEnum;
import cn.bensun.elasticsearch.service.ICollectionService;
import cn.bensun.elasticsearch.util.DateUtil;
import cn.bensun.elasticsearch.util.SearchRequestUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weizongtang
 * @Description
 * @CreateTime 2023/03/14 18:45:19
 */
@RestController
@RequestMapping("/collection")
@Slf4j
public class CollectionController {

    @Autowired
    private ICollectionService collectionService;

    /**
     * @Description 查询代收列表
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/14 18:45:16
     */
    @GetMapping("/list")
    @ApiOperation("查询代收列表")
    public TableDataInfo list(Collection collection) throws Exception {
        return SearchRequestUtil.search(collection, Collection::getCreatedTime);
    }

    /**
     * @Description 获取天账单
     * @CreatedBy weizongtang
     * @CreateTime 2023/03/17 14:45:33
     */
    @GetMapping("/getDayBillBySuccess")
    @ApiOperation(value = "getDayBillBySuccess")
    public List<Collection> getDayBillBySuccess() throws Exception {
        List<Collection> channelBalanceDays = new ArrayList<>();
        for (Long date : DateUtil.getRecentMonth()) {
            Collection collection = new Collection();
            collection.setSearchStartTime(date);
            collection.setSearchEndTime(DateUtil.timestampAdd(date, 1));
            collection.setStatus(PayInStatusEnum.已支付.getCode());
            collection.setChannelNo("DS1112");
            List<Collection> collections = SearchRequestUtil.searchAggregation(collection,
                    Collection::getAmount,
                    Collection::getRealAmount,
                    Collection::getIncome,
                    Collection::getMerchantFee,
                    Collection::getFee,
                    Collection::getMerchantSettle);
            channelBalanceDays.add(collections.get(0));
        }
        return channelBalanceDays;
    }
}