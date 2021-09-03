package com.bkhech.boot.core.lua;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RedisTemplate lua 使用示例
 * @author guowm
 * @date 2021/6/25
 */
@Slf4j
@Component
public class RedisTemplateLuaDemo {

    private final StringRedisTemplate redisTemplate;

    private final DefaultRedisScript<List> getRedisScript;

    public RedisTemplateLuaDemo(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.getRedisScript = new DefaultRedisScript<>();
        getRedisScript.setResultType(List.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/lua/limit_load_times.lua")));
    }

    public void redisLua() {
        /**
         * List设置lua的KEYS
         */
        List<String> keyList = new ArrayList();
        keyList.add("count");
        keyList.add("rate.limiting:127.0.0.1");

        /**
         * 用Map设置Lua的ARGV[1]
         */
        Map<String, Object> argvMap = new HashMap<>(2);
        argvMap.put("expire", 10000);
        argvMap.put("times", 10);
        final List ret = redisTemplate.execute(getRedisScript,
                new FastJsonRedisSerializer(Object.class), // args 泛型类型
                new FastJsonRedisSerializer(Object.class),  // Map 结果元素泛型类型
                keyList,
                argvMap);
        System.out.println(ret);

        // 方式二：更底层的方式
//        redisTemplate.execute((RedisCallback<List>) connection -> connection.eval(
//                scriptString.getBytes(StandardCharsets.UTF_8),
//                ReturnType.INTEGER,
//                1,
//                JSONArray.toJSONBytes(keyList),
//                JSONArray.toJSONBytes(argvMap)
//        ));
    }


    final String scriptString = "" +
            "--获取KEY\n" +
            "local key1 = KEYS[1]\n" +
            "local key2 = KEYS[2]\n" +
            "\n" +
            "-- 获取ARGV[1],这里对应到应用端是一个List<Map>.\n" +
            "--  注意，这里接收到是的字符串，所以需要用csjon库解码成table类型\n" +
            "local receive_arg_json =  cjson.decode(ARGV[1])\n" +
            "\n" +
            "--返回的变量\n" +
            "local result = {}\n" +
            "\n" +
            "--打印日志到reids\n" +
            "--注意，这里的打印日志级别，需要和redis.conf配置文件中的日志设置级别一致才行\n" +
            "redis.log(redis.LOG_DEBUG,key1)\n" +
            "redis.log(redis.LOG_DEBUG,key2)\n" +
            "redis.log(redis.LOG_DEBUG, ARGV[1],#ARGV[1])\n" +
            "\n" +
            "--获取ARGV内的参数并打印\n" +
            "local expire = receive_arg_json.expire\n" +
            "local times = receive_arg_json.times\n" +
            "redis.log(redis.LOG_DEBUG,tostring(times))\n" +
            "redis.log(redis.LOG_DEBUG,tostring(expire))\n" +
            "\n" +
            "--往redis设置值\n" +
            "redis.call(\"set\", key1, times)\n" +
            "redis.call(\"incr\", key2)\n" +
            "redis.call(\"expire\", key2, expire)\n" +
            "\n" +
            "--用一个临时变量来存放json, json是要放入要返回的数组中的\n" +
            "local jsonRedisTemp = {}\n" +
            "jsonRedisTemp[key1] = redis.call(\"get\", key1)\n" +
            "jsonRedisTemp[key2] = redis.call(\"get\", key2)\n" +
            "jsonRedisTemp[\"ttl\"] = redis.call(\"ttl\", key2)\n" +
            "redis.log(redis.LOG_DEBUG, cjson.encode(jsonRedisTemp))\n" +
            "\n" +
            "--springboot redistemplate接收的是List,如果返回的数组内容是json对象,需要将json对象转成字符串,客户端才能接收\n" +
            "result[1] = cjson.encode(jsonRedisTemp)\n" +
            "--将源参数内容一起返回\n" +
            "result[2] = ARGV[1]\n" +
            "--打印返回的数组结果，这里返回需要以字符返回\n" +
            "redis.log(redis.LOG_DEBUG, cjson.encode(result))\n" +
            "\n" +
            "return result"
            ;

    public void redisLuaWithScriptString() {
        /**
         * List设置lua的KEYS
         */
        List<String> keyList = new ArrayList();
        keyList.add("count");
        keyList.add("rate.limiting:127.0.0.1");

        /**
         * 用Map设置Lua的ARGV[1]
         */
        Map<String, Object> argvMap = new HashMap<>(2);
        argvMap.put("expire", 10000);
        argvMap.put("times", 10);
        final List ret = redisTemplate.execute(
                new RedisScript<List>() {
                    @Override
                    public String getSha1() {
                        return DigestUtils.sha1DigestAsHex(scriptString);
                    }

                    @Override
                    public Class<List> getResultType() {
                        return List.class;
                    }
                    @Override
                    public String getScriptAsString() {
                        System.out.println(scriptString);
                        return scriptString;
                    }
                },
                new FastJsonRedisSerializer(Object.class),
                new FastJsonRedisSerializer(String.class),
                keyList,
                argvMap);
        System.out.println(ret);
    }


}
