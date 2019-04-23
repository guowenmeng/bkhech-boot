package com.bkhech.boot.configure.common.dto;

import java.io.Serializable;

/**
 * GlobalHeader
 *
 * Created by guowm on 18-5-22.
 */
public class GlobalHeader implements Serializable {

    private static final long serialVersionUID = 6326441175181163611L;

    public static final String KEY_CONTENT_TYPE = "Content-Type";
    public static final String KEY_API_VERSION = "Api-Version";
    public static final String KEY_CLIENT_IP = "Client-Ip";
    public static final String KEY_ACCESS_TOKEN = "Access-Token";
    public static final String KEY_TIMESTAMP = "Timestamp";
    public static final String KEY_APP_ID = "App-Id";
    public static final String KEY_USER_ID = "User-Id";
    public static final String KEY_GAME_ID = "Game-Id";
    public static final String KEY_IS_ADMIN = "Is-Admin";

    private String api_version;
    private String client_ip;
    private String access_token;
    private Long timestamp;
    private String content_type;
    private Integer app_id;
    private Integer user_id;
    private Integer game_id;
    private Boolean is_admin;

    public GlobalHeader() { }

    public GlobalHeader(String content_type, String api_version, String client_ip, String access_token, Long timestamp, Integer app_id, Integer user_id, Integer game_id, Boolean is_admin) {
        this.content_type = content_type;
        this.api_version = api_version;
        this.client_ip = client_ip;
        this.access_token = access_token;
        this.timestamp = timestamp;
        this.app_id = app_id;
        this.user_id = user_id;
        this.game_id = game_id;

        this.is_admin = is_admin;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }
    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }
}
