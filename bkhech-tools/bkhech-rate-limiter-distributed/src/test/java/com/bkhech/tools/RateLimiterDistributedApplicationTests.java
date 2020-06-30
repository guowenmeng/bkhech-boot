package com.bkhech.tools;

import com.bkhech.tools.distributed.RedisRateLimiter;
import com.bkhech.tools.spring.event.GuoEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class RateLimiterDistributedApplicationTests {

    @Autowired
    RedisRateLimiter redisRateLimiter;

    @Test
    void redisRateLimiter() {
        boolean b = redisRateLimiter.rateLimiter();
        System.out.println("b = " + b);
    }

}
