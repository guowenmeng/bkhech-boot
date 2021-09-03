package com.bkhech.boot.core.distributed_locks.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * RedisTemplate lock 使用示例
 * @author guowm
 * @date 2021/9/3
 */
@Slf4j
@Component
public class RedisLockDemo {

    private final StringRedisTemplate redisTemplate;

    private final String RELEASE_LOCK_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0; end";
    private final DefaultRedisScript<Long> redisScript;

    public RedisLockDemo(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        /**
         * 加载lua脚本并设置返回值类型
         *
         * 一定要设置返回值类型，不然会报错
         * org.springframework.data.redis.RedisSystemException: Redis exception; nested exception is io.lettuce.core.RedisException: java.lang.IllegalStateException
         */
        this.redisScript = new DefaultRedisScript<>(RELEASE_LOCK_LUA, Long.class);
    }

    public void test() {
        // 方式一：
//		final Long execute = redisTemplate.execute(redisScript, List.of("1"), "1");
//		System.out.println(execute);

        // 方式二：
        final Long execute = redisTemplate.execute((RedisCallback<Long>) connection -> connection.eval(
                RELEASE_LOCK_LUA.getBytes(StandardCharsets.UTF_8),
                ReturnType.INTEGER,
                1,
                "1".getBytes(StandardCharsets.UTF_8),
                "1".getBytes(StandardCharsets.UTF_8)
        ));
        System.out.println(execute);
    }

}
