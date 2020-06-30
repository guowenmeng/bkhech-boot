package com.bkhech.tools.standalone;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guowm
 * @date 2019/12/11
 * @description
 */
public class GuavaRateLimiter {

    //令牌桶算法
    private RateLimiter rateLimiter = RateLimiter.create(10);


    //漏桶算法
    private RateLimiter rateLimiterTong = RateLimiter.create(1, 1, TimeUnit.SECONDS);


    public void rateLimiter() {
        boolean b = rateLimiterTong.tryAcquire();
        System.out.println("b = " + b);
    }

}
