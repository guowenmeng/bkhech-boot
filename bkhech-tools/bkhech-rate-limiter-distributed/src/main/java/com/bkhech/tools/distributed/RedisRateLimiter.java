package com.bkhech.tools.distributed;

/**
 * @author guowm
 * @date 2019/12/11
 * @description
 *  1. redission client 已经实现
 *  2. jedis client需要自己实现
 */
public interface RedisRateLimiter {

    boolean rateLimiter();

}
