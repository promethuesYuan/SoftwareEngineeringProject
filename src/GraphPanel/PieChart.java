package GraphPanel;

import GraphPanel.ChartUtil.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

import java.awt.*;


public class PieChart {
    /**
    * @api �ӿ����� Author PKY:
    * @api PieChart
    * @api Description:  ���� ��ͼ���
    * @api Param :
    * @api return:
     **/
    public PieChart() {
    }

    public static  DefaultPieDataset createDataset() {
        /**
            * @api ��Ա��������  Author : PKY
            * @api createDataset
            * @api Description:   ������ͼ���ݼ�
            * @api Param []:  Map()
            * @api returnorg.jfree.data.general.DefaultPieDataset:  ���ر�ͼ���ݼ�
            **/
        String[] categories = { "б����", "���Ǽ�", "�Ŷ�ͷ��", "ǰ�ۼ�", "�ش�", "����ͷ��", "��Բ��", "������", "���ȼ�"}; // muscle_name
        Object[] datas = { 230, 411, 192, 123, 34, 572, 21, 41, 24 };                                                    //num
        DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);
        return dataset;
    }


    public static ChartPanel createChart() {
        /**
            * @api ��Ա��������  Author : PKY
            * @api createChart
            * @api Description:  ���� ��ͼ panel
            * @api Param []:
            * @api returnorg.jfree.chart.ChartPanel:
            **/

        JFreeChart chart = ChartFactory.createPieChart("����Ⱥ�������", createDataset());
        ChartUtils.setAntiAlias(chart);  // �����
        ChartUtils.setPieRender(chart.getPlot());
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // ��עλ���Ҳ�
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }


}