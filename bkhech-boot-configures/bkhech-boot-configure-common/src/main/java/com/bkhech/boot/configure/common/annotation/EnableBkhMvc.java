package com.bkhech.boot.configure.common.annotation;

import com.bkhech.boot.configure.common.builder.chart.configuration.ChartConfig;
import com.bkhech.boot.configure.common.builder.chart.configuration.ContextConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
*  EnableBkhMvc
*  @author  guowm 2018/9/20
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({ChartConfig.class, ContextConfig.class})
public @interface EnableBkhMvc {


}
