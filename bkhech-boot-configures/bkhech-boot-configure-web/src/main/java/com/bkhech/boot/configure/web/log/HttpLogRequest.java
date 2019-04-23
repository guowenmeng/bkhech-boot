package com.bkhech.boot.configure.web.log;

/**
 * HttpLogRequest
 *
 * Created by guowm on 18-5-24.
 */
public class HttpLogRequest {

    private String requestURI;
    private String method;

    public HttpLogRequest(String requestURI, String method) {
        this.requestURI = requestURI;
        this.method = method;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
