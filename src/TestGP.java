import DButils.DBmanager;
import GraphPanel.Compare_LineChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class TestGP extends JFrame{

    public TestGP(){

        DBmanager dbmanager = new DBmanager();
        //String username = "pky";
        String [] usernames = {"pky" , "tjl" , "yzb"};
        ChartPanel piepanel = new Compare_LineChart(dbmanager,usernames).createChart();   // 初始化实例时传入  dbmanager ,  usernames
        piepanel.setEnabled(true);
        piepanel.setVisible(true);
        this.add(piepanel);

        this.setTitle("chart test");                // 主窗口标题
        this.setSize(900, 600);        // 主窗口大小
        this.setLocationRelativeTo(null);           // 相对位置为 null  ，此时在屏幕正中
        this.setResizable(false);                   // 不可缩放
        this.setVisible(true);
    }

    public void actionperformed(ActionEvent e) {     //事件监听函数
    }

    public static void main(String[] aigs) {         //主函数入口
        new TestGP();
    }

}

