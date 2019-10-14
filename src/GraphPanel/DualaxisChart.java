package GraphPanel;

import GraphPanel.ChartUtil.ChartUtils;
import GraphPanel.ChartUtil.Serie;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import java.awt.*;
import java.util.Vector;



public class DualaxisChart {
    /**
    * @api �ӿ����� Author PKY:
    * @api DualaxisChart
    * @api Description:  ����һ������ͼ+ ����ͼ���
    * @api Param :
    * @api return:
     **/
	public DualaxisChart() {
	}

	public static  ChartPanel createChart() {
		String[] categories = { "1999-12-31", "2000-12-31", "2001-12-31", "2002-12-31", "2003-12-31", "2004-12-31", "2005-12-31", "2006-12-31", "2007-12-31",
				"2008-12-31", "2009-12-31", "2010-12-31", "2011-12-31", "2012-12-31", "2013-12-31" };
		for (int i = 0; i < categories.length; i++) {
			categories[i]=categories[i].substring(0, 4);
		}
		Vector<Serie> seriesNetProfit = new Vector<Serie>();
		// ������
		Object[] netProfit = { 92669.20, 95790.47, 106187.80, 128530.88, 156608.82, 193003.08, 255800.38, 335302.66, 549877.54, 1251596.81, 1321658.11,
				1917721.09, 2728598.10, 3418600.00, 4092200.00 };
		// ����֧����
		Object[] payoutRatio = { "39.01", "--", "45.39", "30.46", "27.50", "24.34", "19.90", "19.48", "12.67", "10.40", "10.02", "11.97", "20.51", "30.01",
				" --" };
		seriesNetProfit.add(new Serie("������", netProfit));

		Vector<Serie> seriesPayoutRatio = new Vector<Serie>();
		seriesPayoutRatio.add(new Serie("����֧����", payoutRatio));

		DefaultCategoryDataset datasetNetProfit = ChartUtils.createDefaultCategoryDataset(seriesNetProfit, categories);
		JFreeChart chart = ChartFactory.createBarChart("", "", "������(��Ԫ)", datasetNetProfit);
		ChartUtils.setAntiAlias(chart);// �����
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);
		// ����������
		ChartUtils.setXAixs(chart.getCategoryPlot());
		ChartUtils.setYAixs(chart.getCategoryPlot());
		// linechart
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		DefaultCategoryDataset datasetPayoutRatio = ChartUtils.createDefaultCategoryDataset(seriesPayoutRatio, categories);
		categoryplot.setDataset(1, datasetPayoutRatio);
		categoryplot.mapDatasetToRangeAxis(1, 1);
		// X��̶�
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// �Ҳ�Y��
		NumberAxis numberaxis = new NumberAxis("����֧����(%)");
		categoryplot.setRangeAxis(1, numberaxis);
		// ����Y�̶�
		numberaxis.setAxisLineVisible(false);
		numberaxis.setTickMarksVisible(false);

		// ��������ͼ��ʽ
		LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
		lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
		lineRenderer.setBaseShapesVisible(true);// ���ݵ������״
		categoryplot.setRenderer(1, lineRenderer);
		
		categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// ����Z-index, ������ͼ��ǰ��
		chart.getLegend().setPosition(RectangleEdge.TOP);//��ע�ڶ���
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

}
