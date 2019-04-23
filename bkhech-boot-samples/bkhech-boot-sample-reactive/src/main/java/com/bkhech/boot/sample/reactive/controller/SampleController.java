package com.bkhech.boot.sample.reactive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * SampleController
 *
 * Created by guowm on 18-5-3.
 */
@RestController
public class SampleController {

    @RequestMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello, reactive!");
    }

    @RequestMapping("/exception")
    public Mono<String> exception() {
        System.out.println(1 / 0);
        return Mono.just("exception");
    }

}
