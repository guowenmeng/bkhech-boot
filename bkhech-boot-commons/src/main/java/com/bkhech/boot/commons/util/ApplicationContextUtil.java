package com.bkhech.boot.commons.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Created by guowm on 2016/10/28.
 */
public class ApplicationContextUtil {

    private static ApplicationContext context;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        if (context == null) {
            throw new RuntimeException("can't get ApplicationContext");
        }
        return context;
    }

    public static <T> T getBean(Class<T> clazz){
        return getContext().getBean(clazz);
    }

    public static Object getBean(String name){
        return getContext().getBean(name);
    }

    public static <T> T getBean(Class<T> superClass, String name) {
        Map<String, T> map = ApplicationContextUtil.getContext().getBeansOfType(superClass);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                if (entry.getKey().equals(name)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
}
