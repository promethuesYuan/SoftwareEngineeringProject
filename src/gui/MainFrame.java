package gui;

import GraphPanel.*;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Yuan Zhibo
 * @ClassName MainFrame.java
 * @Description 程序的主界面
 *              上方为几个按钮，包含功能：查询，添加，删除，肌群分析，健身日志
 *              中间是数据库显示
 *              下方是几个按钮，进行数据库的翻页功能
 * @createTime 2019年10月11日 14:25:00
 */

public class MainFrame extends JFrame {
    private JFrame MainFrame = new JFrame("Fitness Management System");
    private Container container = MainFrame.getContentPane();
    private JPanel jPanelNorth, jPanelSouth;
    private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonFind, jButtonAdd, jButtonPieChart,
            jButtonDelete, jButtonDate;
    private JLabel curPageNumJLabel, jLabelUserName;
    private static JTable jTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;
    private String[] columns = {"日期", "健身项目","组数","次数","重量"};
    private String username;


    public static int curPageNum = 1;

    /**
     * @author Yuan Zhibo
     * FunctionName: MainFrame
     * @Description 主界面的框架的设置
     */
    public MainFrame(String s){
        username = s;
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

    /**
     * @author Yuan Zhibo
     * FunctionName: init
     * @Description: 主界面的生成
     */
    public void init(){
        //标题按钮部分，North。包含五个按钮，分别对应五个功能，查找，删除，添加，肌群分析，健身日志分析。
        //然后中间显示用户名
        jLabelUserName = new JLabel(username);
        jLabelUserName.setFont(new Font(AppConstans.FONT, Font.BOLD, 18));
        jLabelUserName.setHorizontalAlignment(JLabel.CENTER);
        jLabelUserName.setPreferredSize(new Dimension(150,50));
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
            /**
             * @author Yuan Zhibo
             * @Description: 生成一个弹窗，让用户输入想要查询的日期 
             */
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showInputDialog(null,"输入想查找的日期(如:190305, 代表19年3月5日)","输入用户名",1);
                JLabel label = new JLabel("输入想查找的日期(如:190305, 代表19年3月5日)");
                label.setFont(new Font(AppConstans.FONT, Font.PLAIN, 15));
                showInputDialog(label,JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION,"Test",400,200);
            }
        });
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: 生成一个弹窗，让用户添加健身项目
             */
            public void actionPerformed(ActionEvent e) {
                MainFrame.setEnabled(true);
                JFrame now = new AddFrame();

                now.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        MainFrame.setEnabled(true);
                        now.dispose();
                    }
                });
            }
        });

        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: 删除用户在数据显示界面选中的行 
             */
            public void actionPerformed(ActionEvent e) {
                int selectRow = jTable.getSelectedRow();
                if(selectRow != -1) {
                    myTableModel.removeRow(selectRow);
                }
            }
        });
        jButtonPieChart.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: 生成一个弹窗，显示用户健身项目的肌群分析 
             */
            public void actionPerformed(ActionEvent e) {
                ChartPanel piePanel =PieChart.createChart();
                piePanel.setEnabled(true);
                piePanel.setVisible(true);
                JFrame another = new JFrame("肌群图");
                another.setSize(1200,900);
                another.setLocationRelativeTo(null);
                setResizable(false);
                another.setVisible(true);
                another.add(piePanel);
            }
        });
        jButtonDate.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: 生成一个弹窗，显示用户的健身日志 
             */
            public void actionPerformed(ActionEvent e) {
                ChartPanel linePanel = LineChart.createChart();
                linePanel.setEnabled(true);
                linePanel.setVisible(true);
                JFrame another = new JFrame("健身日志");
                another.setSize(1600,900);
                another.setLocationRelativeTo(null);
                setResizable(false);
                another.setVisible(true);
                another.add(linePanel);
            }
        });

        jPanelNorth.add(jButtonFind);
        jPanelNorth.add(jButtonAdd );
        jPanelNorth.add(jButtonDelete);
        jPanelNorth.add(jLabelUserName);
        jPanelNorth.add(jButtonPieChart);
        jPanelNorth.add(jButtonDate);
        container.add(jPanelNorth,"North");

        /**
         * 中间数据库部分，center。将用户健身条目的数据库内容显示在主界面。目前采用hard code实现
         */
        String result[][] = new String[24][5];
        for(int i=0; i<24; i++){
            for(int j=0; j<5; j++){
                result[i][j] = i+j +"";
            }
        }
        myTableModel = new DefaultTableModel(result, columns){
            public boolean isCellEditable(int row, int column) {
                return false;//返回true表示能编辑，false表示不能编辑
            }
        };
        jTable = new JTable(myTableModel);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable.setFont(new Font(AppConstans.FONT, Font.PLAIN, 16));
        jTable.setRowHeight(25);
        JTableHeader header = jTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        header.setFont(new Font(AppConstans.FONT, Font.PLAIN, 16));// 设置表格字体
        header.setReorderingAllowed(false); //设置表头是否可以移动
        header.setResizingAllowed(false); //设置表头是否可以修改大小
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);   //设置数据居中
        jTable.setDefaultRenderer(Object.class, cr);
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);

        container.add(jScrollPane, BorderLayout.CENTER);

        //最下面按钮部分，south。包含四个按钮，分别是首页、前一页、下一页、尾页。中间是用一个标签来显示当前所在页数
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
            /**
             * @author Yuan Zhibo
             * @Description: 前往首页
             */
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonLast.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: 前往尾页
             */
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonPre.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: 前往上一页 
             */
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonNext.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: 前往下一页 
             */
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

    // private void delete(ActionEvent e)
    // {
    //     DefaultTableModel model=(DefaultTableModel) jTable.getModel();    //获得表格模型
    //     int[] selectedRows=jTable.getSelectedRows();
    //     for(int i=selectedRows[0];i<selectedRows.length;i++)
    //     {
    //         model.removeRow(selectedRows[0]);
    //     }
    //     jTable.setModel(model);
    // }

    /**
     * @author Yuan Zhibo
     * @FunctionName: showInputDialog
     * @Description: 生成一个弹窗，让用户输入想要查询的日期 
     */
    public static Object showInputDialog(Object message,int messageType,int optionType,String title,int width,int height){

        JOptionPane pane = new JOptionPane(message, messageType, optionType);

        pane.setWantsInput(true);

        JDialog dialog = pane.createDialog(title);

        dialog.setSize(width, height);

        dialog.show();

        dialog.dispose();

        Object value = pane.getInputValue();

        if(value == JOptionPane.UNINITIALIZED_VALUE)return null;

        return value;

    }

    public static void main(String[] args) {
        new MainFrame("");
    }
}

