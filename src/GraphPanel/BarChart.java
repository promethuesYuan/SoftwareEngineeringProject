package GraphPanel;

import GraphPanel.ChartUtil.ChartUtils;
import GraphPanel.ChartUtil.Serie;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.Vector;

public class BarChart {
    /**
    * @api 接口描述 Author PKY:
    * @api BarChart
    * @api Description:  生成一个条形图的panel供主面板调用 ， 调用方法 BarChart.createDataset()
    * @api Param :
    * @api return:
     **/
	public BarChart() {
	}

	public static  DefaultCategoryDataset createDataset() {
	    /**
	        * @api 成员函数描述  Author : PKY
	        * @api createDataset
	        * @api Description:   生成条形图数据集的函数
	        * @api Param []:   Map {"item" :  value}
	        * @api returnorg.jfree.data.category.DefaultCategoryDataset:   条形图数据集
	        **/
		// 标注类别
		String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		series.add(new Serie("Tokyo", new Double[] { 49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 }));   // 条形图的一组数据
		series.add(new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 }));
		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}

	public static  ChartPanel createChart() {
		/**
		    * @api 成员函数描述  Author : PKY
		    * @api createChart
		    * @api Description: 创建 panel 面板的函数的函数
		    * @api Param []:
		    * @api returnorg.jfree.chart.ChartPanel:
		    **/
		JFreeChart chart = ChartFactory.createBarChart("Monthly Average Rainfall", "", "Rainfall (mm)", createDataset());
		ChartUtils.setAntiAlias(chart); // 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//

		ChartUtils.setXAixs(chart.getCategoryPlot());           // X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());            // Y坐标轴渲染
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));// 设置标注无边框
		ChartPanel chartPanel = new ChartPanel(chart);           // 6:使用chartPanel接收
		return chartPanel;
	}

}