package com.bkhech.boot.configure.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.core.serializer.support.SerializationDelegate;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.lang.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: DoubleCache
 * @author guowm 2018/10/12
 */
public class DoubleCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(DoubleCache.class);

    private DoubleCacheProperties properties;
    private RedisCache redisCache;
    private ConcurrentHashMap<Object, Object> localCache;
    private boolean enableLocal;
    private boolean enableRedis;

    protected SerializationDelegate serialization = new SerializationDelegate(getClass().getClassLoader());

    protected DoubleCache(DoubleCacheProperties properties, RedisCache redisCache) {
        this.properties = properties;
        this.redisCache = redisCache;

        this.enableLocal = properties.getLocalCache().getEnable();
        this.enableRedis = properties.getRedisCache().getEnable();

        int capacity = properties.getLocalCache().getCapacity();
        if (enableLocal) {
            capacity = capacity <= 0 ? 5000 : capacity;
            localCache = new ConcurrentHashMap<>(capacity);
        }
    }

    @Override
    public String getName() {
        return redisCache.getName();
    }

    @Override
    public Object getNativeCache() {
        return redisCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper wrapper = null;

        if (enableLocal) {
            List<String> names = properties.getLocalCache().getExclusions();

            if (!names.contains(getName())) {
                long count = properties.getLocalCache().getHitCount();
                if (count > 0) {
                    long hitCount = CacheHitCounter.getHitCount(key);

                    if (hitCount < count) {
                        wrapper = toValueWrapper(localCache.get(key));

                        if (wrapper != null) {
                            CacheHitCounter.incrementAndGet(key);
                        }
                    } else {
                        CacheHitCounter.reset(key);
                        localCache.remove(key);
                    }
                } else {
                    wrapper = toValueWrapper(localCache.get(key));
                }
            }
        }

        if (wrapper != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("get object from localCache, key: {}", key);
            }
            return wrapper;
        } else {
            if (enableRedis) {
                wrapper = redisCache.get(key);
                if (wrapper != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("get object from redisCache, key: {}", key);
                    }
                    if (enableLocal) {
                        localCache.put(key, wrapper);
                    }
                }
            }
            return wrapper;
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (value == null) {
            return;
        }

        if (enableLocal) {
            if (logger.isDebugEnabled()) {
                logger.debug("put the object to localCache, key: {}", key);
            }
            localCache.put(key, toStoreValue(value));
        }

        if (enableRedis) {
            if (logger.isDebugEnabled()) {
                logger.debug("put the object to redisCache, key: {}", key);
            }
            redisCache.put(key, value);
        }
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return redisCache.get(key, type);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return redisCache.get(key, valueLoader);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return redisCache.putIfAbsent(key, toStoreValue(value));
    }

    @Override
    public void evict(Object key) {
        if (enableLocal) {
            localCache.remove(key);
        }
        if (enableRedis) {
            redisCache.evict(key);
        }
    }

    @Override
    public void clear() {
        if (enableLocal) {
            localCache.clear();
        }
        if (enableRedis) {
            redisCache.clear();
        }
    }

    protected Object toStoreValue(@Nullable Object storeValue) {
        try {
            return serializeValue(serialization, storeValue);
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Failed to serialize cache value '" + storeValue + "'", ex);
        }
    }

    private Object serializeValue(SerializationDelegate serialization, Object storeValue) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            serialization.serialize(storeValue, out);
            return out.toByteArray();
        } finally {
            out.close();
        }
    }

    protected Object fromStoreValue(@Nullable Object storeValue) {
        try {
            return deserializeValue(this.serialization, storeValue);
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Failed to deserialize cache value '" + storeValue + "'", ex);
        }
    }

    private Object deserializeValue(SerializationDelegate serialization, Object storeValue) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream((byte[]) storeValue);
        try {
            return serialization.deserialize(in);
        } finally {
            in.close();
        }
    }

    protected ValueWrapper toValueWrapper(@Nullable Object storeValue) {
        return storeValue != null ? storeValue instanceof ValueWrapper ? (ValueWrapper) storeValue : new SimpleValueWrapper(fromStoreValue(storeValue)) : null;
    }

}
