package com.bkhech.boot.sample.netty.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.bkhech.boot.sample.netty.dto.RealTimeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.bkhech.boot.sample.netty.constants.RealTimeEvent.SOCKET_EVENT;

/**
 * Description: RealTimeListener
 * @author guowm 2018/9/26
 */
@Component
@Slf4j
public class RealTimeListener implements DataListener<RealTimeDto> {

    @Override
    public void onData(SocketIOClient socketIOClient, RealTimeDto realTimeDto, AckRequest ackRequest) throws Exception {
        log.debug("data: {}", realTimeDto);
        String responseData = "this is response data";
        socketIOClient.sendEvent(SOCKET_EVENT, responseData);
        socketIOClient.set(SOCKET_EVENT, realTimeDto);

    }
}
