package com.bkhech.boot.configure.common.builder.chart.configuration;

/**
*  InitChartProperties
*  Created by guowm 2018/9/11
*/
public class InitChartProperties {
    /**
     * title : 累计用户量
     * unit : 人
     * yLabel : 人数/间隔(天)
     * description : 图表说明文字
     */
    private String title;
    private String unit;
    private String yLabel;
    private String description;

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
