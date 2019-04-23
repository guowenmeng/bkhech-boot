package com.bkhech.boot.configure.common.builder.chart.radar.response;

import com.bkhech.boot.configure.common.builder.chart.BaseCharts;

/**
*  RadarChartResponse　数据结构定义见: https://wiki.kyhub.cn/pages/viewpage.action?pageId=1804077
*  Created by guowm 2018/9/10
*/
public class RadarChartResponse extends BaseCharts {

    private WithRadarPropertyCharts chart;

    public WithRadarPropertyCharts getChart() {
        return chart;
    }

    public void setChart(WithRadarPropertyCharts chart) {
        this.chart = chart;
    }

}
