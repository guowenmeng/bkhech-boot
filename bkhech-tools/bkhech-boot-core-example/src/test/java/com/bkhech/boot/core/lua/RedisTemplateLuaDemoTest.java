package com.bkhech.boot.core.lua;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTemplateLuaDemoTest {

    @Autowired
    RedisTemplateLuaDemo redisTemplateLuaDemo;

    @Test
    void redisLua() {
        redisTemplateLuaDemo.redisLua();
    }

    @Test
    void redisLuaWithScriptString() {
        redisTemplateLuaDemo.redisLuaWithScriptString();
    }
}