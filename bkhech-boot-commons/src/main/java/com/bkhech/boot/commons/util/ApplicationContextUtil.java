package com.bkhech.boot.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.util.Map;

/**
 * Created by guowm on 2016/10/28.
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);

    private static ApplicationContext applicationContext;

    private static StringValueResolver stringValueResolver;

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new RuntimeException("can't get ApplicationContext");
        }
        return applicationContext;
    }

    /**
     * 获取对象
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 获取对象
     * @param clazz
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }
    public static <T> T getBean(Class<T> superClass, String name) {
        Map<String, T> map = ApplicationContextUtil.getApplicationContext().getBeansOfType(superClass);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                if (entry.getKey().equals(name)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 动态获取配置文件中的值
     * @param name
     * @return
     */
    public static String getPropertiesValue(String name) {
        try {
            return applicationContext.getEnvironment().getProperty(name);
//            return stringValueResolver.resolveStringValue( "${" + name + "}");
        } catch (Exception e) {
            logger.error("环境变量中没有名为“{}”的配置", name);
            // 若获取失败则返回null
            return null;
        }
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        ApplicationContextUtil.stringValueResolver = stringValueResolver;
    }

}
