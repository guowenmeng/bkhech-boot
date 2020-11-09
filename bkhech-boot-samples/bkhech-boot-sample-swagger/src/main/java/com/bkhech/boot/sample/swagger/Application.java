package com.bkhech.boot.sample.swagger;

import com.bkhech.boot.configure.mvc.annotation.EnableHttpLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guowm
 * @date 2020/11/9
 * @description
 */
@SpringBootApplication
@EnableHttpLog
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
