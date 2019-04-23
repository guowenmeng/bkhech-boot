package com.bkhech.boot.configure.common.builder.chart.pieorfunnel;

import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.google.common.base.Preconditions;
import com.bkhech.boot.configure.common.builder.chart.configuration.ChartConfig;

/**
*  PieOrFunnelContext
*  Created by guowm 2018/9/10
*/
public class PieOrFunnelContext implements ChartContext {

    private ChartConfig chartConfig;

    public PieOrFunnelContext(ChartConfig chartConfig) {
        this.chartConfig = chartConfig;
    }

    @Override
    public PieOrFunnelBuilder createChart(String pieOrFunnelIndex) {
        Preconditions.checkNotNull(pieOrFunnelIndex, "pieOrFunnelIndex is null");
        Preconditions.checkNotNull(chartConfig, "chartConfig is null");

        ChartConfig.PieOrFunnel pieOrFunnel = chartConfig
                .getPieOrFunnels()
                .stream()
                .filter(hol -> pieOrFunnelIndex.equals(hol.getIndex()))
                .findFirst()
                .get();
        Preconditions.checkNotNull(chartConfig, "not found goal according to pieOrFunnelIndex in chartConfig");

        PieOrFunnelChartResponse response = new PieOrFunnelChartResponse();
        response.setTitle(pieOrFunnel.getCharts().getTitle());
        response.setUnit(pieOrFunnel.getCharts().getUnit());
        response.setyLabel(pieOrFunnel.getCharts().getyLabel());
        response.setDescription(pieOrFunnel.getCharts().getDescription());
        return new PieOrFunnelBuilder(response);
    }

}
