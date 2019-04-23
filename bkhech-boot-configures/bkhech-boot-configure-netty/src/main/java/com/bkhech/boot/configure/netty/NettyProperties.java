package com.bkhech.boot.configure.netty;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * NettyProperties
 *
 * Created by guowm on 18-5-24.
 */
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    private String hostname = "localhost";
    private Integer port = 8080;
    private String context = "/socket.io";


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}
