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


public  class Compare_LineChart {

    public static DefaultCategoryDataset createDataset() {
        // ��ע���
        String[] categories = {"1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"};
        Vector<Serie> series = new Vector<Serie>();
        // �������ƣ��������е�ֵ����
        series.add(new Serie("С��", new Integer[]{20, 15, 30, 22, 24, 25, 22, 18, 16, 29, 22, 19}));
        //series.add(new Serie("New York", new Double[]{83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3}));
        series.add(new Serie("С��", new Integer[]{1, 3, 4, 1, 2, 3, 4, 5, 4, 5, 1, 4}));
        series.add(new Serie("С��", new Integer[]{10, 8, 4, 6, 8, 12, 14, 7, 12, 10, 7, 9}));
        // 1���������ݼ���
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public static ChartPanel createChart() {
        // 2������Chart[������ͬͼ��]
        JFreeChart chart = ChartFactory.createLineChart("ÿ�¶�����������ͼ", "", "Rainfall (mm)", createDataset());
        // 3:���ÿ���ݣ���ֹ������ʾ�����
        ChartUtils.setAntiAlias(chart);// �����
        // 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
        ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//
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
