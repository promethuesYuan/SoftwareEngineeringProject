package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JFrame loginFrame = new JFrame("Login");
    private Container container = loginFrame.getContentPane();
    private JButton login_bt;
    private JButton register_bt;
    private JTextField UserName_TextField;
    private JPasswordField User_PasswordField;

    public LoginFrame(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width/3;
        int height = screen.height/3;
        loginFrame.setBounds(300, 300, width, height);
        container.setLayout(new BorderLayout());
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        loginFrame.setVisible(true);
    }

    public void init() {
        //标题部分，North
        JPanel titlePanel = new JPanel();
        JLabel label_title = new JLabel("Fitness Management System");
        label_title.setForeground(Color.BLACK);
        label_title.setFont(new Font(AppConstans.FONT, Font.BOLD, 30));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(label_title);
        container.add(titlePanel,"North");

        //输入部分，Center
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        JLabel label_Username = new JLabel("User name");
        label_Username.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        JLabel label_Password = new JLabel("User password");
        label_Password.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        int width = loginFrame.getWidth();
        int height = loginFrame.getHeight();
        label_Username.setBounds(width/4,20,150,30);
        label_Password.setBounds(width/4,60,150,30);


        UserName_TextField = new JTextField();
        UserName_TextField.setColumns(100);

        User_PasswordField = new JPasswordField();
        User_PasswordField.setColumns(100);

        UserName_TextField.setBounds(width/4+200, 20, 120, 30);
        User_PasswordField.setBounds(width/4+200, 60, 120, 30);
        UserName_TextField.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        User_PasswordField.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));
        loginPanel.add(label_Username);
        loginPanel.add(label_Password);
        loginPanel.add(UserName_TextField);
        loginPanel.add(User_PasswordField);
        container.add(loginPanel, "Center");

        //按钮部分，South
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        login_bt = new JButton("Login");
        login_bt.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        login_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });

        register_bt = new JButton("Register");
        register_bt.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        register_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                new RegisterFrame();
            }
        });

        buttonPanel.add(login_bt);
        buttonPanel.add(register_bt);
        container.add(buttonPanel,"South");
    }

    private void check(){
        if(UserName_TextField.getText().equals("123") && String.valueOf(User_PasswordField.getPassword()).equals("123")){
            JOptionPane.showMessageDialog(null,
                    "登录成功！","提示",JOptionPane.PLAIN_MESSAGE);
            loginFrame.dispose();
            new MainFrame();
        }
        else{
            JOptionPane.showMessageDialog(null, "用户名或密码错误！"
                    , "错误 ", 0);
            UserName_TextField.setText("");
            User_PasswordField.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
