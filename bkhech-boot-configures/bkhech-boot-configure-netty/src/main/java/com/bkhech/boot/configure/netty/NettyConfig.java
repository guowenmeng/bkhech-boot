package com.bkhech.boot.configure.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * NettyConfig
 *
 * Created by guowm on 18-5-24.
 */
@Configuration
public class NettyConfig {

    @Autowired
    private NettyProperties properties;

    @Bean
    public NettyServer nettyServer() {
        return new NettyServer(properties);
    }

}
