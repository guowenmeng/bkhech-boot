package com.bkhech.boot.configure.cache;

import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Description: DoubleCacheProperties
 * @author guowm 2018/10/12
 */
@ConfigurationProperties(prefix = "cache")
public class DoubleCacheProperties {

    private LocalCache localCache = new LocalCache();
    private EhCache ehCache = new EhCache();
    private RedisCache redisCache = new RedisCache();

    public LocalCache getLocalCache() {
        return localCache;
    }

    public void setLocalCache(LocalCache localCache) {
        this.localCache = localCache;
    }

    public EhCache getEhCache() {
        return ehCache;
    }

    public void setEhCache(EhCache ehCache) {
        this.ehCache = ehCache;
    }

    public RedisCache getRedisCache() {
        return redisCache;
    }

    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    public class LocalCache {
        /**
         * 是否启用本地缓存
         */
        private boolean enable = false;

        /**
         * 本地缓存初始容量
         */
        private int capacity = 5000;

        /**
         * 缓存击中次数，超过设置的值，会强制访问上一级缓存， 0代表忽略此设置
         */
        private long hitCount = 20;

        /**
         * 忽略列表，这里设置的key将不会在本地缓存
         */
        private List<String> exclusions = Lists.newArrayList();

        public boolean getEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public long getHitCount() {
            return hitCount;
        }

        public void setHitCount(long hitCount) {
            this.hitCount = hitCount;
        }

        public List<String> getExclusions() {
            return exclusions;
        }

        public void setExclusions(List<String> exclusions) {
            this.exclusions = exclusions;
        }
    }

    public class EhCache {

        private boolean enable = false;
        private long timeToIdleSeconds = 0;
        private long timeToLiveSeconds = 0;

        public boolean getEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public long getTimeToIdleSeconds() {
            return timeToIdleSeconds;
        }

        public void setTimeToIdleSeconds(long timeToIdleSeconds) {
            this.timeToIdleSeconds = timeToIdleSeconds;
        }

        public long getTimeToLiveSeconds() {
            return timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(long timeToLiveSeconds) {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }
    }

    public class RedisCache {

        private boolean enable = true;
        private String keyPrefix = "cache";
        private int expire = 86400;

        public boolean getEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getKeyPrefix() {
            return keyPrefix;
        }

        public void setKeyPrefix(String keyPrefix) {
            this.keyPrefix = keyPrefix;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }
    }

}
