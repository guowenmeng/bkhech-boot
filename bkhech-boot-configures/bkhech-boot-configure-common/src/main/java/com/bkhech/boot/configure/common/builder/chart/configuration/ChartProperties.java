package com.bkhech.boot.configure.common.builder.chart.configuration;

import java.io.Serializable;

/**
*  ChartProperties　图表基础配置对象
*  Created by guowm 2018/9/10
*/
public class ChartProperties<T> implements Serializable {

    /**
     * index : 图表索引
     * charts : 图表基本信息
     */
    private String index;
    private T charts;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public T getCharts() {
        return charts;
    }

    public void setCharts(T charts) {
        this.charts = charts;
    }
}
