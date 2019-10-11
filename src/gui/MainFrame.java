package gui;

import javafx.scene.control.SelectionModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private JFrame MainFrame = new JFrame("Fitness Management System");
    private Container container = MainFrame.getContentPane();
    private JPanel jPanelNorth, jPanelSouth, jPanelCenter;
    private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonFind, jButtonAdd, jButtonPieChart,
            jButtonDelete, jButtonDate;
    private JLabel curPageNumJLabel, jLabelUserName;
    private static JTable jTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;
    private String[] columns = {"id", "健身项目","组数","次数","重量","日期"};


    public static int curPageNum = 1;

    public MainFrame(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width/2;
        int height = screen.height/2;
        MainFrame.setBounds(5,5,width,height);
        container.setLayout(new BorderLayout());
        MainFrame.setResizable(false);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        MainFrame.setVisible(true);
    }

    public void init(){
        //标题按钮部分，North
        jLabelUserName = new JLabel("user name");
        jLabelUserName.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jLabelUserName.setHorizontalAlignment(JLabel.CENTER);
        jPanelNorth = new JPanel();
        jPanelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        jButtonFind = new JButton("查找");
        jButtonAdd = new JButton("添加");
        jButtonDelete = new JButton("删除");
        jButtonPieChart = new JButton("肌群图");
        jButtonDate = new JButton("健身日志");
        jButtonFind.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonAdd.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonPieChart.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonDelete.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonDate.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRow = jTable.getSelectedRow();
                if(selectRow != -1) {
                    myTableModel.removeRow(selectRow);
                }
            }
        });
        jButtonPieChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jPanelNorth.add(jButtonFind);
        jPanelNorth.add(jButtonAdd );
        jPanelNorth.add(jButtonDelete);
        jPanelNorth.add(jLabelUserName);
        jPanelNorth.add(jButtonPieChart);
        jPanelNorth.add(jButtonDate);
        container.add(jPanelNorth,"North");

        //中间数据库部分，center
        String result[][] = new String[6][6];
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                result[i][j] = i+j +"";
            }
        }
        myTableModel = new DefaultTableModel(result, columns);
        jTable = new JTable(myTableModel);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, cr);
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);


        container.add(jScrollPane, BorderLayout.CENTER);

        //最下面按钮部分，south
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1,5));
        jButtonFirst = new JButton("首页");
        jButtonLast = new JButton("尾页");
        jButtonPre = new JButton("上一页");
        jButtonNext = new JButton("下一页");
        jButtonFirst.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonLast.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonPre.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonNext.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        curPageNumJLabel = new JLabel("第 "+curPageNum+"/99 页");
        curPageNumJLabel.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        curPageNumJLabel.setHorizontalAlignment(JLabel.CENTER);

        jPanelSouth.add(jButtonFirst);
        jPanelSouth.add(jButtonPre);
        jPanelSouth.add(curPageNumJLabel);
        jPanelSouth.add(jButtonNext);
        jPanelSouth.add(jButtonLast);
        container.add(jPanelSouth, "South");

    }

    private void delete(ActionEvent e)
    {
        DefaultTableModel model=(DefaultTableModel) jTable.getModel();    //获得表格模型
        int[] selectedRows=jTable.getSelectedRows();
        for(int i=selectedRows[0];i<selectedRows.length;i++)
        {
            model.removeRow(selectedRows[0]);
        }
        jTable.setModel(model);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
