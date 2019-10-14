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
	* @api �ӿ����� Author PKY: 
	* @api TimeSeriesChart
	* @api Description: 
	* @api Param :
	* @api return:
	 **/
	public TimeSeriesChart() {
	}


	public static TimeSeriesCollection createDataset() {
		/**
		    * @api ��Ա��������  Author : PKY
		    * @api createDataset
		    * @api Description:
		    * @api Param []:
		    * @api returnorg.jfree.data.time.TimeSeriesCollection:
		    **/
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		String[] categories = { "�ɶ��ܻ���", "ǰʮ���ֹɱ���" };
	
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
		    * @api ��Ա��������  Author : PKY
		    * @api createChart
		    * @api Description:
		    * @api Param []:
		    * @api returnorg.jfree.chart.ChartPanel:
		    **/
		// 2������Chart[������ͬͼ��]
		TimeSeriesCollection dataset = createDataset();
		JFreeChart chart = ChartFactory.createTimeSeriesChart("�ɶ��ܻ���", "", "", dataset);
		// 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setAntiAlias(chart);// �����
		// 4:�����ӽ�����Ⱦ[������ͬͼ��]
		ChartUtils.setTimeSeriesRender(chart.getPlot(), true, true);
		// 5:���������ֽ�����Ⱦ
		XYPlot xyplot = (XYPlot) chart.getPlot();
		ChartUtils.setXY_XAixs(xyplot);
		ChartUtils.setXY_YAixs(xyplot);
		// ����X������
		DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
		domainAxis.setAutoTickUnitSelection(false);
		DateTickUnit dateTickUnit = null;
		if (dataset.getItemCount(0) < 16) {
			//�̶ȵ�λ��,����Ϊ���
			dateTickUnit = new DateTickUnit(DateTickUnitType.MONTH, 6, new SimpleDateFormat("yyyy-MM")); // �ڶ���������ʱ������
		} else {// ���ݹ���,����ʾ����
			XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
			xyRenderer.setBaseItemLabelsVisible(false);
			dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 1, new SimpleDateFormat("yyyy")); // �ڶ���������ʱ������
		}
		// ����ʱ�䵥λ
		domainAxis.setTickUnit(dateTickUnit);
		ChartUtils.setLegendEmptyBorder(chart);
		// ����ͼ��λ��
		// 6:ʹ��chartPanel����
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

}
