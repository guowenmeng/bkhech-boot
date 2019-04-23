package com.bkhech.boot.configure.common.builder.chart.pieorfunnel;

import com.bkhech.boot.commons.util.ApplicationContextUtil;
import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.bkhech.boot.configure.common.builder.chart.NameAndData;
import com.bkhech.boot.configure.common.builder.BaseBuilder;
import com.bkhech.boot.configure.common.builder.chart.CompositeCharts;
import com.bkhech.boot.configure.common.builder.chart.NameAndValue;

import java.util.List;

/**
 * Description: PieOrFunnelBuilder
 * @author guowm 2018/9/20
 */
public class PieOrFunnelBuilder implements BaseBuilder {

    private CompositeCharts chartDto = new CompositeCharts();
    private PieOrFunnelChartResponse response;

    private static ChartContext context;

    static {
        try {
            context = ApplicationContextUtil.getBean(PieOrFunnelContext.class);
        } catch (Exception e) {
            throw new RuntimeException("init ChartContext failed,please enable EnableBkhMvc annotation.", e.fillInStackTrace());
        }
    }

    protected PieOrFunnelBuilder(PieOrFunnelChartResponse response) {
        this.response = response;
    }

    private PieOrFunnelBuilder(String index) {
        PieOrFunnelBuilder builder = (PieOrFunnelBuilder) context.createChart(index);
        builder.getResponse().setChart(chartDto);
        this.response = builder.getResponse();
    }

    private PieOrFunnelChartResponse getResponse() {
        return response;
    }

    /**
     * set y轴数据
     * @param yAxis
     * @param <T> y轴数据类型
     * @return this builder
     */
    public <T extends Number> PieOrFunnelBuilder yAxis(List<NameAndData<NameAndValue<T>>> yAxis) {
        this.chartDto.setyAxis(yAxis);
        return this;
    }

    /**
     * set 提示数据
     * @param label
     * @return this builder
     */
    public PieOrFunnelBuilder label(List<NameAndData<NameAndValue<String>>> label) {
        this.chartDto.setLabel(label);
        return this;
    }

    public PieOrFunnelChartResponse build() {
        return this.getResponse();
    }

    public static PieOrFunnelBuilder newBuilder(String index) {
        return new PieOrFunnelBuilder(index);
    }

}
