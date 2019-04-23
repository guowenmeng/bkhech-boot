package com.bkhech.boot.configure.web.rest;

import com.bkhech.boot.configure.common.dto.GlobalHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * RestConfig
 *
 * Created by guowm on 18-5-24.
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.interceptors(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                GlobalHeader globalHeader = RestHeaderPool.get();
                if (globalHeader != null) {
                    HttpHeaders headers = request.getHeaders();
                    headers.add(GlobalHeader.KEY_ACCESS_TOKEN, globalHeader.getAccess_token());
                    headers.add(GlobalHeader.KEY_API_VERSION, globalHeader.getApi_version());
                    headers.add(GlobalHeader.KEY_CLIENT_IP, globalHeader.getClient_ip());
                    headers.add(GlobalHeader.KEY_CONTENT_TYPE, globalHeader.getContent_type());
                    if (!StringUtils.isEmpty(globalHeader.getTimestamp())) {
                        headers.add(GlobalHeader.KEY_TIMESTAMP, globalHeader.getTimestamp().toString());
                    }
                    if (globalHeader.getApp_id() != null) {
                        headers.add(GlobalHeader.KEY_APP_ID, globalHeader.getApp_id().toString());
                    }
                    if (globalHeader.getUser_id() != null) {
                        headers.add(GlobalHeader.KEY_USER_ID, globalHeader.getUser_id().toString());
                    }
                    if (globalHeader.getGame_id() != null) {
                        headers.add(GlobalHeader.KEY_GAME_ID, globalHeader.getGame_id().toString());
                    }
                    if (globalHeader.getIs_admin() != null) {
                        headers.add(GlobalHeader.KEY_IS_ADMIN, globalHeader.getIs_admin().toString());
                    }
                }
                return execution.execute(request, body);
            }
        }).build();
    }

}
