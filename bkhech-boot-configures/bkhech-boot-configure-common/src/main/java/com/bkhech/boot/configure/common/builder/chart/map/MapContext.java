package com.bkhech.boot.configure.common.builder.chart.map;

import com.google.common.base.Preconditions;
import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.bkhech.boot.configure.common.builder.chart.configuration.ChartConfig;

/**
*  MapContext
*  Created by guowm 2018/9/10
*/
public class MapContext implements ChartContext {

    private ChartConfig chartConfig;

    public MapContext(ChartConfig chartConfig) {
        this.chartConfig = chartConfig;
    }

    @Override
    public MapBuilder createChart(String mapIndex) {
        Preconditions.checkNotNull(mapIndex, "mapIndex is null");
        Preconditions.checkNotNull(chartConfig, "chartConfig is null");

        ChartConfig.Map radarMap = chartConfig
                .getMaps()
                .stream()
                .filter(hol -> mapIndex.equals(hol.getIndex()))
                .findFirst()
                .get();
        Preconditions.checkNotNull(chartConfig, "not found goal according to mapIndex in chartConfig");

        MapChartResponse response = new MapChartResponse();
        response.setTitle(radarMap.getCharts().getTitle());
        response.setUnit(radarMap.getCharts().getUnit());
        response.setyLabel(radarMap.getCharts().getyLabel());
        response.setDescription(radarMap.getCharts().getDescription());
        return new MapBuilder(response);
    }

}
