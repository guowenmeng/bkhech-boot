package com.bkhech.boot.configure.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/12/9
 */
public class IpUtil {

    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public static String getRealClientAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

}
