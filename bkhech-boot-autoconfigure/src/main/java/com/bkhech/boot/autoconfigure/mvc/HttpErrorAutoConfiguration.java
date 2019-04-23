package com.bkhech.boot.autoconfigure.mvc;

import com.bkhech.boot.configure.mvc.error.HttpErrorConfig;
import com.bkhech.boot.configure.mvc.error.HttpErrorHandler;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * HttpErrorAutoConfigure
 *
 * Created by guowm on 18-5-4.
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties(ResourceProperties.class)
@Import({HttpErrorConfig.class, HttpErrorHandler.class})
public class HttpErrorAutoConfiguration {
    
}
