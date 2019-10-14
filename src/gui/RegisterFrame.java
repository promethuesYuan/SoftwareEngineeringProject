package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yuan Zhibo
 * @ClassName RegisterFrame.java
 * @Description ע����棬��Ҫ�����û������û����룬��֤�ɹ�����ע�ᡣע��ɹ�����ת����¼���档
 * @createTime 2019��9��25�� 14:25:00
 */

public class RegisterFrame extends JFrame {
    private JFrame registerFrame = new JFrame("Register");
    private Container container = registerFrame.getContentPane();
    private JButton confirm_Button;
    private JTextField UserName_TextField;
    private JPasswordField User_PasswordField, User_PasswordField_confirm;

    /**
     * @author Yuan Zhibo
     * FunctionName: RegisterFrame
     * @Description ��¼����Ŀ�ܵ�����
     */
    public RegisterFrame(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width/3;
        int height = screen.height/3;
        registerFrame.setBounds(300, 300, width, height);
        container.setLayout(new BorderLayout());
        registerFrame.setResizable(false);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        registerFrame.setVisible(true);
    }

    /**
     * @author Yuan Zhibo
     * FunctionName: init
     * @Description ��¼����Ŀ�ܵĳ�ʼ��
     */
    public void init(){
        //1.���ⲿ�֣�North������һ��Title
        JPanel titlePanel = new JPanel();
        JLabel label_title = new JLabel("�������ϵͳ");
        label_title.setForeground(Color.BLACK);
        label_title.setFont(new Font(AppConstans.FONT, Font.BOLD, 30));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(label_title);
        container.add(titlePanel,"North");

        //2.�м��������ݲ���, Center�������û������룬���������Լ�����ȷ��
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        JLabel label_Username = new JLabel("�û���");
        label_Username.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        label_Username.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        JLabel label_Password = new JLabel("����");
        label_Password.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        JLabel name_restrict = new JLabel("(�û�����4~10λ,������0~9��Ӣ����ĸ���)");
        name_restrict.setFont(new Font(AppConstans.FONT, Font.PLAIN, 15));

        JLabel password_restrict = new JLabel("(���빲4~16λ,������0~9��Ӣ����ĸ���)");
        password_restrict.setFont(new Font(AppConstans.FONT, Font.PLAIN, 15));

        JLabel password_confirm = new JLabel("����ȷ��");
        password_confirm.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        int width = registerFrame.getWidth();
        int height = registerFrame.getHeight();
        label_Username.setBounds   (width/6,20,150,30);
        label_Password.setBounds   (width/6,70,150,30);
        password_confirm.setBounds (width/6,120,250,30);
        name_restrict.setBounds    (width/6,45,310,30);
        password_restrict.setBounds(width/6,95,300,30);


        UserName_TextField = new JTextField();
        UserName_TextField.setColumns(100);

        User_PasswordField = new JPasswordField();
        User_PasswordField.setColumns(100);

        User_PasswordField_confirm = new JPasswordField();
        User_PasswordField_confirm.setColumns(100);

        UserName_TextField.setBounds(width/4+200, 20, 200, 30);
        User_PasswordField.setBounds(width/4+200, 70, 200, 30);
        User_PasswordField_confirm.setBounds(width/4+200, 120, 200, 30);
        UserName_TextField.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        User_PasswordField.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        User_PasswordField_confirm.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        registerPanel.add(label_Username);
        registerPanel.add(label_Password);
        registerPanel.add(name_restrict);
        registerPanel.add(password_restrict);
        registerPanel.add(password_confirm);
        registerPanel.add(UserName_TextField);
        registerPanel.add(User_PasswordField);
        registerPanel.add(User_PasswordField_confirm );
        container.add(registerPanel, "Center");

        //3.ȷ�ϰ�ť��South������һ��ȷ�ϰ�ť��
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        confirm_Button = new JButton("ȷ��");
        confirm_Button.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        confirm_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });

        buttonPanel.add(confirm_Button);
        container.add(buttonPanel,"South");
    }

    /**
     * @author Yuan Zhibo
     * FunctionName: check
     * @Description: �������������Ƿ���������趨�Ĺ淶���Լ��û����Ƿ��Ѿ����ڡ�
     *              ���������ע��ɹ�����������ʾע��ʧ�� 
     **/
    private void check(){
        JOptionPane.showMessageDialog(null,
                    "ע��ɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);
        registerFrame.dispose();
        new LoginFrame();
    }
}
