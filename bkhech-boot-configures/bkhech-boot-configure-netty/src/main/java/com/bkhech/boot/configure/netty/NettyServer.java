package com.bkhech.boot.configure.netty;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * NettyServer
 *
 * Created by guowm on 18-5-24.
 */
public class NettyServer<T extends Object, R extends Object> {

    private NettyProperties properties;

    private SocketIOServer server;

    public NettyServer(NettyProperties properties) {
        this.properties = properties;
    }

    public SocketIOServer initServer() {
        Configuration config = new Configuration();
        config.setHostname(properties.getHostname());
        config.setPort(properties.getPort());
        config.setContext(properties.getContext());
        server = new SocketIOServer(config);
        return server;
    }

    public SocketIOServer getServer() {
        if (server == null) {
            initServer();
        }
        return server;
    }

    public void start(List<NettyEvent<T>> eventList) {
        eventList.forEach(event -> getServer().addEventListener(event.getEvent(), event.getClazz(), event.getDataListener()));
        getServer().start();
    }

    public void start(NettyEvent<T> event) {
        start(Lists.newArrayList(event));
    }

    public void sendEvent(String event, R data) {
        List<SocketIOClient> socketIOClients = getServer().getAllClients().stream().filter(p -> p.get(event) != null).collect(Collectors.toList());
        socketIOClients.forEach(client -> sendEvent(client, event, data));
    }

    /**
     * e.g. sendEvent("evnet", store -> { return "this is message"; })
     *
     * @param event
     * @param function
     */
    public void sendEvent(String event, Function<T, R> function) {
        List<SocketIOClient> socketIOClients = getServer().getAllClients().stream().filter(p -> p.get(event) != null).collect(Collectors.toList());
        socketIOClients.forEach(client -> {
            T store = client.get(event);
            R data = function.apply(store);
            if (data != null) {
                sendEvent(client, event, data);
            }
        });
    }

    public void sendEvent(UUID uuid, String event, R data) {
        SocketIOClient client = getServer().getClient(uuid);
        sendEvent(client, event, data);
    }

    public void sendEvent(SocketIOClient client, String event, R data) {
        client.sendEvent(event, data);
    }

}
