package gui;

import GraphPanel.Compare_BChart;
import GraphPanel.Compare_LineChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yuan Zhibo
 * @ClassName ViewerFrame.java
 * @Description Viewer显示界面。上方用来筛选用户，最多三个，下方用来显示数据分析结果，分别是健身强度图
 * 和健身日志
 * @createTime 2019年10月12日 14:03:00
 */
public class ViewerFrame extends JFrame {
    private JFrame ViewerFrame = new JFrame("Viewer观察界面");
    private Container container = ViewerFrame.getContentPane();

    /**
     * @author Yuan Zhibo
     * FunctionName: ViewerFrame
     * @Description: 观察界面的框架的设置
     */
    public ViewerFrame(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width/3*2;
        int height = screen.height/3*2;
        ViewerFrame.setBounds(600, 300, 1500, 600);
        container.setLayout(new BorderLayout());
        ViewerFrame.setResizable(false);
        ViewerFrame.setLocationRelativeTo(null);
        ViewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        ViewerFrame.setVisible(true);
    }

    /**
     * @author Yuan Zhibo
     * FunctionName: init
     * @Description: 观察界面的生成
     */
    public void init(){
        /**
         * 两个分析图的生成
         */
        ChartPanel Compare_barPanel = Compare_BChart.createChart();
        Compare_barPanel.setEnabled(true);
        Compare_barPanel.setVisible(true);
        int width = ViewerFrame.getWidth();
        int height = ViewerFrame.getHeight();
        ChartPanel Compare_linePanel = Compare_LineChart.createChart();
        Compare_linePanel.setEnabled(true);
        Compare_linePanel.setVisible(true);
        Compare_barPanel.setPreferredSize(new Dimension(width/2-4, height));
        Compare_linePanel.setPreferredSize(new Dimension(width/2-4, height));
        /**
         * 中间添加一个分割线
         */
        JPanel sep = new JPanel();
        sep.setPreferredSize(new Dimension(8, height));
        sep.setBackground(Color.BLACK);
        /**
         * 上方选择用户的生成
         */
        JLabel select = new JLabel("选择用户");
        select.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        //select.setLocation(width/2-50, 20);
        JPanel up = new JPanel();
        JPanel.setDefaultLocale(null);
        JComboBox cmb = new JComboBox();
        cmb.addItem(new JCheckBox("小红",true));
        cmb.addItem(new JCheckBox("小刚",true));
        cmb.addItem(new JCheckBox("小明",true));
        cmb.addItem(10);
        cmb.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        //cmb.setBounds(width/2+50, 20, 150, 30);
        ViewerFrame.add(Compare_barPanel, "West");
        ViewerFrame.add(Compare_linePanel, "East");
        //ViewerFrame.add(sep, "Center");
        up.add(select);
        //up.add(cmb);
        JCheckBox j1 = new JCheckBox("小红");
        JCheckBox j2 = new JCheckBox("小刚");
        JCheckBox j3 = new JCheckBox("小明");
        j1.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        j2.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        j3.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        up.add(j1);
        up.add(j2);
        up.add(j3);
        container.add(up, "North");
    }

    public static void main(String[] args) {
        new ViewerFrame();
    }
}
