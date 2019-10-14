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


public  class LineChart {
    /**
    * @api �ӿ����� Author PKY:
    * @api LineChart
    * @api Description:   ���� ����ͼ ������ �� ���÷�ʽ��  LineChart.createChart(List)
    * @api Param :List[num1 , num2 , num3 ,num4 ...]   ÿ���·ݵĶ��������б�    up_to_month = List.length() ��ʾ��ֹ������
    * @api return: ����һ��panel��幩frameʹ��
     **/
    public LineChart() {
    }

    public static DefaultCategoryDataset createDataset() {
        /**
            * @api ��Ա��������  Author : PKY
            * @api createDataset
            * @api Description: ��������ͼ����  �� ���ᡢ���ᣩ
            * @api Param []:List[num1 , num2 , num3 ,num4 ...]
            * @api returnorg.jfree.data.category.DefaultCategoryDataset: ����һ������ͼ���ݼ�
            **/
        String[] categories = {"1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"};
        Vector<Serie> series = new Vector<Serie>();
        series.add(new Serie("С��", new Integer[]{20, 15, 30, 2, 18, 24, 22, 14, 12, 30, 22, 19}));      // һ������
        //series.add(new Serie("New York", new Double[]{83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3}));
        //series.add(new Serie("London", new Double[]{48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2}));
        series.add(new Serie("С��", new Integer[]{4, 3, 13, 23, 5, 8, 2, 20, 12, 12, 5, 6}));

        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories); // 1���������ݼ���
        return dataset;
    }

    public static ChartPanel createChart() {
        /**
            * @api ��Ա��������  Author : PKY
            * @api createChart
            * @api Description:  ����һ������ͼ���
            * @api Param []:
            * @api returnorg.jfree.chart.ChartPanel:  Frame panel
            **/
        JFreeChart chart = ChartFactory.createLineChart("ÿ�¶�����������ͼ", "", "Rainfall (mm)", createDataset());
        ChartUtils.setAntiAlias(chart);// �����
        ChartUtils.setLineRender(chart.getCategoryPlot(), false, true); // 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
        // 5:���������ֽ�����Ⱦ
        ChartUtils.setXAixs(chart.getCategoryPlot());// X��������Ⱦ
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y��������Ⱦ
        // ���ñ�ע�ޱ߿�
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 6:ʹ��chartPanel����
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

}
