package com.bkhech.boot.sample.netty.server;

import com.bkhech.boot.configure.netty.NettyEvent;
import com.bkhech.boot.configure.netty.NettyServer;
import com.bkhech.boot.sample.netty.constants.RealTimeEvent;
import com.bkhech.boot.sample.netty.dto.RealTimeDto;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Description: RealTimeServer
 * @author guowm 2018/9/26
 */
@Component
@Slf4j
public class RealTimeServer {

    private static HashedWheelTimer timer = new HashedWheelTimer();

    @Autowired
    private NettyServer<RealTimeDto, String> nettyServer;

    @Autowired
    private RealTimeListener realTimeListener;

    /**
     * 启动netty服务
     */
    @PostConstruct
    public void startServer() {
        NettyEvent nettyEvent = new NettyEvent(RealTimeEvent.SOCKET_EVENT, RealTimeDto.class, realTimeListener);
        nettyServer.start(nettyEvent);
        startTimer();
    }

    /**
     * 每隔一定周期，server主动推送数据
     */
    private void startTimer() {
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                nettyServer.sendEvent(RealTimeEvent.SOCKET_EVENT, request -> "this is timer response data");
                timer.newTimeout(this, 3000, TimeUnit.MILLISECONDS);
            }
        }, 10, TimeUnit.SECONDS);
        log.info("{} Timer Started", RealTimeEvent.SOCKET_EVENT);
    }
}
