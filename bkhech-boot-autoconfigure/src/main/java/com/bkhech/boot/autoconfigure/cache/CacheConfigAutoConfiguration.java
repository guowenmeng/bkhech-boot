package com.bkhech.boot.autoconfigure.cache;

import com.bkhech.boot.configure.cache.CacheConfig;
import com.bkhech.boot.configure.cache.DoubleCacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Description: CacheConfigAutoConfiguration
 * @author guowm 2018/10/12
 */
@Configuration
@ConditionalOnClass(CacheConfig.class)
@Import({CacheConfig.class, DoubleCacheProperties.class})
public class CacheConfigAutoConfiguration {
}
