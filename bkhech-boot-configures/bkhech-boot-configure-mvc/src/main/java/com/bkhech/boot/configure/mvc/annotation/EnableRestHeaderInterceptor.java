package com.bkhech.boot.configure.mvc.annotation;

import com.bkhech.boot.configure.mvc.rest.RestInterceptorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableRestHeaderInterceptor
 *
 * Created by guowm on 18-5-24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(RestInterceptorConfig.class)
public @interface EnableRestHeaderInterceptor {
    
}
