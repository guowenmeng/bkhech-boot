package com.bkhech.boot.configure.common.builder.chart;

import java.io.Serializable;

/**
*  BaseCharts
*  @author guowm 2018/9/10
*/
public class BaseCharts implements Serializable {

    private static final long serialVersionUID = -5689382304452461332L;
    /**
     * index : 图表索引
     * title : 累计用户量
     * unit : 人
     * yLabel : 人数/间隔(天)
     * description : 图表说明文字
     */
    private String index;
    private String title;
    private String unit;
    private String yLabel;
    private String description;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getyLabel() {
        return yLabel;
    }

    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
