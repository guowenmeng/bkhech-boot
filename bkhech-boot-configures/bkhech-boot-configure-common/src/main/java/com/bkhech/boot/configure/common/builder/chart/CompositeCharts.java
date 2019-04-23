package com.bkhech.boot.configure.common.builder.chart;

import java.io.Serializable;
import java.util.List;

/**
*  CompositeCharts　图表内容数据对象
*  @author guowm 2018/9/10
*/
public class CompositeCharts<T> implements Serializable{

    private static final long serialVersionUID = 2162272501050413192L;

    private List<T> yAxis;
    private List<T> label;

    public List<T> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<T> yAxis) {
        this.yAxis = yAxis;
    }

    public List<T> getLabel() {
        return label;
    }

    public void setLabel(List<T> label) {
        this.label = label;
    }
}