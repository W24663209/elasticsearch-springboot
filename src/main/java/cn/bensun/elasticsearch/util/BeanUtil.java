package cn.bensun.elasticsearch.util;

import cn.bensun.elasticsearch.domain.LogMsg;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weizongtang
 * @Description bean工具
 * @CreateTime 2023/03/20 13:15:29
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {
    private BeanUtil() {
    }

    private static Map<String, Class> tables = new HashMap<>();

    static {
        setTable(LogMsg.class);
    }

    public static void setTable(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Document.class)) {
            Document document = clazz.getAnnotation(Document.class);
            tables.put(document.indexName(), clazz);
        }
    }

    /**
     * @Description
     * @author weizongtang
     * @CreateTime 2023/03/20 13:21:35
     */
    public static Class getTable(String tableName) {
        return tables.get(tableName);
    }
}
