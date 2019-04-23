package com.bkhech.boot.configure.web.log;


import com.bkhech.boot.configure.web.annotation.ThrowHttpStatus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by guowm on 2017/4/6.
 */
public class ThrowHttpStatusHandler {

    public static boolean validateThrowHttpStatus(HttpLogInfo logInfo) {
        ThrowHttpStatus throwHttpStatus = getThrowHttpStatus(logInfo.getClazz(), logInfo.getMethodName());
        return throwHttpStatus != null ? true : false;
    }

    public static ThrowHttpStatus getThrowHttpStatus(Class clazz, String methodName) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                Annotation annotation = method.getAnnotation(ThrowHttpStatus.class);
                if (annotation != null) {
                    return (ThrowHttpStatus) annotation;
                }
            }
        }
        return null;
    }
}
