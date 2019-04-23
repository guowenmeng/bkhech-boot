package com.bkhech.boot.configure.common.exception;

import com.bkhech.boot.configure.common.code.StatusCode;


/**
 * Created by guowm on 2018/5/3.
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -73567508601044861L;

    private int code;

    public SystemException() {}

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public <T extends StatusCode> SystemException(T statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
    }

    public <T extends StatusCode> SystemException(T statusCode, String message) {
        super(statusCode.getMessage() + ": " + message);
        this.code = statusCode.getCode();
    }

    public SystemException(String message) {
        super(message);
        this.code = StatusCode.UNKNOWN.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
