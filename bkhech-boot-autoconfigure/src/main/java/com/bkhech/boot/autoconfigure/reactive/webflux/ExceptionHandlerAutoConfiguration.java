package com.bkhech.boot.autoconfigure.reactive.webflux;


import com.bkhech.boot.configure.reactive.webflux.ExceptionHandlers;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * ExceptionHandlerAutoConfigure
 *
 * Created by guowm on 18-5-3.
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnClass(WebFluxConfigurer.class)
//@AutoConfigureBefore(WebFluxAutoConfiguration.class)
@EnableConfigurationProperties({ ServerProperties.class, ResourceProperties.class })
//@AutoConfigureAfter(ErrorWebFluxAutoConfiguration.class)
@Import(ExceptionHandlers.class)
public class ExceptionHandlerAutoConfiguration {

//    private final ServerProperties serverProperties;
//
//    private final ApplicationContext applicationContext;
//
//    private final ResourceProperties resourceProperties;
//
//    private final List<ViewResolver> viewResolvers;
//
//    private final ServerCodecConfigurer serverCodecConfigurer;
//
//    public ExceptionHandlerAutoConfigure(ServerProperties serverProperties,
//            ResourceProperties resourceProperties,
//            ObjectProvider<List<ViewResolver>> viewResolversProvider,
//            ServerCodecConfigurer serverCodecConfigurer,
//            ApplicationContext applicationContext) {
//        this.serverProperties = serverProperties;
//        this.applicationContext = applicationContext;
//        this.resourceProperties = resourceProperties;
//        this.viewResolvers = viewResolversProvider
//                .getIfAvailable(() -> Collections.emptyList());
//        this.serverCodecConfigurer = serverCodecConfigurer;
//    }
//
//    @Bean
//    public ErrorWebExceptionHandler errorWebExceptionHandler(
//            ErrorAttributes errorAttributes) {
//        BkhechExceptionHandler exceptionHandler = new BkhechExceptionHandler(
//                errorAttributes, this.resourceProperties,
//                this.serverProperties.getError(), this.applicationContext);
//        exceptionHandler.setViewResolvers(this.viewResolvers);
//        exceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
//        exceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
//        return exceptionHandler;
//    }
//
//    @Bean
//    public DefaultErrorAttributes errorAttributes() {
//        return new DefaultErrorAttributes(
//                this.serverProperties.getError().isIncludeException());
//    }
}
