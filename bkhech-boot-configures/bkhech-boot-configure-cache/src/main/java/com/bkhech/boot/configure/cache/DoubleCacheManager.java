package com.bkhech.boot.configure.cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * Description: DoubleCacheManager
 * @author guowm 2018/10/12
 */
public class DoubleCacheManager extends RedisCacheManager {

    private DoubleCacheProperties properties;

    public DoubleCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, DoubleCacheProperties properties) {
        super(cacheWriter, defaultCacheConfiguration);
        this.properties = properties;
    }

    @Override
    protected Cache decorateCache(Cache cache) {
        return new DoubleCache(properties, (RedisCache) cache);
    }

}

