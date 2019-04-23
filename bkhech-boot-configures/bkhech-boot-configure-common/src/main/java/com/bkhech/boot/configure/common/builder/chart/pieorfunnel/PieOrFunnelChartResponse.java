package com.bkhech.boot.configure.common.builder.chart.pieorfunnel;

import com.bkhech.boot.configure.common.builder.chart.BaseCharts;
import com.bkhech.boot.configure.common.builder.chart.CompositeCharts;

/**
*  PieOrFunnelChartResponse　数据结构定义见: https://wiki.kyhub.cn/pages/viewpage.action?pageId=1804077
*  Created by guowm 2018/9/10
*/
public class PieOrFunnelChartResponse extends BaseCharts {

    private CompositeCharts chart;

    public CompositeCharts getChart() {
        return chart;
    }

    public void setChart(CompositeCharts chart) {
        this.chart = chart;
    }


}
