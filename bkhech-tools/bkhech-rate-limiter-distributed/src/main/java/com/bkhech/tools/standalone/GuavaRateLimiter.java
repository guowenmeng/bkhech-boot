package com.bkhech.tools.standalone;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guowm
 * @date 2019/12/11
 * @description
 */
public class GuavaRateLimiter {

    private RateLimiter rateLimiter = RateLimiter.create(0.5);

    private AtomicInteger counter = new AtomicInteger();

    public void rateLimiter() {
        boolean acquire = rateLimiter.tryAcquire();
        if (acquire) {
            int andIncrement = counter.incrementAndGet();
            System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("mm:ss:SSS")) + "--" + andIncrement);
        }
    }

}
