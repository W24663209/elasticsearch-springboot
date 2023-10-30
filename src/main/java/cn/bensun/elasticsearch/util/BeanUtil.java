package cn.bensun.elasticsearch.util;


import cn.bensun.elasticsearch.domain.Collection;
import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author weizongtang
 * @Description bean工具
 * @CreateTime 2023/03/20 13:15:29
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {
    private BeanUtil() {
    }

    private static Map<String, Class> tables = new HashMap<>();
    private static AtomicBoolean initFlag = new AtomicBoolean();

    private static void init() throws Exception {
        ClassLoader classLoader = BeanUtil.class.getClassLoader();
        Class clazz = ClassLoader.class;
        Field classes = clazz.getDeclaredField("classes");
        classes.setAccessible(true);
        Vector<Class<?>> classVector = (Vector<Class<?>>) classes.get(classLoader);//并发修改异常
        for (int i = 0; i < classVector.size(); i++) {
            try {
                if (classVector.get(i).isAnnotationPresent(Document.class)) {
                    Document document = classVector.get(i).getAnnotation(Document.class);
                    tables.put(document.indexName(), classVector.get(i));
                }
            } catch (Exception e) {
            }
        }
        initFlag.getAndSet(true);
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
        if (!initFlag.get()) {
            try {
                init();
            } catch (Exception e) {
            }
        }
        return tables.get(tableName);
    }
}
