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
    * @api 接口描述 Author PKY:
    * @api PieChart
    * @api Description:  创建 饼图面板
    * @api Param :
    * @api return:
     **/
    public PieChart() {
    }

    public static  DefaultPieDataset createDataset() {
        /**
            * @api 成员函数描述  Author : PKY
            * @api createDataset
            * @api Description:   创建饼图数据集
            * @api Param []:  Map()
            * @api returnorg.jfree.data.general.DefaultPieDataset:  返回饼图数据集
            **/
        String[] categories = { "斜方肌", "三角肌", "肱二头肌", "前臂肌", "胸大肌", "肱三头肌", "大圆肌", "背阔肌", "大腿肌"}; // muscle_name
        Object[] datas = { 230, 411, 192, 123, 34, 572, 21, 41, 24 };                                                    //num
        DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);
        return dataset;
    }


    public static ChartPanel createChart() {
        /**
            * @api 成员函数描述  Author : PKY
            * @api createChart
            * @api Description:  创建 饼图 panel
            * @api Param []:
            * @api returnorg.jfree.chart.ChartPanel:
            **/

        JFreeChart chart = ChartFactory.createPieChart("肌肉群锻炼情况", createDataset());
        ChartUtils.setAntiAlias(chart);  // 抗锯齿
        ChartUtils.setPieRender(chart.getPlot());
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 标注位于右侧
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }


}