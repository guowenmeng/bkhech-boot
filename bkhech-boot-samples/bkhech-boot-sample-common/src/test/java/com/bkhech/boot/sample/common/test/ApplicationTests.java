package com.bkhech.boot.sample.common.test;

import com.google.common.collect.Lists;
import com.bkhech.boot.configure.common.builder.chart.histogramorline.HistogramOrLineBuilder;
import com.bkhech.boot.configure.common.builder.chart.map.MapBuilder;
import com.bkhech.boot.configure.common.builder.chart.pieorfunnel.PieOrFunnelBuilder;
import com.bkhech.boot.configure.common.builder.chart.radar.RadarBuilder;
import com.bkhech.boot.configure.common.builder.chart.NameAndData;
import com.bkhech.boot.configure.common.builder.chart.NameAndValue;
import com.bkhech.boot.configure.common.builder.chart.histogramorline.response.HistogramOrLineChartResponse;
import com.bkhech.boot.configure.common.builder.chart.map.MapChartResponse;
import com.bkhech.boot.configure.common.builder.chart.pieorfunnel.PieOrFunnelChartResponse;
import com.bkhech.boot.configure.common.builder.chart.radar.response.RadarChartResponse;
import com.bkhech.boot.configure.common.builder.dropdown.DropDownBuilder;
import com.bkhech.boot.configure.common.builder.dropdown.response.DropDown;
import com.bkhech.boot.configure.common.builder.dropdown.response.DropDownResponse;
import com.bkhech.boot.configure.common.builder.table.TableBuilder;
import com.bkhech.boot.configure.common.builder.table.response.TableResponse;
import com.bkhech.boot.configure.common.code.StatusCode;
import com.bkhech.boot.configure.common.dto.ResultResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.bkhech.mybatis.extend.page.result.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;

/**
*  ApplicationTests
*  Created by guowm 2018/9/11
 *
 *  数据结构定义见: https://wiki.kyhub.cn/pages/viewpage.action?pageId=1804077
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Value("${chart.histogram-or-lines[0].index}")
	private String key;
	@Value("${chart.pie-or-funnels[0].index}")
	private String piekey;
	@Value("${chart.radars[0].index}")
	private String radarkey;
	@Value("${chart.maps[0].index}")
	private String mapkey;

	@Value("#{${theadA}}")
	private List theadtAList;

//	@Value("#{thead.list}")
//	private List<Map<String,String>> theadlist;
	@Autowired
	private ApplicationContext context;

//	@Test
//	public void testConfiguraton(){
//		System.out.println("theadtAList = " + theadtAList);
//		System.out.println("theadlist = " + theadlist);

//		ExpressionParser parser = new SpelExpressionParser();
		//List解析
//		List<Map> theadtest = parser.parseExpression
//				("{{name:'brian'},{city:'Shanghai'}}").getValue(List.class);
//		System.out.println("theadtest = " + theadtest);
//	}

//	@Test
	public void testNullableAnnotation(){
		ResolvableType resolvableType = null;
		Class<ResolvableType> clazz = null;
		context.getBean("histogramOrLineContext", clazz);
		context.isTypeMatch("histogramOrLineContext", resolvableType);
	}

	/**
	 * 柱状图、折线图
	 */
	@Test
	public void testHistogramLine() {
		List<NameAndData<Integer>> yAxis = Lists.newArrayList();
		List<String> xAxis = Lists.newArrayList("星期一","星期二", "星期三", "星期四", "星期五", "星期六", "星期日");
		List label = Lists.newArrayList();

		NameAndData<Integer> yAxisDto = new NameAndData();
		yAxisDto.setName("直接访问");
		yAxisDto.setData(Lists.newArrayList(150, 232, 201, 154, 190, 330, 410));
		yAxis.add(yAxisDto);

		NameAndData<String> labelDto = new NameAndData();
		labelDto.setName("直接访问");
		labelDto.setData(Lists.newArrayList("150次", "232次", "201次", "154次", "190次", "330次", "410次"));
		label.add(labelDto);

        HistogramOrLineChartResponse build = HistogramOrLineBuilder.newBuilder(key)
                .yAxis(yAxis)
                .xAxis(xAxis)
                .label(label)
                .build();
        ResultResponse<HistogramOrLineChartResponse> data = ResultResponse.custom(StatusCode.OK, build);

		System.out.println("HistogramOrLineChartResponse data = " + data);

	}

	/**
	 * 地图
	 */
	@Test
	public void testMap() {

		List<NameAndValue<Integer>> yAxis = Lists.newArrayList();
		List<NameAndValue<Double>> label = Lists.newArrayList();

		NameAndValue<Integer> yAxisDto = new NameAndValue();
		yAxisDto.setName("北京");
		yAxisDto.setValue(23650);
		yAxis.add(yAxisDto);

		NameAndValue<Double> labelDto = new NameAndValue();
		labelDto.setName("北京");
		labelDto.setValue(58.6);
		label.add(labelDto);

        ResultResponse<MapChartResponse> data = ResultResponse.custom(StatusCode.OK, () ->
            MapBuilder.newBuilder(mapkey).yAxis(yAxis).label(label).build()
        );
		System.out.println("MapChartResponse data = " + data);
	}

	/**
	 * 饼状图、漏斗图
	 */
	@Test
	public void testPieOrFunnel() {

		List<NameAndData<NameAndValue<Integer>>> yAxis = Lists.newArrayList();
		List<NameAndData<NameAndValue<String>>> label = Lists.newArrayList();
		List<NameAndValue<Integer>> yAxisValueDtos = Lists.newArrayList();
		List<NameAndValue<String>> labelValueDtos = Lists.newArrayList();

		NameAndValue<Integer> yAxisValueDto = new NameAndValue();
		yAxisValueDto.setName("直达");
		yAxisValueDto.setValue(335);
		yAxisValueDtos.add(yAxisValueDto);

		NameAndValue<String> labelValueDto = new NameAndValue();
		labelValueDto.setName("直达");
		labelValueDto.setValue("335个");
		labelValueDtos.add(labelValueDto);

		NameAndData<NameAndValue<Integer>> yAxisDto = new NameAndData();
		yAxisDto.setName("直接访问");
		yAxisDto.setData(yAxisValueDtos);
		yAxis.add(yAxisDto);

		NameAndData<NameAndValue<String>> labelDto = new NameAndData();
		labelDto.setName("直接访问");
		labelDto.setData(labelValueDtos);
		label.add(labelDto);

        ResultResponse<PieOrFunnelChartResponse> data = ResultResponse.custom(StatusCode.OK, () ->
            PieOrFunnelBuilder.newBuilder(piekey).yAxis(yAxis).label(label).build()
        );

		System.out.println("PieOrFunnelChartResponse data = " + data);
	}

	/**
	 * 雷达图
	 */
	@Test
	public void testRadar() {

		List<NameAndData<Integer>> yAxis = Lists.newArrayList();
		List<NameAndValue<Integer>> radar = Lists.newArrayList();
		List<NameAndData<String>> label = Lists.newArrayList();


		NameAndValue<Integer> radarDto = new NameAndValue();
		radarDto.setName("销售（sales）");
		radarDto.setValue(23650);
		radar.add(radarDto);

		NameAndData<Integer> yAxisDto = new NameAndData();
		yAxisDto.setName("直接访问");
		yAxisDto.setData(Lists.newArrayList(150, 232, 201, 154, 190, 330, 410));
		yAxis.add(yAxisDto);

		NameAndData<String> labelDto = new NameAndData();
		labelDto.setName("直接访问");
		labelDto.setData(Lists.newArrayList("150次", "232次", "201次", "154次", "190次", "330次", "410次"));
		label.add(labelDto);

        RadarChartResponse build = RadarBuilder.newBuilder(radarkey).radar(radar).yAxis(yAxis).label(label).build();
        ResultResponse<RadarChartResponse> data = ResultResponse.success(build);

		System.out.println("RadarChartResponse data = " + data);
	}

	/**
	 * 表格
	 */
	@Test
	public void testTable() {
//		theadtAList 固定表头标题，放置在配置文件中
//		List thead = Lists.newArrayList(new LinkedHashMap(){{put("a",1);}});

		List<Model> data = Lists.newArrayList(new Model("2","1","3","5"));
		PageList<String> datas = new PageList(data, 0,10,2);

        ResultResponse<TableResponse> data1 = ResultResponse.custom(StatusCode.OK, () ->
            TableBuilder.newBuilder().thead(theadtAList).body(data).build()
        );
        System.out.println("TableResponse data1 = " + data1);

        TableResponse tableResponse = TableBuilder.newBuilder().thead(theadtAList).body(data).build();
        ResultResponse<TableResponse> data2 = ResultResponse.custom(StatusCode.OK, tableResponse);
        System.out.println("TableResponse data2 = " + data2);
	}

    /**
     * ResultResponse的success和failed方法 使用示例
     */
	@Test
	public void testSuccessFailed() {
        List thead = Lists.newArrayList(new LinkedHashMap(){{put("a",1);}});
        List<String> data = Lists.newArrayList("2","4");
        PageList<String> datas = new PageList(data, 0,10,2);

        ResultResponse success1 = ResultResponse.success();
        System.out.println("SuccessFailed success1 = " + success1);

        ResultResponse<PageList<String>> success2 = ResultResponse.success(datas);
        System.out.println("SuccessFailed success2 = " + success2);


        ResultResponse failed1 = ResultResponse.failed();
        System.out.println("SuccessFailed failed1 = " + failed1);

        ResultResponse failed2 = ResultResponse.failed("发生错误");
        System.out.println("SuccessFailed failed2 = " + failed2);
    }
	
	/**
	 * 下拉框（筛选框、表格头部）
	 */
	@Test
	public void testDropDown() {
		DropDown dropDown = new DropDown();
        dropDown.setText("北京");
        dropDown.setValue("bj");
		List<DropDown> data = Lists.newArrayList(dropDown);

        ResultResponse<DropDownResponse> custom = ResultResponse.custom(StatusCode.OK, () -> {
            return DropDownBuilder.newBuilder().data(data).build();
        });

        System.out.println("DropDownResponse custom = " + custom);

    }

}
