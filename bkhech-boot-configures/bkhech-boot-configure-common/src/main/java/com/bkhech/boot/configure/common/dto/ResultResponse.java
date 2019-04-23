package com.bkhech.boot.configure.common.dto;

import com.alibaba.fastjson.JSON;
import com.bkhech.boot.configure.common.code.StatusCode;
import com.bkhech.boot.configure.common.constants.GlobalConstants;
import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

/**
 * Description: ResultResponse
 * kdc自定义响应对象
 * @author guowm 2018/9/19
 */
public class ResultResponse<V> extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = -2746096075868445005L;

    public ResultResponse() {
        this(StatusCode.UNKNOWN);
    }

    public <T extends StatusCode> ResultResponse(T statusCode) {
        this(statusCode.getCode(), statusCode.getMessage());
    }

    public <T extends StatusCode> ResultResponse(T statusCode, String msg) {
        this(statusCode.getCode(), msg);
    }

    public <T extends StatusCode> ResultResponse(T statusCode, V data) {
        this(statusCode.getCode(), statusCode.getMessage(), data);
    }

    public <T extends StatusCode> ResultResponse(Integer code, String msg) {
        this(code, msg, null);
    }

    public <T extends StatusCode> ResultResponse(Integer code, String msg, V data) {
        Preconditions.checkNotNull(code, "code is null");
        put(GlobalConstants.CODE, code);
        put(GlobalConstants.MSG, msg);
        put(GlobalConstants.DATA, data);
    }


    // Static methods start......

    /**
     * -------------- success 成功-------------
     */
    public static ResultResponse success() {
        return new ResultResponse(StatusCode.OK);
    }

    public static <V> ResultResponse<V> success(V data) {
        return new ResultResponse(StatusCode.OK, data);
    }

    /**
     * --------------------failed 失败-------------------
     */
    public static ResultResponse failed() {
        return new ResultResponse(StatusCode.FAILED);
    }

    public static ResultResponse failed(String msg) {
        return new ResultResponse(StatusCode.FAILED, msg);
    }

    /**
     * --------------------custom 自定义-------------------
     */
    public static <V, T extends StatusCode> ResultResponse<V> custom(T statusCode, Supplier<V> supplier) {
        return new ResultResponse(statusCode, supplier.get());
    };

    public static <V, T extends StatusCode> ResultResponse<V> custom(T statusCode, V data) {
        return new ResultResponse(statusCode, data);
    };

    public static <T extends StatusCode> ResultResponse custom(T statusCode) {
        return new ResultResponse(statusCode);
    };

    public static <V> ResultResponse<V> custom(int code, String msg, V data) {
        return new ResultResponse(code, msg, data);
    };

    public static ResultResponse custom(int code, String msg) {
        return new ResultResponse(code, msg);
    };

    // Static methods end......


    public Boolean isSuccess() {
        return (int)this.get(GlobalConstants.CODE) == 0;
    }

    public int getCode() {
        return (int)this.get(GlobalConstants.CODE);
    }

    public String getMsg() {
        return (String)this.get(GlobalConstants.MSG);
    }

    public Object getData() {return this.get(GlobalConstants.DATA);}

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

