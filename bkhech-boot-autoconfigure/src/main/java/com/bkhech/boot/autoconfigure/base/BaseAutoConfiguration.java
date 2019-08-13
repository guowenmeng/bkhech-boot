package com.bkhech.boot.autoconfigure.base;

import com.bkhech.boot.commons.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * BaseAutoConfiguration
 * <p>
 * Created by guowm on 18-6-1.
 */
@Configuration
@ConditionalOnClass(ApplicationContextUtil.class)
public class BaseAutoConfiguration {

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() {
//        ApplicationContextUtil.setApplicationContext(context);
    }
}
