package com.bkhech.boot.configure.common.builder.chart.configuration;

import com.bkhech.boot.configure.common.builder.chart.histogramorline.HistogramOrLineContext;
import com.bkhech.boot.configure.common.builder.chart.map.MapContext;
import com.bkhech.boot.configure.common.builder.chart.pieorfunnel.PieOrFunnelContext;
import com.bkhech.boot.configure.common.builder.chart.radar.RadarContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
*  ContextConfig 各类图表上下文环境
*  Created by guowm 2018/9/10
*/
@Configuration
public class ContextConfig {

    @Autowired
    private ChartConfig chartConfig;

    @Bean
    public HistogramOrLineContext histogramOrLineContext() {
        return new HistogramOrLineContext(chartConfig);
    }

    @Bean
    public MapContext mapContext() {
        return new MapContext(chartConfig);
    }

    @Bean
    public PieOrFunnelContext pieOrFunnelContext() {
        return new PieOrFunnelContext(chartConfig);
    }

    @Bean
    public RadarContext radarContext() {
        return new RadarContext(chartConfig);
    }
}
