package com.bkhech.boot.commons.annotation;

import com.bkhech.boot.commons.util.BeanHandler;

import java.lang.annotation.*;

/**
 * Description: 忽略对象中加该注解的属性，结合BeanHandler.java使用
 * @see  BeanHandler#getfieldListWithSuper(Class)
 * @see  BeanHandler#getfieldList(Class)
 * @author guowm 2018/9/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface TheadIgnore {
}
