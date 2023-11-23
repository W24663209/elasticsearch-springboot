package cn.bensun.elasticsearch.util;

import cn.bensun.elasticsearch.domain.CallBackLogMsg;
import cn.bensun.elasticsearch.domain.LogMsg;
import cn.bensun.elasticsearch.domain.ReqLogMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    static {
        setTable(LogMsg.class);
        setTable(CallBackLogMsg.class);
        setTable(ReqLogMsg.class);
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
        log.info("tableName:{}",tableName);
        Class aClass = tables.get(tableName);
        log.info("class:{}",aClass);
        return aClass;
    }


    private static final char DOT = '.';
    private static final char SLASH = '/';
    private static final String CLASS_SUFFIX = ".class";

    public static List<Class<?>> findClasses(String packageName) {
        String baseDir = packageName.replace(DOT, SLASH);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String classpath = classLoader.getResource(baseDir).getPath();

        return findClasses(new File(classpath), packageName);
    }

    private static List<Class<?>> findClasses(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(CLASS_SUFFIX)) {
                try {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - CLASS_SUFFIX.length());
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    // 处理异常
                }
            }
        }

        return classes;
    }
}
