package com.bkhech.boot.autoconfigure.mybatis;

import com.bkhech.boot.configure.mybatis.MybatisConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * MyBatisAutoConfigure
 *
 * Created by guowm on 18-5-7.
 */
@Configuration
@ConditionalOnClass(MybatisAutoConfiguration.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@Import(MybatisConfig.class)
public class MyBatisAutoConfiguration {
    
}
