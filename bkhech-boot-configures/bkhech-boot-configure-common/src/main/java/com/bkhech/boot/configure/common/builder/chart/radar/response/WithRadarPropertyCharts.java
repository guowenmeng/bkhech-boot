package com.bkhech.boot.configure.common.builder.chart.radar.response;

import com.bkhech.boot.configure.common.builder.chart.CompositeCharts;
import com.bkhech.boot.configure.common.builder.chart.NameAndValue;

import java.util.List;

/**
*  WithRadarPropertyCharts
*  Created by guowm 2018/9/10
*/
public class WithRadarPropertyCharts<T> extends CompositeCharts {

    private List<NameAndValue<T>> radar;

    public List<NameAndValue<T>> getRadar() {
        return radar;
    }

    public void setRadar(List<NameAndValue<T>> radar) {
        this.radar = radar;
    }
}
