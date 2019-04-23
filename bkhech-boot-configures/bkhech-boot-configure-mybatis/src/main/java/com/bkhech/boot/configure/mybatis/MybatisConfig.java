package com.bkhech.boot.configure.mybatis;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bkhech.mybatis.extend.page.interceptor.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * MybatisConfig
 *
 * Created by guowm on 18-5-7.
 */
@Configuration
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    @Value("${mybatis.enable-page:true}")
    private Boolean enablePage;

    @Value("${mybatis.mapper-locations:}")
    private String mapperLocations;

    /**
     * register page interceptor
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        if (enablePage) {
            PageInterceptor pageInterceptor = new PageInterceptor();
            bean.setPlugins(new Interceptor[]{pageInterceptor});
        }

        if (!StringUtils.isEmpty(mapperLocations)) {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setMapperLocations(resolver.getResources(mapperLocations));
        }

        return bean.getObject();
    }

}
