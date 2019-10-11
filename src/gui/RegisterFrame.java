package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseChronology;

/**
 * 注册界面，基本格局和登录界面一致，只是少了一个按钮
 */

public class RegisterFrame extends JFrame {
    private JFrame registerFrame = new JFrame("Register");
    private Container container = registerFrame.getContentPane();
    private JButton confirm_Button;
    private JTextField UserName_TextField;
    private JPasswordField User_PasswordField, User_PasswordField_confirm;

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

    public void init(){
        //1.标题部分，North
        JPanel titlePanel = new JPanel();
        JLabel label_title = new JLabel("健身 Management System");
        label_title.setForeground(Color.BLACK);
        label_title.setFont(new Font(AppConstans.FONT, Font.BOLD, 30));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(label_title);
        container.add(titlePanel,"North");

        //2.中间输入内容部分, Center
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        JLabel label_Username = new JLabel("User name");
        label_Username.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        label_Username.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        JLabel label_Password = new JLabel("User password");
        label_Password.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        JLabel name_restrict = new JLabel("(用户名共4~10位,由数字0~9和英文字母组成)");
        name_restrict.setFont(new Font(AppConstans.FONT, Font.PLAIN, 15));

        JLabel password_restrict = new JLabel("(密码共4~16位,由数字0~9和英文字母组成)");
        password_restrict.setFont(new Font(AppConstans.FONT, Font.PLAIN, 15));

        JLabel password_confirm = new JLabel("Password Confirm");
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

        //3.确认按钮，South
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        confirm_Button = new JButton("确认");
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

    private void check(){
        JOptionPane.showMessageDialog(null,
                    "注册成功","提示",JOptionPane.PLAIN_MESSAGE);
        registerFrame.dispose();
        new LoginFrame();
    }
}
