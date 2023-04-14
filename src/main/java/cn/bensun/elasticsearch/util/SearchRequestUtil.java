package cn.bensun.elasticsearch.util;

import cn.bensun.elasticsearch.domain.TableDataInfo;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weizongtang
 * @Description 查询工具
 * @CreateTime 2023/03/16 14:09:22
 */
@Slf4j
@Component
public class SearchRequestUtil {

    private static RestHighLevelClient restHighLevelClient;

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        SearchRequestUtil.restHighLevelClient = restHighLevelClient;
    }

    /**
     * @Description 列表查询
     * @author weizongtang
     * @CreateTime 2023/03/17 15:51:09
     */
    public static <T> TableDataInfo searchList(Class<T> clazz, T obj) throws Exception {
        List<T> list = new ArrayList<>();
        Field pageNumberField = clazz.getSuperclass().getDeclaredField("pageNumber");
        pageNumberField.setAccessible(true);
        Field pageSizeField = clazz.getSuperclass().getDeclaredField("pageSize");
        pageSizeField.setAccessible(true);
        Document annotation = clazz.getAnnotation(Document.class);
        SearchRequest searchRequest = new SearchRequest(annotation.indexName());
        searchRequest.types(annotation.type());
        // termQuery: 精确查询
        // SpanTermQuery: 词距查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(org.springframework.data.elasticsearch.annotations.Field.class)) {
                org.springframework.data.elasticsearch.annotations.Field fieldAnnotation = field.getAnnotation(org.springframework.data.elasticsearch.annotations.Field.class);
                Object value = field.get(obj);
                if (ObjectUtil.isNotEmpty(value)) {
                    boolQueryBuilder.must(QueryBuilders.matchQuery(fieldAnnotation.name(), String.valueOf(value)));
                }
            }
        }
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
        if (searchTime.size() == 1) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("created_time").gte(searchTime.get(0)));
        } else if (searchTime.size() == 2) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("created_time").gte(searchTime.get(0)).lt(searchTime.get(1)));
        }
        if (paySearchTime.size() == 1) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("pay_time").gte(paySearchTime.get(0)));
        } else if (paySearchTime.size() == 2) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("pay_time").gte(paySearchTime.get(0)).lt(paySearchTime.get(1)));
        }
        searchSourceBuilder.sort(new FieldSortBuilder("created_time").order(SortOrder.DESC));
        int pageNumber = pageNumberField.getInt(obj);
        int pageSize = pageSizeField.getInt(obj);
        if (pageNumber != -1) {
            searchSourceBuilder.from((pageNumber - 1) * pageSize).size(pageSize);
        }
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        log.info("查询mapping:{}", searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //计算总页数
        long totalCount = searchResponse.getHits().getTotalHits();
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            JSONObject jsonObject = JSONObject.parseObject(hit.getSourceAsString());
            list.add(JSON.to(clazz, jsonObject));
        }
        return TableDataInfo.getInstance(list, totalCount);
    }


    /**
     * @Description 聚合查询
     * @author weizongtang
     * @CreateTime 2023/03/17 15:53:14
     */
    public static <T> T searchAggregation(Class<?> clazz, T obj) throws Exception {
        Document annotation = clazz.getAnnotation(Document.class);
        SearchRequest searchRequest = new SearchRequest(annotation.indexName());
        searchRequest.types(annotation.type());
        // termQuery: 精确查询
        // SpanTermQuery: 词距查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<Field> fields = new ArrayList<>();
        ClassLoader classLoader = clazz.getClassLoader();
        while (classLoader.equals(clazz.getClassLoader())) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                fields.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        {
            for (Field field : fields) {
                if (field.isAnnotationPresent(org.springframework.data.elasticsearch.annotations.Field.class)) {
                    org.springframework.data.elasticsearch.annotations.Field fieldAnnotation = field.getAnnotation(org.springframework.data.elasticsearch.annotations.Field.class);
                    Object value = field.get(obj);
                    if (ObjectUtil.isNotEmpty(value)) {
                        boolQueryBuilder.must(QueryBuilders.matchQuery(fieldAnnotation.name(), value));
                    }
                    if (field.getType().equals(Double.class)) {
                        searchSourceBuilder.aggregation(AggregationBuilders.sum(fieldAnnotation.name()).field(fieldAnnotation.name()));
                    }
                }
            }
        }
        List<Object> searchTime = new ArrayList<>();
        List<Object> paySearchTime = new ArrayList<>();
        for (Field field : fields) {
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
        if (searchTime.size() == 1) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("created_time").gte(searchTime.get(0)));
        } else if (searchTime.size() == 2) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("created_time").gte(searchTime.get(0)).lt(searchTime.get(1)));
        }
        if (paySearchTime.size() == 1) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("pay_time").gte(paySearchTime.get(0)));
        } else if (paySearchTime.size() == 2) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("pay_time").gte(paySearchTime.get(0)).lt(paySearchTime.get(1)));
        }
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        log.info("查询mapping:{}", searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        Map<String, Field> fieldMap = fields.stream().collect(Collectors.toMap(Field::getName, e -> e));
        //计算总页数
        T o = (T) obj.getClass().getDeclaredConstructor().newInstance();
        fieldMap.get("count").set(o, searchResponse.getHits().getTotalHits());
        for (Aggregation aggregation : searchResponse.getAggregations()) {
            ParsedSum parsedSum = (ParsedSum) aggregation;
            String name = parsedSum.getName();
            double value = parsedSum.getValue();
            for (Field field : fields) {
                if (field.isAnnotationPresent(org.springframework.data.elasticsearch.annotations.Field.class)) {
                    org.springframework.data.elasticsearch.annotations.Field fieldAnnotation = field.getAnnotation(org.springframework.data.elasticsearch.annotations.Field.class);
                    if (fieldAnnotation.name().equalsIgnoreCase(name)) {
                        field.set(o, value);
                    }
                }
            }
        }
        return o;
    }

    public static void main(String[] args) {

    }
}
