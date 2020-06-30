package com.bkhech.tools.distributed;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author guowm
 * @date 2019/12/11
 * @description
 */
@Component
public class RedisRateLimiterImpl implements RedisRateLimiter{

    private RedisTemplate<String, String> redisTemplate;

    public RedisRateLimiterImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean rateLimiter() {
//        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
//        hashOperations.put("guowm", "g", "w");
        //设置时间step
//        redisTemplate.expire("guowm", 10_000, TimeUnit.MILLISECONDS);
        //设置时间结束点
//        redisTemplate.expireAt("guowm", new Date(1593482482000L));



        return true;
    }

}
