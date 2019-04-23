package com.bkhech.boot.configure.common.builder.chart.configuration;


import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Description: ChartConfig
 * @author guowm 2018/9/29
 */
@ConfigurationProperties(prefix = "chart")
public class ChartConfig {

    private List<HistogramOrLine> histogramOrLines = Lists.newArrayList(new HistogramOrLine());
    private List<PieOrFunnel> pieOrFunnels = Lists.newArrayList(new PieOrFunnel());
    private List<Radar> radars = Lists.newArrayList(new Radar());
    private List<Map> maps = Lists.newArrayList(new Map());

    public static class HistogramOrLine extends ChartProperties<WithXLabelInitChartProperties> {}

    public static class PieOrFunnel extends ChartProperties<InitChartProperties> {}

    public static class Radar extends ChartProperties<InitChartProperties> {}

    public static class Map extends ChartProperties<InitChartProperties> {}

    public List<HistogramOrLine> getHistogramOrLines() {
        return histogramOrLines;
    }

    public void setHistogramOrLines(List<HistogramOrLine> histogramOrLines) {
        this.histogramOrLines = histogramOrLines;
    }

    public List<PieOrFunnel> getPieOrFunnels() {
        return pieOrFunnels;
    }

    public void setPieOrFunnels(List<PieOrFunnel> pieOrFunnels) {
        this.pieOrFunnels = pieOrFunnels;
    }

    public List<Radar> getRadars() {
        return radars;
    }

    public void setRadars(List<Radar> radars) {
        this.radars = radars;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }

}
