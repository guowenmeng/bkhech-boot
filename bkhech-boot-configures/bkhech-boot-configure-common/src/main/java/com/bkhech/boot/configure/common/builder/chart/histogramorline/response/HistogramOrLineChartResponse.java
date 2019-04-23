package com.bkhech.boot.configure.common.builder.chart.histogramorline.response;

/**
*  HistogramOrLineChartResponse  数据结构定义见: https://wiki.kyhub.cn/pages/viewpage.action?pageId=1804077
*  Created by guowm 2018/9/6
*/
public class HistogramOrLineChartResponse extends WithXLabelPropertyChart {

    private WithXAxisPropertyChart chart;

    public WithXAxisPropertyChart getChart() {
        return chart;
    }

    public void setChart(WithXAxisPropertyChart chart) {
        this.chart = chart;
    }

}
