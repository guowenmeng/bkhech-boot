package com.bkhech.boot.sample.common;

import com.bkhech.boot.configure.common.annotation.EnableBkhMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: Application
 * @author guowm 2018/9/21
 */
@SpringBootApplication
@EnableBkhMvc
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
