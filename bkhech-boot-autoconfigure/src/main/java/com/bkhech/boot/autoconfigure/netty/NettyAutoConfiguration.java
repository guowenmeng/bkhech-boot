package com.bkhech.boot.autoconfigure.netty;

import com.corundumstudio.socketio.SocketIOServer;
import com.bkhech.boot.configure.netty.NettyConfig;
import com.bkhech.boot.configure.netty.NettyProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * NettyAutoConfiguration
 *
 * Created by guowm on 18-5-24.
 */
@Configuration
@ConditionalOnClass(SocketIOServer.class)
@Import({NettyConfig.class, NettyProperties.class})
public class NettyAutoConfiguration {
    
}
