package com.bkhech.boot.configure.common.builder.chart.histogramorline;

import com.bkhech.boot.commons.util.ApplicationContextUtil;
import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.bkhech.boot.configure.common.builder.chart.NameAndData;
import com.bkhech.boot.configure.common.builder.chart.histogramorline.response.HistogramOrLineChartResponse;
import com.bkhech.boot.configure.common.builder.BaseBuilder;
import com.bkhech.boot.configure.common.builder.chart.histogramorline.response.WithXAxisPropertyChart;

import java.util.List;

/**
 * Description: HistogramOrLineBuilder
 * @author guowm 2018/9/19
 */
public class HistogramOrLineBuilder implements BaseBuilder {

    private WithXAxisPropertyChart chartDto = new WithXAxisPropertyChart();
    private HistogramOrLineChartResponse response;

    private static ChartContext context;

    static {
        try {
            context = ApplicationContextUtil.getBean(HistogramOrLineContext.class);
        } catch (Exception e) {
            throw new RuntimeException("init ChartContext failed,please enable EnableBkhMvc annotation.", e.fillInStackTrace());
        }
    }

    protected HistogramOrLineBuilder(HistogramOrLineChartResponse response) {
        this.response = response;
    }

    private HistogramOrLineBuilder(String index) {
        HistogramOrLineBuilder builder = (HistogramOrLineBuilder) context.createChart(index);
        builder.getResponse().setChart(chartDto);
        this.response = builder.getResponse();
    }

    private HistogramOrLineChartResponse getResponse() {
        return this.response;
    }

    /**
     * set y轴数据
     * @param yAxis
     * @param <T> y轴数据类型
     * @return this builder
     */
    public <T extends Number> HistogramOrLineBuilder yAxis(List<NameAndData<T>> yAxis) {
        this.chartDto.setyAxis(yAxis);
        return this;
    }

    /**
     * set x轴数据
     * @param xAxis
     * @return this builder
     */
    public HistogramOrLineBuilder xAxis(List<String> xAxis) {
        this.chartDto.setxAxis(xAxis);
        return this;
    }

    /**
     * set 提示数据
     * @param label
     * @return this builder
     */
    public HistogramOrLineBuilder label(List<NameAndData<String>> label) {
        this.chartDto.setLabel(label);
        return this;
    }

    public HistogramOrLineChartResponse build() {
        return this.getResponse();
    }

    public static HistogramOrLineBuilder newBuilder(String index) {
        return new HistogramOrLineBuilder(index);
    }

}
