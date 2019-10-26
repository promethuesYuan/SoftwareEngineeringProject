package GraphPanel;

import DButils.DBmanager;
import GraphPanel.ChartUtil.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class PieChart {
    /**
    * @api �ӿ����� Author PKY:
    * @api PieChart
    * @api Description:  ���� ��ͼ���
    * @api Param :
    * @api return:
     **/

    private DBmanager dbmanager = null;
    private  String username = null ;

    public PieChart(DBmanager DB, String user) {
        dbmanager = DB ;
        username = user;
    }

    public  DefaultPieDataset createDataset() {
        /**
            * @api ��Ա��������  Author : PKY
            * @api createDataset
            * @api Description:   ������ͼ���ݼ�
            * @api Param []:  Map()
            * @api returnorg.jfree.data.general.DefaultPieDataset:  ���ر�ͼ���ݼ�
            **/

        Map muscle_maps = new HashMap();

        try {
            muscle_maps = dbmanager.return_Pie_data(username);
        }catch (Exception e){
            e.printStackTrace();
        }

        String[] categories = new String[muscle_maps.size()];
        Object [] datas = new Object[muscle_maps.size()];
        int i = 0 ;
        for(Object key : muscle_maps.keySet()){
             String Key = key.toString();
             categories[i] = Key;
             datas[i] = muscle_maps.get(Key);
             i++;
         }
        DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);


//        String[] categories = { "б����", "���Ǽ�", "�Ŷ�ͷ��", "ǰ�ۼ�", "�ش�", "����ͷ��", "��Բ��", "������", "���ȼ�"}; // muscle_name
//        Object[] datas = { 230, 411, 192, 123, 34, 572, 21, 41, 24 };                                                    //num

        return dataset;
    }


    public ChartPanel createChart() {
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