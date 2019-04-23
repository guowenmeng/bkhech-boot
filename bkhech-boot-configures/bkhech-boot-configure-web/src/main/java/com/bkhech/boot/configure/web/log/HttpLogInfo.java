package com.bkhech.boot.configure.web.log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.bkhech.boot.configure.common.constants.ConfigureConstants.ERROR_PATH;

/**
 * HttpLogInfo
 *
 * Created by guowm on 18-5-8.
 */
public class HttpLogInfo {

    private static Logger logger = LoggerFactory.getLogger(HttpLogInfo.class);

    private Class       clazz;
    private String      methodName;
    private String      uri;
    private Object[]    params;
    private String      method;
    private Object      result;
    private long        beginTime;
    private long        costMilliseconds;

    public HttpLogInfo(JoinPoint point, HttpLogRequest request) {
        this.clazz      = point.getTarget().getClass();
        this.methodName = point.getSignature().getName();
        this.uri        = request.getRequestURI();
        this.method     = request.getMethod();
        this.params     = point.getArgs();
        this.beginTime  = System.currentTimeMillis();
    }

    public String getClazzName() {
        return clazz.getSimpleName();
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return shortLog(getFullParams());
    }

    public String getFullParams() {
        String result;
        try {
            result = JSON.toJSONString(params);
        } catch (Exception e) {
            result = Arrays.toString(params);
        }
        return result;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return shortLog(result);
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getCostMilliseconds() {
        return costMilliseconds;
    }

    public void setCostMilliseconds(long costMilliseconds) {
        this.costMilliseconds = costMilliseconds;
    }

    public void beforeLog() {
        if (uri.endsWith(ERROR_PATH)) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("LOG [Request  ][").append(uri).append("][").append(method).append("]");
        buffer.append(" params ").append(getParams());
        logger.info(buffer.toString());
    }

    public void afterLog(Object result) {
        if (uri.endsWith(ERROR_PATH)) {
            return;
        }
        setResult(result);
        setCostMilliseconds(System.currentTimeMillis() - getBeginTime());
        StringBuffer buffer = new StringBuffer();
        buffer.append("LOG [Response ][").append(uri).append("][").append(method).append("]");
        buffer.append(" cost: ").append(costMilliseconds).append("ms.");
        buffer.append(" result: ").append(getResult());
        logger.info(buffer.toString());
    }

    public void throwLog(String message, Throwable e) {
        if (uri.endsWith(ERROR_PATH)) {
            return;
        }
        setCostMilliseconds(System.currentTimeMillis() - getBeginTime());
        StringBuffer buffer = new StringBuffer();
        buffer.append("LOG [Exception][").append(uri).append("][").append(method).append("]");
        buffer.append(" cost: ").append(costMilliseconds).append("ms.");
        buffer.append(" message: ").append(message).append(".");
        buffer.append(" params: ").append(getFullParams());
        logger.error(buffer.toString(), e);
    }

    public String shortLog(Object param) {
        int length = 4096;

        String value = param != null ? param.toString() : null;
        if (value != null && value.length() > length) {
            value = value.substring(0, length) + "...";
        }
        return value;
    }
}
