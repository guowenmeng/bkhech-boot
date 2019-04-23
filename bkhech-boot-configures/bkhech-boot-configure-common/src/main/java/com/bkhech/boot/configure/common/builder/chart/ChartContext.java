package com.bkhech.boot.configure.common.builder.chart;

import com.bkhech.boot.configure.common.builder.BaseBuilder;

/**
*  ChartContext
*  @author guowm 2018/9/6
*/
public interface ChartContext {

    /**
     * Create The chart builder
     * @param index  The index of chart. Creates the builder for the specified key configuration
     * @return The builder of the specified key context
     */
    BaseBuilder createChart(String index);
}
