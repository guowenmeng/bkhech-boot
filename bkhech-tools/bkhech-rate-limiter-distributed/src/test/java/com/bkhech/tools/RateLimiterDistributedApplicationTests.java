package com.bkhech.tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class RateLimiterDistributedApplicationTests {

    @Autowired
    ApplicationContext context;

    //spring事件机制
    @Test
    void contextLoads() {
        GuoEvent guoEvent = new GuoEvent(context, "事件源");
        context.publishEvent(guoEvent);
    }

}
