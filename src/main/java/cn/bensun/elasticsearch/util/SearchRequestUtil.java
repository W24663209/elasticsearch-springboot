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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
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

    private static RestHighLevelClient restHighLevelClient;

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        SearchRequestUtil.restHighLevelClient = restHighLevelClient;
    }

    public static <T> TableDataInfo search(T obj, ColumnUtil.SFunction<T, ?> fn) throws Exception {
        List<T> list = new ArrayList<>();
        Class<?> clazz = obj.getClass();
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
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(org.springframework.data.elasticsearch.annotations.Field.class)) {
                org.springframework.data.elasticsearch.annotations.Field fieldAnnotation = field.getAnnotation(org.springframework.data.elasticsearch.annotations.Field.class);
                Object value = field.get(obj);
                if (ObjectUtil.isNotEmpty(value)) {
                    searchSourceBuilder.query(QueryBuilders.matchQuery(fieldAnnotation.name(), value));
                }
            }
        }
        List<Object> searchTime = new ArrayList<>();
        for (Field field : clazz.getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (ObjectUtil.isNotEmpty(value)) {
                if ("searchStartTime".equalsIgnoreCase(field.getName())) {
                    searchTime.add(value);
                    //searchSourceBuilder.query(QueryBuilders.rangeQuery("created_time").from(value));
                } else if ("searchEndTime".equalsIgnoreCase(field.getName())) {
                    searchTime.add(value);
                    //searchSourceBuilder.query(QueryBuilders.rangeQuery("created_time").to(value));
                }
            }
        }
        if (searchTime.size() == 1) {
            searchSourceBuilder.query(QueryBuilders.rangeQuery("created_time").gte(searchTime.get(0)));
        } else if (searchTime.size() == 2) {
            searchSourceBuilder.query(QueryBuilders.rangeQuery("created_time").gte(searchTime.get(0)).lt(searchTime.get(1)));
        }
        searchSourceBuilder.sort(new FieldSortBuilder(ColumnUtil.getFieldName(fn)).order(SortOrder.DESC));
        int pageNumber = pageNumberField.getInt(obj);
        int pageSize = pageSizeField.getInt(obj);
        searchSourceBuilder.from((pageNumber - 1) * pageSize).size(pageSize);
        searchRequest.source(searchSourceBuilder);
        log.info("查询mapping:{}", searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //计算总页数
        long totalCount = searchResponse.getHits().getTotalHits();
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            JSONObject jsonObject = JSONObject.parseObject(hit.getSourceAsString());
            list.add((T) JSON.to(clazz, jsonObject));
        }
        return TableDataInfo.getInstance(list, totalCount);
    }

    public static void main(String[] args) {

    }
}
