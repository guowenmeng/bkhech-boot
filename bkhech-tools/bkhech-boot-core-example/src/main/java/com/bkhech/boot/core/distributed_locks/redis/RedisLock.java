package com.bkhech.boot.core.distributed_locks.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate lock
 * redis server 单机情况下没什么大问题，但是还存在锁续期问题，集群情况下还是有问题，慎用。
 * 可选用Redisson sdk 解决
 *
 * @see {@literal https://niceyoo.blog.csdn.net/article/details/109326322}
 *      {@literal https://niceyoo.blog.csdn.net/article/details/109326247}
 * @author guowm
 * @date 2021/9/3
 */
@Slf4j
@Component
public class RedisLock {

    private final StringRedisTemplate redisTemplate;

    private final DefaultRedisScript<Long> redisScript;

    public RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/lua/release_lock.lua")));
    }

    /**
     * lock
     * @param key
     * @param value 每次的value值不要相同（可使用snowflake或者uuid生成的值），防止相同key，一个线程加的锁，被另外一个线程解锁。
     *              在解锁的lua脚本中，把加锁的value值传给解锁方法，校验当value值相等的时候，才允许解锁。
     * @return
     */
    public boolean lock(String key, String value) {
        return this.lock(key, value, 20_000);
    }

    /**
     * lock
     * @param key
     * @param value
     * @param timeoutMs 锁自动过期时间
     * @return
     */
    public boolean lock(String key, String value, long timeoutMs) {
        final Boolean ret = redisTemplate.opsForValue().setIfAbsent(key, value, timeoutMs, TimeUnit.MILLISECONDS);
        return ret != null && ret;
    }

    /**
     * unlock
     * @param key
     * @param value 加锁的value值。作用：校验当value值相等的时候，才允许解锁。
     * @return
     */
    public boolean unlock(String key, String value) {
        Long count = redisTemplate.execute(redisScript, List.of(key), value);
        return count != null && count == 1;
    }




}
