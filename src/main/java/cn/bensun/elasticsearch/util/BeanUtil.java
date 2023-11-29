package cn.bensun.elasticsearch.util;


import cn.bensun.elasticsearch.domain.Collection;
import cn.bensun.elasticsearch.domain.LogMsg;
import cn.bensun.elasticsearch.domain.Payment;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author weizongtang
 * @Description bean工具
 * @CreateTime 2023/03/20 13:15:29
 */
@Slf4j
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {
    private BeanUtil() {
    }

    private static Map<String, Class> tables = new HashMap<>();
    private static AtomicBoolean initFlag = new AtomicBoolean();

    static {
        setTable(LogMsg.class);
        setTable(Collection.class);
        setTable(Payment.class);
    }

    public static void setTable(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Document.class)) {
            Document document = clazz.getAnnotation(Document.class);
            tables.put(document.collection(), clazz);
        }
    }

    public static void main(String[] args) {
        Collection collection = new Collection();
        Class t_collection = getTable("t_collection");
        System.out.println(t_collection);
    }

    /**
     * @Description
     * @author weizongtang
     * @CreateTime 2023/03/20 13:21:35
     */
    public static Class getTable(String tableName) {
        Class clazz = tables.get(tableName);
        log.info("实体类:{}", clazz);
        if (ObjectUtil.isEmpty(clazz)) {
            throw new RuntimeException("没配置实体类");
        } else {
            return clazz;
        }
    }
}
