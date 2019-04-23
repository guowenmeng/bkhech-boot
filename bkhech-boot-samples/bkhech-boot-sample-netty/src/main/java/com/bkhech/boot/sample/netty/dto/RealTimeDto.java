package com.bkhech.boot.sample.netty.dto;

import java.io.Serializable;

/**
 * Description: RealTimeDto
 * @author guowm 2018/9/26
 */
public class RealTimeDto implements Serializable{

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
