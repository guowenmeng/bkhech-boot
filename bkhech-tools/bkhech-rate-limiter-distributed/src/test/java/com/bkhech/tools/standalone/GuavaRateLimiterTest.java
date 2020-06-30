package com.bkhech.tools.standalone;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class GuavaRateLimiterTest {

    GuavaRateLimiter guavaRateLimiter = new GuavaRateLimiter();

    CountDownLatch countDownLatch = new CountDownLatch(1);


    @Test
    public void rateLimiter() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        Runnable task = () -> {
//            try {
//                countDownLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            guavaRateLimiter.rateLimiter();
        };

        IntStream.range(1, 30).forEach(i -> {
            executorService.execute(task);
        });

//        countDownLatch.countDown();
        System.in.read();

    }
}