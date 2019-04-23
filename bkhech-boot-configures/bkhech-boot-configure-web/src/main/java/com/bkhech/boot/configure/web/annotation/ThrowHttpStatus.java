package com.bkhech.boot.configure.web.annotation;

import java.lang.annotation.*;

/**
 *
 * Created by guowm on 2017/4/6.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThrowHttpStatus {

}
