package com.bkhech.boot.sample.cache;

import com.bkhech.boot.sample.cache.dto.Person;
import com.bkhech.boot.sample.cache.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private CacheService cacheService;

	@Test
	public void contextLoads() {

		String str = "brain";
		for (int i = 0; i < 2; i++) {
			String add = cacheService.add(str);
			System.out.println("add = " + add);
		}
	}

	@Test
	public void add2() {

		String str = "brain";
		String param2 = "brain2";
		for (int i = 0; i < 2; i++) {
			String add2 = cacheService.add(str, param2);
			System.out.println("add2 = " + add2);
		}
	}

	@Test
	public void add3() {

		String str = "brain";
		String param2 = "brain2";

		for (int i = 0; i < 2; i++) {
			String add2 = cacheService.add(new Person(str, 18));
			System.out.println("add2 = " + add2);
		}
	}

    @Test
    public void update() {

        String str = "brain";
        String add2 = cacheService.update(str);
        System.out.println("add2 = " + add2);
    }


    @Test
    public void deleteById() {
        String str = "brain";
        cacheService.delete(str);
    }

    @Test
    public void deleteAll() {
        cacheService.deleteAll();
    }

}
