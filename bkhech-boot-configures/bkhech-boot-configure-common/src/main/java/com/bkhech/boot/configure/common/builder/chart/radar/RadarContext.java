package com.bkhech.boot.configure.common.builder.chart.radar;

import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.bkhech.boot.configure.common.builder.chart.radar.response.RadarChartResponse;
import com.google.common.base.Preconditions;
import com.bkhech.boot.configure.common.builder.chart.configuration.ChartConfig;

/**
*  RadarContext
*  Created by guowm 2018/9/10
*/
public class RadarContext implements ChartContext {

    private ChartConfig chartConfig;

    public RadarContext(ChartConfig chartConfig) {
        this.chartConfig = chartConfig;
    }

    @Override
    public RadarBuilder createChart(String radarIndex) {
        Preconditions.checkNotNull(radarIndex, "radarIndex is null");
        Preconditions.checkNotNull(chartConfig, "chartConfig is null");

        ChartConfig.Radar radarMap = chartConfig
                .getRadars()
                .stream()
                .filter(hol -> radarIndex.equals(hol.getIndex()))
                .findFirst()
                .get();
        Preconditions.checkNotNull(chartConfig, "not found goal according to radarIndex in chartConfig");

        RadarChartResponse response = new RadarChartResponse();
        response.setTitle(radarMap.getCharts().getTitle());
        response.setUnit(radarMap.getCharts().getUnit());
        response.setyLabel(radarMap.getCharts().getyLabel());
        response.setDescription(radarMap.getCharts().getDescription());
        return new RadarBuilder(response);
    }

}
