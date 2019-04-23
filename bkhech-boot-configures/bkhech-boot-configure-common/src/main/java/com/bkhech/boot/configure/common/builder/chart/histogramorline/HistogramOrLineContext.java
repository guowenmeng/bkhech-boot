package com.bkhech.boot.configure.common.builder.chart.histogramorline;

import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.bkhech.boot.configure.common.builder.chart.histogramorline.response.HistogramOrLineChartResponse;
import com.google.common.base.Preconditions;
import com.bkhech.boot.configure.common.builder.chart.configuration.ChartConfig;

/**
*  HistogramOrLineContext
*  Created by guowm 2018/9/6
*/
public class HistogramOrLineContext implements ChartContext {

    private ChartConfig chartConfig;

    public HistogramOrLineContext(ChartConfig chartConfig) {
        this.chartConfig = chartConfig;
    }

    @Override
    public HistogramOrLineBuilder createChart(String histogramOrLineIndex) {
        Preconditions.checkNotNull(histogramOrLineIndex, "histogramOrLineIndex is null");
        Preconditions.checkNotNull(chartConfig, "chartConfig is null");

        ChartConfig.HistogramOrLine histogramOrLine = chartConfig
                .getHistogramOrLines()
                .stream()
                .filter(hol -> histogramOrLineIndex.equals(hol.getIndex()))
                .findFirst()
                .get();
        Preconditions.checkNotNull(chartConfig, "not found goal according to histogramOrLineIndex in chartConfig");
        HistogramOrLineChartResponse response = new HistogramOrLineChartResponse();

        response.setTitle(histogramOrLine.getCharts().getTitle());
        response.setUnit(histogramOrLine.getCharts().getUnit());
        response.setxLabel(histogramOrLine.getCharts().getxLabel());
        response.setyLabel(histogramOrLine.getCharts().getyLabel());
        response.setDescription(histogramOrLine.getCharts().getDescription());
        return new HistogramOrLineBuilder(response);
    }

}
