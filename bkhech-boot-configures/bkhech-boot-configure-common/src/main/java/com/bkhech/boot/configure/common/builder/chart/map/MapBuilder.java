package com.bkhech.boot.configure.common.builder.chart.map;

import com.bkhech.boot.commons.util.ApplicationContextUtil;
import com.bkhech.boot.configure.common.builder.BaseBuilder;
import com.bkhech.boot.configure.common.builder.chart.ChartContext;
import com.bkhech.boot.configure.common.builder.chart.CompositeCharts;
import com.bkhech.boot.configure.common.builder.chart.NameAndValue;

import java.util.List;

/**
 * Description: MapBuilder
 * @author guowm 2018/9/19
 */
public class MapBuilder implements BaseBuilder {

    private CompositeCharts chartDto = new CompositeCharts();
    private MapChartResponse response;

    private static ChartContext context;

    static {
        try {
            context = ApplicationContextUtil.getBean(MapContext.class);
        } catch (Exception e) {
            throw new RuntimeException("init ChartContext failed,please enable EnableBkhMvc annotation.", e.fillInStackTrace());
        }
    }

    protected MapBuilder(MapChartResponse response) {
        this.response = response;
    }

    private MapBuilder(String index) {
        MapBuilder builder = (MapBuilder) context.createChart(index);
        builder.getResponse().setChart(chartDto);
        this.response = builder.getResponse();
    }

    private MapChartResponse getResponse() {
        return this.response;
    }

    /**
     * set y轴数据
     * @param yAxis
     * @param <T> y轴数据类型
     * @return this builder
     */
    public <T extends Number> MapBuilder yAxis(List<NameAndValue<T>> yAxis) {
        this.chartDto.setyAxis(yAxis);
        return this;
    }

    /**
     * set 提示数据
     * @param label
     * @param <T> 提示数据类型
     * @return this builder
     */
    public <T extends Number> MapBuilder label(List<NameAndValue<T>> label) {
        this.chartDto.setLabel(label);
        return this;
    }

    public MapChartResponse build() {
        return this.getResponse();
    }

    public static MapBuilder newBuilder(String index) {
        return new MapBuilder(index);
    }


}
