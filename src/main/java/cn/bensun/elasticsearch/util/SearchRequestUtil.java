package cn.bensun.elasticsearch.util;

import cn.bensun.elasticsearch.domain.TableDataInfo;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weizongtang
 * @Description 查询工具
 * @CreateTime 2023/03/16 14:09:22
 */
@Slf4j
@Component
public class SearchRequestUtil {

    private static MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        SearchRequestUtil.mongoTemplate = mongoTemplate;
    }

    /**
     * @Description 列表查询
     * @author weizongtang
     * @CreateTime 2023/03/17 15:51:09
     */
    public static <T> TableDataInfo searchList(Class<T> clazz, T obj) throws Exception {
        Field pageNumberField = clazz.getSuperclass().getDeclaredField("pageNumber");
        pageNumberField.setAccessible(true);
        Field pageSizeField = clazz.getSuperclass().getDeclaredField("pageSize");
        pageSizeField.setAccessible(true);
        List<Object> searchTime = new ArrayList<>();
        List<Object> paySearchTime = new ArrayList<>();
        for (Field field : clazz.getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (ObjectUtil.isNotEmpty(value)) {
                if ("searchStartTime".equalsIgnoreCase(field.getName())) {
                    searchTime.add(value);
                } else if ("searchEndTime".equalsIgnoreCase(field.getName())) {
                    searchTime.add(DateUtil.timestampAdd(Long.parseLong(value.toString()), 1));
                }
                if ("searchPayStartTime".equalsIgnoreCase(field.getName())) {
                    paySearchTime.add(value);
                } else if ("searchPayEndTime".equalsIgnoreCase(field.getName())) {
                    paySearchTime.add(DateUtil.timestampAdd(Long.parseLong(value.toString()), 1));
                }
            }
        }
        Criteria criteria = null;
        if (searchTime.size() == 1) {
            criteria = Criteria.where("created_time").gte(DateUtil.longToStr(searchTime.get(0)));
        } else if (searchTime.size() == 2) {
            criteria = Criteria.where("created_time").gte(DateUtil.longToStr(searchTime.get(0))).lte(DateUtil.longToStr(searchTime.get(1)));
        }
        if (paySearchTime.size() == 1) {
            if (ObjectUtil.isNotEmpty(criteria)) {
                criteria.and("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0)));
            } else {
                criteria = Criteria.where("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0)));
            }
        } else if (paySearchTime.size() == 2) {
            if (ObjectUtil.isNotEmpty(criteria)) {
                criteria.and("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0))).lte(DateUtil.longToStr(paySearchTime.get(1)));
            } else {
                criteria = Criteria.where("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0))).lte(DateUtil.longToStr(paySearchTime.get(1)));
            }
        }
        if (ObjectUtil.isEmpty(criteria)) {
            criteria = new Criteria();
        }
        Query query = new Query(criteria);
        int page = pageNumberField.getInt(obj) - 1;
        int size = pageSizeField.getInt(obj);
        Pageable pageable = PageRequest.of(page, size);
        //条件
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(org.springframework.data.mongodb.core.mapping.Field.class)) {
                org.springframework.data.mongodb.core.mapping.Field fieldAnnotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                Object value = field.get(obj);
                if (ObjectUtil.isNotEmpty(value)) {
                    // 添加其他查询条件，如果需要
                    query.addCriteria(Criteria.where(fieldAnnotation.name()).regex(value + ".*", "i"));
                }
            }
        }
        long totalCount = mongoTemplate.count(query, clazz);
        query.with(pageable);
        query.with(Sort.by(Sort.Direction.DESC, "created_time"));
        // 将Query对象转换为MongoDB查询语句
        String queryStatement = mongoTemplate.getCollectionName(SearchRequestUtil.class) + ".find(" + query.getQueryObject() + ")";
        // 打印查询语句
        log.info("MongoDB查询语句: {}", queryStatement);
        // 执行查询，获取符合条件的结果集
        List<T> list = mongoTemplate.find(query, clazz);
        // 计算总记录数
        return TableDataInfo.getInstance(list, totalCount);
    }


    /**
     * @Description 聚合查询
     * @author weizongtang
     * @CreateTime 2023/03/17 15:53:14
     */
    public static <T> T searchAggregation(Class<?> clazz, T obj) throws Exception {
        List<Object> searchTime = new ArrayList<>();
        List<Object> paySearchTime = new ArrayList<>();
        for (Field field : clazz.getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (ObjectUtil.isNotEmpty(value)) {
                if ("searchStartTime".equalsIgnoreCase(field.getName())) {
                    searchTime.add(value);
                } else if ("searchEndTime".equalsIgnoreCase(field.getName())) {
                    searchTime.add(DateUtil.timestampAdd(Long.parseLong(value.toString()), 1));
                }
                if ("searchPayStartTime".equalsIgnoreCase(field.getName())) {
                    paySearchTime.add(value);
                } else if ("searchPayEndTime".equalsIgnoreCase(field.getName())) {
                    paySearchTime.add(DateUtil.timestampAdd(Long.parseLong(value.toString()), 1));
                }
            }
        }
        Criteria criteria = null;
        if (searchTime.size() == 1) {
            criteria = Criteria.where("created_time").gte(DateUtil.longToStr(searchTime.get(0)));
        } else if (searchTime.size() == 2) {
            criteria = Criteria.where("created_time").gte(DateUtil.longToStr(searchTime.get(0))).lte(DateUtil.longToStr(searchTime.get(1)));
        }
        if (paySearchTime.size() == 1) {
            if (ObjectUtil.isNotEmpty(criteria)) {
                criteria.and("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0)));
            } else {
                criteria = Criteria.where("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0)));
            }
        } else if (paySearchTime.size() == 2) {
            if (ObjectUtil.isNotEmpty(criteria)) {
                criteria.and("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0))).lte(DateUtil.longToStr(paySearchTime.get(1)));
            } else {
                criteria = Criteria.where("pay_time").gte(DateUtil.longToStr(paySearchTime.get(0))).lte(DateUtil.longToStr(paySearchTime.get(1)));
            }
        }
        if (ObjectUtil.isEmpty(criteria)) {
            criteria = new Criteria();
        }
//        Query query = new Query(criteria);
        //条件
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(org.springframework.data.mongodb.core.mapping.Field.class)) {
                org.springframework.data.mongodb.core.mapping.Field fieldAnnotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                Object value = field.get(obj);
                if (ObjectUtil.isNotEmpty(value)) {
                    // 添加其他查询条件，如果需要
//                    query.addCriteria(Criteria.where(fieldAnnotation.name()).regex(String.valueOf(value + ".*"), "i"));
                    criteria.and(fieldAnnotation.name()).is(value);
                }
            }
        }
        MatchOperation matchStage = Aggregation.match(criteria);
        GroupOperation groupStage = Aggregation.group("department").count().as("count");

        TypedAggregation<?> aggregation = Aggregation.newAggregation(
                clazz,
                matchStage,
                groupStage
        );
        AggregationResults<?> results =
                mongoTemplate.aggregate(aggregation, clazz, clazz);
        return (T) results.getMappedResults();
    }

    public static void main(String[] args) {

    }
}
