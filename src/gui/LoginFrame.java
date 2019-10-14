package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yuan Zhibo
 * @ClassName LoginFrame.java
 * @Description 登录界面，需要输入用户名和用户密码，验证成功即可登录。也可以在此界面点击“注册”按钮，跳转至注册界面。
 * @createTime 2019年9月25日 14:25:00
 */
public class LoginFrame extends JFrame {
    private JFrame loginFrame = new JFrame("登录界面");
    private Container container = loginFrame.getContentPane();
    private JButton login_bt;
    private JButton register_bt;
    public JTextField UserName_TextField;
    private JPasswordField User_PasswordField;

    /**
     * @author Yuan Zhibo
     * FunctionName: LoginFrame
     * @Description 登录界面的框架的设置
     */
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

    /**
     * @author Yuan Zhibo
     * FunctionName: init
     * @Description:登录界面初始化
     */
    public void init() {
        //标题部分，North。包含一个Title
        JPanel titlePanel = new JPanel();
        JLabel label_title = new JLabel("健身管理系统");
        label_title.setForeground(Color.BLACK);
        label_title.setFont(new Font(AppConstans.FONT, Font.BOLD, 30));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(label_title);
        container.add(titlePanel,"North");

        //输入部分，Center。包含用户名输入和密码输入
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        JLabel label_Username = new JLabel("用户名");
        label_Username.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        JLabel label_Password = new JLabel("密码");
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

        //按钮部分，South。包含登录按钮和注册按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        login_bt = new JButton("确认");
        login_bt.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        login_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        /**
         * 注册按钮，弹出点击后弹出注册界面
         */
        register_bt = new JButton("注册");
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

    /**
     * @author Yuan Zhibo
     * FunctionName: check
     * @Description: 判断登录是否成功，成功会提示登陆成功，然后进入主界面。否则提示登录失败，然后重新输入
     */
    private void check(){
        if(UserName_TextField.getText().equals("viewer") && String.valueOf(User_PasswordField.getPassword()).equals("viewer")){
            new ViewerFrame();
            loginFrame.dispose();
        }
        else if(UserName_TextField.getText().equals("123") && String.valueOf(User_PasswordField.getPassword()).equals("123")){
            JOptionPane.showMessageDialog(null,
                    "登录成功！","提示",JOptionPane.PLAIN_MESSAGE);
            new MainFrame(UserName_TextField.getText());
            loginFrame.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "用户名或密码错误！"
                    , "错误 ", 0);
            UserName_TextField.setText("");
            User_PasswordField.setText("");
        }
    }

    //调试
    public static void main(String[] args) {
        new LoginFrame();
    }
}
