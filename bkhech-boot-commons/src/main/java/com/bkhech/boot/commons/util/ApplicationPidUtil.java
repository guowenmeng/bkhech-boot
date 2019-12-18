package com.bkhech.boot.commons.util;

import java.lang.management.ManagementFactory;

/**
 * @author guowm
 * @date 2019/12/18
 * @description
 */
public class ApplicationPidUtil {

    /**
     * 获取应用pid
     * @see //org.springframework.boot.system.ApplicationPid
     * @return
     */
    public static String getPid() {
        try {
            String jvmName = ManagementFactory.getRuntimeMXBean().getName();
            return jvmName.split("@")[0];
        }
        catch (Throwable ex) {
            return null;
        }
    }

}
