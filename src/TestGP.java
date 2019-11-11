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
        ChartPanel piepanel = new Compare_LineChart(dbmanager,usernames).createChart();   // ��ʼ��ʵ��ʱ����  dbmanager ,  usernames
        piepanel.setEnabled(true);
        piepanel.setVisible(true);
        this.add(piepanel);

        this.setTitle("chart test");                // �����ڱ���
        this.setSize(900, 600);        // �����ڴ�С
        this.setLocationRelativeTo(null);           // ���λ��Ϊ null  ����ʱ����Ļ����
        this.setResizable(false);                   // ��������
        this.setVisible(true);
    }

    public void actionperformed(ActionEvent e) {     //�¼���������
    }

    public static void main(String[] aigs) {         //���������
        new TestGP();
    }

}

