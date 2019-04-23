package com.bkhech.boot.configure.mvc.rest;

import com.bkhech.boot.configure.common.dto.GlobalHeader;
import com.bkhech.boot.configure.web.rest.RestHeaderPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * RestInterceptorConfig
 *
 * Created by guowm on 18-5-24.
 */
public class RestHeaderInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RestHeaderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("RestHeaderInterceptor preHandle");
        String token = request.getHeader(GlobalHeader.KEY_ACCESS_TOKEN);
        String api_version = request.getHeader(GlobalHeader.KEY_API_VERSION);
        String client_ip = request.getHeader(GlobalHeader.KEY_CLIENT_IP);
        String timestampText = request.getHeader(GlobalHeader.KEY_TIMESTAMP);
        String appIdText = request.getHeader(GlobalHeader.KEY_APP_ID);
        String userIdText = request.getHeader(GlobalHeader.KEY_USER_ID);
        String gameIdText = request.getHeader(GlobalHeader.KEY_GAME_ID);
        String isAdminText = request.getHeader(GlobalHeader.KEY_IS_ADMIN);

        Long timestamp = !StringUtils.isEmpty(timestampText) ? Long.valueOf(timestampText) : null;
        Integer app_id = !StringUtils.isEmpty(appIdText) ? Integer.valueOf(appIdText) : null;
        Integer user_id = !StringUtils.isEmpty(userIdText) ? Integer.valueOf(userIdText) : null;
        Integer game_id = !StringUtils.isEmpty(gameIdText) ? Integer.valueOf(gameIdText) : null;
        Boolean is_admin = !StringUtils.isEmpty(isAdminText) ? Boolean.valueOf(isAdminText) : null;

        GlobalHeader header = new GlobalHeader(request.getContentType(), api_version, client_ip, token, timestamp, app_id, user_id, game_id, is_admin);
        RestHeaderPool.set(header);
        return true;
    }
}
