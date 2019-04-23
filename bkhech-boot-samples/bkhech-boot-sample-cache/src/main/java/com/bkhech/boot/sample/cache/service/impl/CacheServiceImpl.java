package com.bkhech.boot.sample.cache.service.impl;

import com.bkhech.boot.sample.cache.service.CacheService;
import com.bkhech.boot.sample.cache.dto.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Description: CacheServiceImpl
 * @author guowm 2018/10/12
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Override
    @Cacheable(value = "add")
    public String add(String str) {
        System.out.println("come in method... param: " + str);
        return str;
    }

    @Override
    @Cacheable(value = "add")
    public String add(String param1, String param2) {
        System.out.println("come in method... ");
        return param1 + param2;
    }

    @Override
    @Cacheable(value = "add", key = "#p0.name.concat(#p0.age)")
    public String add(Person person) {
        System.out.println("come in method... ");
        return person.toString();
    }

    @Override
    @CachePut(value = "add")
    public String update(String str) {
        System.out.println("come in method... ");
        return updater(str);
    }

    @Override
    @CacheEvict(value = "add", key = "#p0")
    public void delete(String str) {
        System.out.println("come in method... ");
    }

    @Override
    @CacheEvict(value = "add", allEntries = true)
    public void deleteAll() {
        System.out.println("come in method... ");
    }

    String updater(String str) {
        return str.concat("2");
    }

}
