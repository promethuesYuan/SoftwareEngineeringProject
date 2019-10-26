package GraphPanel;

import DButils.DBmanager;
import GraphPanel.ChartUtil.ChartUtils;
import GraphPanel.ChartUtil.Serie;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;
import java.util.Vector;


public  class LineChart {
    /**
    * @api 接口描述 Author PKY:
    * @api LineChart
    * @api Description:   创建 折线图 工具类 ， 调用方式。  LineChart.createChart(List)
    * @api Param :List[num1 , num2 , num3 ,num4 ...]   每个月份的锻炼次数列表    up_to_month = List.length() 表示截止到几月
    * @api return: 返回一个panel面板供frame使用
     **/
    private DBmanager dbmanager = null;
    private  String username = null ;
    public LineChart(DBmanager DB, String user ) {
        dbmanager = DB ;
        username = user;
    }

    public DefaultCategoryDataset createDataset() {
        /**
            * @api 成员函数描述  Author : PKY
            * @api createDataset
            * @api Description: 生成折线图数据  （ 横轴、纵轴）
            * @api Param []:List[num1 , num2 , num3 ,num4 ...]
            * @api returnorg.jfree.data.category.DefaultCategoryDataset: 返回一个折线图数据集
            **/

        List<Integer> times_list = null;
        try {
            times_list = dbmanager.return_Line_data(username);
        }catch (Exception e){
            e.printStackTrace();
        }

        Integer[] times_array = (Integer[])times_list.toArray(new Integer[times_list.size()]);
        String[] enum_month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

        String[] categories = new String[times_array.length];

        for (int i = times_array.length-1; i>=0 ; i--)
        {
            categories[i] = enum_month[i];
        }



        Vector<Serie> series = new Vector<Serie>();
        series.add(new Serie(username , times_array));
        //series.add(new Serie("小红", new Integer[]{20, 15, 30, 2, 18, 24, 22, 14, 12, 30, 22, 19}));                   // 一组数据
        //series.add(new Serie("New York", new Double[]{83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3}));
        //series.add(new Serie("London", new Double[]{48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2}));
       // series.add(new Serie("小明", new Integer[]{4, 3, 13, 23, 5, 8, 2, 20, 12, 12, 5, 6}));

        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories); // 1：创建数据集合
        return dataset;
    }

    public ChartPanel createChart() {
        /**
            * @api 成员函数描述  Author : PKY
            * @api createChart
            * @api Description:  返回一个折线图面板
            * @api Param []:
            * @api returnorg.jfree.chart.ChartPanel:  Frame panel
            **/
        JFreeChart chart = ChartFactory.createLineChart("每月锻炼次数折线图", "", "Rainfall (mm)", createDataset());
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        ChartUtils.setLineRender(chart.getCategoryPlot(), false, true); // 4:对柱子进行渲染[[采用不同渲染]]
        // 5:对其他部分进行渲染
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

}
