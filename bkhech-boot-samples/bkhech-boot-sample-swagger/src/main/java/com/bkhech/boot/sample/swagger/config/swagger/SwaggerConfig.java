package com.bkhech.boot.sample.swagger.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author guowm
 * @date 2020/11/9
 * @description
 */
@EnableKnife4j
@Configuration(BeanIds.NAME)
public class SwaggerConfig implements InitializingBean {

    private final String apiDocTitle = "test_project";

    private final ApplicationContext applicationContext;

    public SwaggerConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder().title(apiDocTitle).build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bkhech.boot.sample.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Field[] declaredFields = ApiVersionConst.class.getDeclaredFields();
        // 动态注入bean
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if(autowireCapableBeanFactory instanceof DefaultListableBeanFactory){
            DefaultListableBeanFactory capableBeanFactory = (DefaultListableBeanFactory) autowireCapableBeanFactory;
            for (Field declaredField : declaredFields) {
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition()
                        .setFactoryMethodOnBean("docketWithGroupName", BeanIds.NAME)
                        .addConstructorArgValue(declaredField.get(ApiVersionConst.class))
                        .getBeanDefinition();
                capableBeanFactory.registerBeanDefinition(declaredField.getName(), beanDefinition);
            }
        }
    }

    private Docket docketWithGroupName(String groupName) {
        return new Docket(DocumentationType.OAS_30)
                .tags(new springfox.documentation.service.Tag(groupName, ""))
                .groupName(groupName)
                .apiInfo(new ApiInfoBuilder().title(apiDocTitle).build())
                .select()
                .apis(requestHandler -> {
                    Optional<ApiOperation> apiOperationOptional = requestHandler.findAnnotation(ApiOperation.class);
                    if (apiOperationOptional.isPresent()) {
                        return  Arrays.asList(apiOperationOptional.get().tags()).contains(groupName);
                    }
                    return false;
                })
                .paths(PathSelectors.any())
                .build();
    }
}
