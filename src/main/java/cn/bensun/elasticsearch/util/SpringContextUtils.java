package cn.bensun.elasticsearch.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: SpringContextUtils
 * @Author: lucky
 * @Version: 1.0
 * @Date: 2020-11-25 10:50:13
 * @Modify
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.applicationContext == null) {
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    /**
     * @apiNote 获取applicationContext
     * @author hongsir 2017/11/3 19:40:00
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @apiNote 通过name获取 Bean.
     * @author hongsir 2017/11/3 19:39:00
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * @apiNote 通过class获取Bean.
     * @author hongsir 2017/11/3 19:39:00
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * @apiNote 通过name, 以及Clazz返回指定的Bean
     * @author hongsir 2017/11/3 19:39:00
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * @Description 获取当前环境
     * @author weizongtang
     * @Date 2020/12/22 10:12:43
     */
    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
