package com.bkhech.boot.configure.netty;

import com.corundumstudio.socketio.listener.DataListener;

/**
 * NettyEvent
 *
 * Created by guowm on 18-5-24.
 */
public class NettyEvent<T> {

    private String event;
    private Class<T> clazz;
    private DataListener<T> dataListener;

    public NettyEvent() { }

    public NettyEvent(String event, Class<T> clazz, DataListener<T> dataListener) {
        this.event = event;
        this.clazz = clazz;
        this.dataListener = dataListener;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public DataListener<T> getDataListener() {
        return dataListener;
    }

    public void setDataListener(DataListener<T> dataListener) {
        this.dataListener = dataListener;
    }
}
