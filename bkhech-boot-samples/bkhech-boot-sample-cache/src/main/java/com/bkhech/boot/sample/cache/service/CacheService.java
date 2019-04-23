package com.bkhech.boot.sample.cache.service;

import com.bkhech.boot.sample.cache.dto.Person;

/**
 * Description: CacheService
 * @author guowm 2018/10/12
 */
public interface CacheService {

    String add(String str);
    String add(String param1, String param2);
    String add(Person person);

    String update(String str);

    void delete(String str);
    void deleteAll();
}
