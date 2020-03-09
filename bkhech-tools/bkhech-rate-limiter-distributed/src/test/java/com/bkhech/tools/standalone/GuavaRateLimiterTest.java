package com.bkhech.tools.standalone;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class GuavaRateLimiterTest {

    GuavaRateLimiter guavaRateLimiter = new GuavaRateLimiter();



    @Test
    public void rateLimiter() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable task = () -> {
            guavaRateLimiter.rateLimiter();
        };

        IntStream.range(1, 11).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(task);
        });

        System.in.read();

    }
}