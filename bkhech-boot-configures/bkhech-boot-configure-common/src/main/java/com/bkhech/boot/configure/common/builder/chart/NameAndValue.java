package com.bkhech.boot.configure.common.builder.chart;

import java.io.Serializable;

/**
*  NameAndValue
 * @author guowm
 */
public class NameAndValue<T> implements Serializable {

    private static final long serialVersionUID = 9179147921991773967L;

    private T value;
    private String name;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
