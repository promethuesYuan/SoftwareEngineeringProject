package GraphPanel;

import GraphPanel.ChartUtil.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;

public class TimeSeriesChart {
	/**
	* @api 接口描述 Author PKY: 
	* @api TimeSeriesChart
	* @api Description: 
	* @api Param :
	* @api return:
	 **/
	public TimeSeriesChart() {
	}


	public static TimeSeriesCollection createDataset() {
		/**
		    * @api 成员函数描述  Author : PKY
		    * @api createDataset
		    * @api Description:
		    * @api Param []:
		    * @api returnorg.jfree.data.time.TimeSeriesCollection:
		    **/
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		String[] categories = { "股东总户数", "前十名持股比例" };
	
		Random random = new Random();
		for (int row = 0; row < categories.length; row++) {
			Vector<Object[]> dateValues = new Vector<Object[]>();
			for (int i = 0; i < 20; i++) {
				String date = (2000 + i) + "-0" + i + "-21";
				System.out.println(date);
				Object[] dateValue = { date, random.nextInt(10) };
				dateValues.add(dateValue);

			}
			TimeSeries timeSeries = ChartUtils.createTimeseries(categories[row], dateValues);
			dataset.addSeries(timeSeries);
		}
		return dataset;
	}

	public static ChartPanel createChart() {
		/**
		    * @api 成员函数描述  Author : PKY
		    * @api createChart
		    * @api Description:
		    * @api Param []:
		    * @api returnorg.jfree.chart.ChartPanel:
		    **/
		// 2：创建Chart[创建不同图形]
		TimeSeriesCollection dataset = createDataset();
		JFreeChart chart = ChartFactory.createTimeSeriesChart("股东总户数", "", "", dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setTimeSeriesRender(chart.getPlot(), true, true);
		// 5:对其他部分进行渲染
		XYPlot xyplot = (XYPlot) chart.getPlot();
		ChartUtils.setXY_XAixs(xyplot);
		ChartUtils.setXY_YAixs(xyplot);
		// 日期X坐标轴
		DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
		domainAxis.setAutoTickUnitSelection(false);
		DateTickUnit dateTickUnit = null;
		if (dataset.getItemCount(0) < 16) {
			//刻度单位月,半年为间隔
			dateTickUnit = new DateTickUnit(DateTickUnitType.MONTH, 6, new SimpleDateFormat("yyyy-MM")); // 第二个参数是时间轴间距
		} else {// 数据过多,不显示数据
			XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
			xyRenderer.setBaseItemLabelsVisible(false);
			dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 1, new SimpleDateFormat("yyyy")); // 第二个参数是时间轴间距
		}
		// 设置时间单位
		domainAxis.setTickUnit(dateTickUnit);
		ChartUtils.setLegendEmptyBorder(chart);
		// 设置图例位置
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

}
