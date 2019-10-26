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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Compare_BarChart {

    private DBmanager dbmanager = null;
    private  String [] users = null ;

    public Compare_BarChart(DBmanager DB, String [] user ) {
        dbmanager = DB ;
        users = user;
    }

    public  DefaultCategoryDataset createDataset(){
        /**
         * @api 成员函数描述  Author : PKY
         * @api createDataset
         * @api Description:   生成条形图数据集的函数
         * @api Param []:   Map {"item" :  value}
         * @api returnorg.jfree.data.category.DefaultCategoryDataset:   条形图数据集
         **/

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      // 生成月份
        String date = df.format(new Date());// new Date()为获取当前系统时间
        int now_month = Integer.valueOf(date.split("-")[1]);
        String[] enum_month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        String[] categories = new String[now_month];

        for (int i = now_month-1; i>=0 ; i--)
        {
            categories[i] = enum_month[i];
        }

        Vector<Serie> series = new Vector<Serie>();
        for (String username : users){

            List<Float> strength_list = null;
            try {
                strength_list = dbmanager.return_bar_data(username);
            }catch (Exception e){
                e.printStackTrace();
            }

            Float[] strength_array = (Float[])strength_list.toArray(new Float[strength_list.size()]);

            // 柱子名称：柱子所有的值集合
            series.add(new Serie(username , strength_array));
        }


//		series.add(new Serie("Tokyo", new Double[] { 49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 }));   // 条形图的一组数据
//		series.add(new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 }));
//		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
//		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
        // 1：创建数据集合
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public  ChartPanel createChart() {
        /**
         * @api 成员函数描述  Author : PKY
         * @api createChart
         * @api Description: 创建 panel 面板的函数的函数
         * @api Param []:
         * @api returnorg.jfree.chart.ChartPanel:
         **/

        JFreeChart chart = ChartFactory.createBarChart("健身强度月度表", "", "强度指数 timse", createDataset());
        ChartUtils.setAntiAlias(chart); // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//

        ChartUtils.setXAixs(chart.getCategoryPlot());           // X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());            // Y坐标轴渲染
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));// 设置标注无边框
        ChartPanel chartPanel = new ChartPanel(chart);           // 6:使用chartPanel接收
        return chartPanel;
    }

}

