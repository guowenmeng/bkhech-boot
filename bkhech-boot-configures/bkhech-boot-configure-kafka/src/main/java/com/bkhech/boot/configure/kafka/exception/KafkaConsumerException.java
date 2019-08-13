package com.bkhech.boot.configure.kafka.exception;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/8/13
 */
public class KafkaConsumerException extends RuntimeException {

    public KafkaConsumerException(String message) {
        super(message);
    }
}
