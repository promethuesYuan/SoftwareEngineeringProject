package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yuan Zhibo
 * @ClassName LoginFrame.java
 * @Description ��¼���棬��Ҫ�����û������û����룬��֤�ɹ����ɵ�¼��Ҳ�����ڴ˽�������ע�ᡱ��ť����ת��ע����档
 * @createTime 2019��9��25�� 14:25:00
 */
public class LoginFrame extends JFrame {
    private JFrame loginFrame = new JFrame("��¼����");
    private Container container = loginFrame.getContentPane();
    private JButton login_bt;
    private JButton register_bt;
    public JTextField UserName_TextField;
    private JPasswordField User_PasswordField;

    /**
     * @author Yuan Zhibo
     * FunctionName: LoginFrame
     * @Description ��¼����Ŀ�ܵ�����
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
     * @Description:��¼�����ʼ��
     */
    public void init() {
        //���ⲿ�֣�North������һ��Title
        JPanel titlePanel = new JPanel();
        JLabel label_title = new JLabel("�������ϵͳ");
        label_title.setForeground(Color.BLACK);
        label_title.setFont(new Font(AppConstans.FONT, Font.BOLD, 30));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(label_title);
        container.add(titlePanel,"North");

        //���벿�֣�Center�������û����������������
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        JLabel label_Username = new JLabel("�û���");
        label_Username.setFont(new Font(AppConstans.FONT, Font.PLAIN, 20));

        JLabel label_Password = new JLabel("����");
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

        //��ť���֣�South��������¼��ť��ע�ᰴť
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        login_bt = new JButton("ȷ��");
        login_bt.setFont(new Font(AppConstans.FONT, Font.PLAIN, 18));
        login_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        /**
         * ע�ᰴť����������󵯳�ע�����
         */
        register_bt = new JButton("ע��");
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
     * @Description: �жϵ�¼�Ƿ�ɹ����ɹ�����ʾ��½�ɹ���Ȼ����������档������ʾ��¼ʧ�ܣ�Ȼ����������
     */
    private void check(){
        if(UserName_TextField.getText().equals("viewer") && String.valueOf(User_PasswordField.getPassword()).equals("viewer")){
            new ViewerFrame();
            loginFrame.dispose();
        }
        else if(UserName_TextField.getText().equals("123") && String.valueOf(User_PasswordField.getPassword()).equals("123")){
            JOptionPane.showMessageDialog(null,
                    "��¼�ɹ���","��ʾ",JOptionPane.PLAIN_MESSAGE);
            new MainFrame(UserName_TextField.getText());
            loginFrame.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "�û������������"
                    , "���� ", 0);
            UserName_TextField.setText("");
            User_PasswordField.setText("");
        }
    }

    //����
    public static void main(String[] args) {
        new LoginFrame();
    }
}
