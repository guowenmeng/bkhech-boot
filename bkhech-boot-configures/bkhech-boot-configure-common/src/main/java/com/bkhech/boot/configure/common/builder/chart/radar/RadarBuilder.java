package com.bkhech.boot.configure.common.builder.chart.radar;

import com.bkhech.boot.commons.util.ApplicationContextUtil;
import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.bkhech.boot.configure.common.builder.chart.NameAndData;
import com.bkhech.boot.configure.common.builder.chart.radar.response.RadarChartResponse;
import com.bkhech.boot.configure.common.builder.chart.radar.response.WithRadarPropertyCharts;
import com.bkhech.boot.configure.common.builder.BaseBuilder;
import com.bkhech.boot.configure.common.builder.chart.NameAndValue;

import java.util.List;

/**
 * Description: RadarBuilder
 * @author guowm 2018/9/19
 */
public class RadarBuilder implements BaseBuilder {

    private RadarChartResponse response;
    private WithRadarPropertyCharts chartDto = new WithRadarPropertyCharts();

    private static ChartContext context;

    static {
        try {
            context = ApplicationContextUtil.getBean(RadarContext.class);
        } catch (Exception e) {
            throw new RuntimeException("init ChartContext failed,please enable EnableBkhMvc annotation.", e.fillInStackTrace());
        }
    }

    protected RadarBuilder(RadarChartResponse response) {
        this.response = response;
    }

    private RadarBuilder(String index) {
        RadarBuilder builder = (RadarBuilder) context.createChart(index);
        builder.getResponse().setChart(chartDto);
        this.response = builder.getResponse();
    }

    private RadarChartResponse getResponse() {
        return response;
    }

    /**
     * set y轴数据
     * @param yAxis
     * @param <T>
     * @return this builder
     */
    public <T extends Number> RadarBuilder yAxis(List<NameAndData<T>> yAxis) {
        this.chartDto.setyAxis(yAxis);
        return this;
    }

    /**
     * set 雷达图外围的最大值 即参照值
     * @param radar
     * @param <T>
     * @return this builder
     */
    public <T extends Number> RadarBuilder radar(List<NameAndValue<T>> radar) {
        this.chartDto.setRadar(radar);
        return this;
    }

    /**
     * set 提示数据
     * @param label
     * @return this builder
     */
    public RadarBuilder label(List<NameAndData<String>> label) {
        this.chartDto.setLabel(label);
        return this;
    }

    public RadarChartResponse build() {
        return this.getResponse();
    }

    public static RadarBuilder newBuilder(String index) {
        return new RadarBuilder(index);
    }

}
