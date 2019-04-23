package com.bkhech.boot.configure.common.code;


/**
 * Created by guowm on 2018/5/3.
 */
public class StatusCode {

    public static final StatusCode UNKNOWN        = Of(-2, "unknown error");
    public static final StatusCode FAILED         = Of(-1, "failed");
    public static final StatusCode OK             = Of(0, "successful");
    public static final StatusCode EMPTY          = Of(1, "response is empty");
    public static final StatusCode NOT_SUPPORT    = Of(2, "not support");
    public static final StatusCode HTTP_ERROR     = Of(3, "http error");
    public static final StatusCode PARAMETER_NULL = Of(4, "parameter is null");
    public static final StatusCode PARAMETER_ERROR= Of(5, "parameter is error");
    public static final StatusCode SIGN_NULL      = Of(6, "missing the sign");
    public static final StatusCode SIGN_ERROR     = Of(7, "sign is error");
    public static final StatusCode TOKEN_NULL     = Of(8, "missing the access token");
    public static final StatusCode TOKEN_ERROR    = Of(9, "token invalid");
    public static final StatusCode HEADER_NULL    = Of(10, "missing request header");
    public static final StatusCode NO_PERMISSION  = Of(11, "no permission");

    private int code;
    private String message;

    public StatusCode() {}

    public StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    protected static StatusCode Of(int code, String message) {
        return new StatusCode(code, message);
    }

}
