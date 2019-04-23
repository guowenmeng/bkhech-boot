package com.bkhech.boot.configure.mvc.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * RestInterceptorConfig
 *
 * Created by guowm on 18-5-24.
 */
@Configuration
public class RestInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public RestHeaderInterceptor restHeaderInterceptor() {
        return new RestHeaderInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(restHeaderInterceptor());
    }

}
