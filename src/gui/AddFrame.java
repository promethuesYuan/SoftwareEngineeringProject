package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yuan Zhibo
 * @ClassName AddFrame.java
 * @Description 添加界面，添加健身内容
 * @createTime 2019年10月11日 15:19:00
 */
public class AddFrame extends JFrame {
    private JFrame AddFrame = new JFrame("添加项目");
    private Container container = AddFrame.getContentPane();
    private JPanel jPanelNorth, jPanelCenter, jPanelSouth;
    private JButton jButtonConfirm, jButtonConceal;
    private JTextField name;
    private JComboBox Group,Time,Weight;

    public AddFrame(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width/3;
        int height = screen.height/3;
        AddFrame.setBounds(300, 300, width, height);
        container.setLayout(new BorderLayout());
        AddFrame.setResizable(false);
        AddFrame.setLocationRelativeTo(null);
        AddFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
        AddFrame.setVisible(true);
    }

    public void init() {
        //标题部分north
        jPanelNorth = new JPanel();
        JLabel jLabelTitle = new JLabel("今天又做了什么运动呀:)");
        jLabelTitle.setFont(new Font(AppConstans.FONT, Font.BOLD, 25));
        jPanelNorth.setLayout(new FlowLayout());
        jPanelNorth.add(jLabelTitle);
        container.add(jPanelNorth, BorderLayout.NORTH);

        //中间添加部分
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(null);

        JLabel jLabelName = new JLabel("名称");
        JLabel jLabelGroups = new JLabel("组数");
        JLabel jLabelTimes = new JLabel("次数");
        JLabel jLabelWeight = new JLabel("重量(kg)");
        jLabelName.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        jLabelGroups.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        jLabelTimes.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        jLabelWeight.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        int width = AddFrame.getWidth();
        int gap=40; //上下间距
        jLabelName.setBounds(width/4,20,150,30);
        jLabelGroups.setBounds(width/4,20+gap*1,150,30);
        jLabelTimes.setBounds(width/4,20+gap*2,150,30);
        jLabelWeight.setBounds(width/4,20+gap*3,150,30);

        Group = GroupBox();
        Time = TimeBox();
        Weight = WeightBox();
        Group.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        Time.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        Weight.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        int  gapHorizen = 120; //左右间距
        name = new JTextField();
        name.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        name.setBounds(width/4+gapHorizen, 20, 150, 30);
        Group.setBounds(width/4+gapHorizen, 20+gap*1, 150, 30);
        Time.setBounds(width/4+gapHorizen, 20+gap*2, 150, 30);
        Weight.setBounds(width/4+gapHorizen, 20+gap*3, 150, 30);

        jPanelCenter.add(jLabelName);
        jPanelCenter.add(jLabelGroups);
        jPanelCenter.add(jLabelTimes);
        jPanelCenter.add(jLabelWeight);
        jPanelCenter.add(name);
        jPanelCenter.add(Group);
        jPanelCenter.add(Time);
        jPanelCenter.add(Weight);
        container.add(jPanelCenter, BorderLayout.CENTER);

        //按钮部分south
        jPanelSouth = new JPanel();
        jButtonConfirm = new JButton("提交");
        jButtonConceal = new JButton("取消");
        jButtonConfirm.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonConceal.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        jButtonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtonConceal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFrame.dispose();
            }
        });

        jPanelSouth.add(jButtonConfirm);
        //jPanelSouth.add(jButtonConceal);
        container.add(jPanelSouth, BorderLayout.SOUTH);
    }

    public JComboBox GroupBox() {
        JComboBox cmb = new JComboBox();
        for(int i=0; i<=15; i++){
            cmb.addItem(i+"");
        }
        return cmb;
    }

    public JComboBox TimeBox(){
        JComboBox cmb = new JComboBox();
        for(int i=0; i<=15; i++){
            cmb.addItem(i+"");
        }
        for(int i=20; i<=60; i+=5){
            cmb.addItem(i+"");
        }
        return cmb;
    }

    public JComboBox WeightBox(){
        JComboBox cmb = new JComboBox();
        cmb.addItem(0);
        cmb.addItem(1);
        cmb.addItem(2.5);
        cmb.addItem(5);
        cmb.addItem(7.5);
        cmb.addItem(10);
        cmb.addItem(12.5);
        cmb.addItem(15);
        cmb.addItem(20);
        for(int i=25; i<=120; i+=5){
            cmb.addItem(i);
        }
        return cmb;
    }


    public static void main(String[] args) {
        new AddFrame();
    }
}
