package com.bkhech.boot.configure.common.builder.chart.histogramorline.response;

import com.bkhech.boot.configure.common.builder.chart.BaseCharts;

/**
*  WithXLabelPropertyChart
*  Created by guowm 2018/9/10
*/
public class WithXLabelPropertyChart extends BaseCharts {

    /**
     * xLabel : 人数/间隔(天)
     */
    private String xLabel;

    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }
}
