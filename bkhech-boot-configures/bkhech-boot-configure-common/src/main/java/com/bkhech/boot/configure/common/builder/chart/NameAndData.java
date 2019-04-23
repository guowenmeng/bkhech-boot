package com.bkhech.boot.configure.common.builder.chart;

import java.io.Serializable;
import java.util.List;

/**
*  NameAndData
 * @author guowm
 */
public class NameAndData<T> implements Serializable{

    private static final long serialVersionUID = 9018466058003845912L;
    private String name;
    private List<T> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
