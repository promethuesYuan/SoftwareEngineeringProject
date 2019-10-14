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
    * @api �ӿ����� Author PKY:
    * @api BarChart
    * @api Description:  ����һ������ͼ��panel���������� �� ���÷��� BarChart.createDataset()
    * @api Param :
    * @api return:
     **/
	public BarChart() {
	}

	public static  DefaultCategoryDataset createDataset() {
	    /**
	        * @api ��Ա��������  Author : PKY
	        * @api createDataset
	        * @api Description:   ��������ͼ���ݼ��ĺ���
	        * @api Param []:   Map {"item" :  value}
	        * @api returnorg.jfree.data.category.DefaultCategoryDataset:   ����ͼ���ݼ�
	        **/
		// ��ע���
		String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		Vector<Serie> series = new Vector<Serie>();
		// �������ƣ��������е�ֵ����
		series.add(new Serie("Tokyo", new Double[] { 49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 }));   // ����ͼ��һ������
		series.add(new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 }));
		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
		// 1���������ݼ���
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}

	public static  ChartPanel createChart() {
		/**
		    * @api ��Ա��������  Author : PKY
		    * @api createChart
		    * @api Description: ���� panel ���ĺ����ĺ���
		    * @api Param []:
		    * @api returnorg.jfree.chart.ChartPanel:
		    **/
		JFreeChart chart = ChartFactory.createBarChart("Monthly Average Rainfall", "", "Rainfall (mm)", createDataset());
		ChartUtils.setAntiAlias(chart); // 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//

		ChartUtils.setXAixs(chart.getCategoryPlot());           // X��������Ⱦ
		ChartUtils.setYAixs(chart.getCategoryPlot());            // Y��������Ⱦ
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));// ���ñ�ע�ޱ߿�
		ChartPanel chartPanel = new ChartPanel(chart);           // 6:ʹ��chartPanel����
		return chartPanel;
	}

}