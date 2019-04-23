package com.bkhech.boot.configure.web.rest;

import com.bkhech.boot.configure.common.dto.GlobalHeader;

/**
 * RestHeaderPool
 *
 * Created by guowm on 18-5-24.
 */
public class RestHeaderPool {

    private static ThreadLocal<GlobalHeader> globalHeader = new ThreadLocal<>();

    public static GlobalHeader get() {
        return globalHeader.get();
    }

    public static void set(GlobalHeader header) {
        globalHeader.set(header);
    }
}
