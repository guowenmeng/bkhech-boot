package com.bkhech.boot.sample.common.test;

import java.io.Serializable;

/**
 * Description: Model
 * @author guowm 2018/10/10
 */
public class Model implements Serializable {
    private static final long serialVersionUID = 76689023273064822L;

    private String key3;
    private String key1;
    private String akey;
    private String key2;

    public Model() {
    }

    public Model(String key3, String key1, String akey, String key2) {
        this.key3 = key3;
        this.key1 = key1;
        this.akey = akey;
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getAkey() {
        return akey;
    }

    public void setAkey(String akey) {
        this.akey = akey;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }
}
