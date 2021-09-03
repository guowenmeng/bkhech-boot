package com.bkhech.boot.core.distributed_locks.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisLockTest {

    @Autowired
    RedisLock redisLock;

    @Test
    void test() {
        final boolean locked = redisLock.lock("1", "1");
        System.out.println(locked);
        if (locked) {
            try {
                //业务处理
                System.out.println("进行业务处理");
            } finally {
                final boolean unlocked = redisLock.unlock("1", "1");
                System.out.println(unlocked);
            }
        }
    }

}