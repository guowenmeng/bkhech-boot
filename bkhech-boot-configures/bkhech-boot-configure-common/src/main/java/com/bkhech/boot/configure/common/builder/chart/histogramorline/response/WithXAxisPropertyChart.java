package com.bkhech.boot.configure.common.builder.chart.histogramorline.response;

import com.bkhech.boot.configure.common.builder.chart.CompositeCharts;

import java.util.List;

/**
*  WithXAxisPropChart
*  Created by guowm 2018/9/10
*/
public class WithXAxisPropertyChart extends CompositeCharts {

    private List<String> xAxis;

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }
}
