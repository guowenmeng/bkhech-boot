package com.bkhech.boot.sample.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("standalone")
public class RedisStandaloneTests {

	final String KEY = "redis-standalone-test-key";
	final String VAlUE = "redis-standalone-test-value";

	@Autowired
    private RedisTemplate<String, String> redisTemplate;

	@Test
	public void contextLoads() {
	}

	/**
	 * redis 单实例
	 * @return
	 */
	@Test
	public void redisTest(){
		redisTemplate.opsForValue().set(KEY, VAlUE);
		String res = redisTemplate.opsForValue().get(KEY);
		System.out.println("res = " + res);
//		redisTemplate.delete(KEY);
	}



}
