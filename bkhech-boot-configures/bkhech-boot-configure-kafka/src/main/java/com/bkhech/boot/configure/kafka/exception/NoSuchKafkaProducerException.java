package com.bkhech.boot.configure.kafka.exception;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/8/13
 */
public class NoSuchKafkaProducerException extends RuntimeException {

    public NoSuchKafkaProducerException(String message)
    {
        super("no such kafka producer:" + message);
    }

}
