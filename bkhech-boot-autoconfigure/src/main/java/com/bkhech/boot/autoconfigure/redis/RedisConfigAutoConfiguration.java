package com.bkhech.boot.autoconfigure.redis;

import com.bkhech.boot.configure.redis.RedisConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisOperations;

/**
 * Description: RedisConfigAutoConfiguration
 * @author guowm 2018/10/12
 */
@Configuration
@ConditionalOnClass(RedisOperations.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@Import(RedisConfig.class)
public class RedisConfigAutoConfiguration {
}
