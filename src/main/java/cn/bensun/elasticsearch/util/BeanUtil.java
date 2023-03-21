package cn.bensun.elasticsearch.util;


import cn.bensun.elasticsearch.domain.Collection;
import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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

    private static void init() {
        ClassLoader classLoader = BeanUtil.class.getClassLoader();
        Class clazz = ClassLoader.class;
        try {
            Field classes = clazz.getDeclaredField("classes");
            classes.setAccessible(true);
            Vector<Class<?>> classVector = (Vector<Class<?>>) classes.get(classLoader);//并发修改异常
            List<Class<?>> collect = classVector.stream().collect(Collectors.toList());
            for (Class<?> aClass : collect) {
                if (aClass.isAnnotationPresent(Document.class)) {
                    Document document = aClass.getAnnotation(Document.class);
                    tables.put(document.indexName(), aClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            init();
        }
        return tables.get(tableName);
    }
}
