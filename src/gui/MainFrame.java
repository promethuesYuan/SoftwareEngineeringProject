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
 * @Description �����������
 *              �Ϸ�Ϊ������ť���������ܣ���ѯ����ӣ�ɾ������Ⱥ������������־
 *              �м������ݿ���ʾ
 *              �·��Ǽ�����ť���������ݿ�ķ�ҳ����
 * @createTime 2019��10��11�� 14:25:00
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
    private String[] columns = {"����", "������Ŀ","����","����","����"};
    private String username;


    public static int curPageNum = 1;

    /**
     * @author Yuan Zhibo
     * FunctionName: MainFrame
     * @Description ������Ŀ�ܵ�����
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
     * @Description: �����������
     */
    public void init(){
        //���ⰴť���֣�North�����������ť���ֱ��Ӧ������ܣ����ң�ɾ������ӣ���Ⱥ������������־������
        //Ȼ���м���ʾ�û���
        jLabelUserName = new JLabel(username);
        jLabelUserName.setFont(new Font(AppConstans.FONT, Font.BOLD, 18));
        jLabelUserName.setHorizontalAlignment(JLabel.CENTER);
        jLabelUserName.setPreferredSize(new Dimension(150,50));
        jPanelNorth = new JPanel();
        jPanelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        jButtonFind = new JButton("����");
        jButtonAdd = new JButton("���");
        jButtonDelete = new JButton("ɾ��");
        jButtonPieChart = new JButton("��Ⱥͼ");
        jButtonDate = new JButton("������־");
        jButtonFind.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonAdd.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonPieChart.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonDelete.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonDate.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonFind.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: ����һ�����������û�������Ҫ��ѯ������ 
             */
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showInputDialog(null,"��������ҵ�����(��:190305, ����19��3��5��)","�����û���",1);
                JLabel label = new JLabel("��������ҵ�����(��:190305, ����19��3��5��)");
                label.setFont(new Font(AppConstans.FONT, Font.PLAIN, 15));
                showInputDialog(label,JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION,"Test",400,200);
            }
        });
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: ����һ�����������û���ӽ�����Ŀ
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
             * @Description: ɾ���û���������ʾ����ѡ�е��� 
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
             * @Description: ����һ����������ʾ�û�������Ŀ�ļ�Ⱥ���� 
             */
            public void actionPerformed(ActionEvent e) {
                ChartPanel piePanel =PieChart.createChart();
                piePanel.setEnabled(true);
                piePanel.setVisible(true);
                JFrame another = new JFrame("��Ⱥͼ");
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
             * @Description: ����һ����������ʾ�û��Ľ�����־ 
             */
            public void actionPerformed(ActionEvent e) {
                ChartPanel linePanel = LineChart.createChart();
                linePanel.setEnabled(true);
                linePanel.setVisible(true);
                JFrame another = new JFrame("������־");
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
         * �м����ݿⲿ�֣�center�����û�������Ŀ�����ݿ�������ʾ�������档Ŀǰ����hard codeʵ��
         */
        String result[][] = new String[24][5];
        for(int i=0; i<24; i++){
            for(int j=0; j<5; j++){
                result[i][j] = i+j +"";
            }
        }
        myTableModel = new DefaultTableModel(result, columns){
            public boolean isCellEditable(int row, int column) {
                return false;//����true��ʾ�ܱ༭��false��ʾ���ܱ༭
            }
        };
        jTable = new JTable(myTableModel);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable.setFont(new Font(AppConstans.FONT, Font.PLAIN, 16));
        jTable.setRowHeight(25);
        JTableHeader header = jTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        header.setFont(new Font(AppConstans.FONT, Font.PLAIN, 16));// ���ñ������
        header.setReorderingAllowed(false); //���ñ�ͷ�Ƿ�����ƶ�
        header.setResizingAllowed(false); //���ñ�ͷ�Ƿ�����޸Ĵ�С
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);   //�������ݾ���
        jTable.setDefaultRenderer(Object.class, cr);
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);

        container.add(jScrollPane, BorderLayout.CENTER);

        //�����水ť���֣�south�������ĸ���ť���ֱ�����ҳ��ǰһҳ����һҳ��βҳ���м�����һ����ǩ����ʾ��ǰ����ҳ��
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1,5));
        jButtonFirst = new JButton("��ҳ");
        jButtonLast = new JButton("βҳ");
        jButtonPre = new JButton("��һҳ");
        jButtonNext = new JButton("��һҳ");
        jButtonFirst.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonLast.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonPre.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonNext.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonFirst.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: ǰ����ҳ
             */
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonLast.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: ǰ��βҳ
             */
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonPre.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: ǰ����һҳ 
             */
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonNext.addActionListener(new ActionListener() {
            @Override
            /**
             * @author Yuan Zhibo
             * @Description: ǰ����һҳ 
             */
            public void actionPerformed(ActionEvent e) {

            }
        });

        curPageNumJLabel = new JLabel("�� "+curPageNum+"/99 ҳ");
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
    //     DefaultTableModel model=(DefaultTableModel) jTable.getModel();    //��ñ��ģ��
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
     * @Description: ����һ�����������û�������Ҫ��ѯ������ 
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

