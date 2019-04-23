package com.bkhech.boot.configure.common.builder.chart.map;

import com.bkhech.boot.configure.common.builder.chart.BaseCharts;
import com.bkhech.boot.configure.common.builder.chart.CompositeCharts;
import com.bkhech.boot.configure.common.builder.chart.NameAndValue;

/**
*  MapChartResponse 数据结构定义见: https://wiki.kyhub.cn/pages/viewpage.action?pageId=1804077
*  Created by guowm 2018/9/10
*/
public class MapChartResponse extends BaseCharts {
    
    private CompositeCharts<NameAndValue> chart;

    public CompositeCharts<NameAndValue> getChart() {
        return chart;
    }

    public void setChart(CompositeCharts<NameAndValue> chart) {
        this.chart = chart;
    }
}
