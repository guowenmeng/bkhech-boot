package com.bkhech.boot.configure.mvc.annotation;

import com.bkhech.boot.configure.mvc.log.MvcHttpLogHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * Created by guowm on 2017/4/6.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(MvcHttpLogHandler.class)
public @interface EnableHttpLog {

}
