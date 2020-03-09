package com.bkhech.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RateLimiterDistributedApplication {


    public static void main(String[] args) {
        SpringApplication.run(RateLimiterDistributedApplication.class, args);
    }

}
