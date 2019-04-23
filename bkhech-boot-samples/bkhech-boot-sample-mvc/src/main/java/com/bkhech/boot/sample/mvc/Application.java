package com.bkhech.boot.sample.mvc;

import com.bkhech.boot.configure.mvc.annotation.EnableHttpLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * Created by guowm on 18-5-3.
 */
@SpringBootApplication
@EnableHttpLog
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
