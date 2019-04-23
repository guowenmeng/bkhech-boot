package com.bkhech.boot.sample.common.test;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: Thead
 * @author guowm 2018/10/10
 */
@Component
public class Thead implements Serializable{
    private static final long serialVersionUID = 2986611488256636272L;

    private Map<String, String> map;
    private List<Map<String, String>> list;

    public Thead(){
        map = new HashMap<String, String>();
        map.put("MapA", "This is A");
        map.put("MapB", "This is B");
        map.put("MapC", "This is C");

        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        Map<String, String> map3 = new HashMap<String, String>();
        map2.put("MapB", "This is B");
        map1.put("MapA", "This is A");
        map3.put("MapC", "This is C");
        list = new ArrayList<Map<String, String>>();
        list.add(map2);
        list.add(map1);
        list.add(map3);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }
}
