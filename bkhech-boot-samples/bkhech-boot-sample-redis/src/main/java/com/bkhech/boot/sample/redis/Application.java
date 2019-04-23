package com.bkhech.boot.sample.redis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: Application
 * @author guowm 2018/10/12
 */
@SpringBootApplication
public class Application implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        System.out.println("init..........");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
