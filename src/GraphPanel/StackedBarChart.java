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

public class StackedBarChart {
	public StackedBarChart() {
	}

	public static  DefaultCategoryDataset createDataset() {
		// 标注类别
		String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		series.add(new Serie("Tokyo", new Double[] { 49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 }));
		series.add(new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 }));
		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}

	public static ChartPanel createChart() {

		JFreeChart chart = ChartFactory.createStackedBarChart("Monthly Average Rainfall", "", "Rainfall (mm)", createDataset());

		ChartUtils.setAntiAlias(chart);// 抗锯齿
		ChartUtils.setStackBarRender(chart.getCategoryPlot());
		ChartUtils.setXAixs(chart.getCategoryPlot());
		ChartUtils.setYAixs(chart.getCategoryPlot());
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

}
