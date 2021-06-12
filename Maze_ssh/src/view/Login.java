package view;

import mysql.User;
import session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField username_input = new JFormattedTextField("账号");
    private JTextField password_input = new JFormattedTextField("密码");
    private JLabel tips = new JLabel();

    public Login() {
        init();
    }

    public boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void init() {
        //边框设置
        setTitle("欢迎登录");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(Session.screen_width / 2 - 200, Session.screen_height / 2 - 170, 400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //组件定义
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JButton login = new JButton("登录");
        JLabel welcome = new JLabel("欢迎登录魔塔游戏");
        JLabel username_label = new JLabel("账号：");
        JLabel password_label = new JLabel("密码：");
        JLabel register = new JLabel("还没有账号？点击注册");
        JTextField username_input = new JTextField(12);
        JTextField password_input = new JTextField(12);
        ImageIcon icon = new ImageIcon("data/login/background.png");
        ImagePanel container = new ImagePanel(icon.getImage());

        //组件配置
        container.setLayout(new GridLayout(7, 1));
        container.setBounds(Session.screen_width / 2 - 200, Session.screen_height / 2 - 170, 400, 350);
        welcome.setFont(new Font("宋体", Font.BOLD, 15));
        tips.setFont(new Font("宋体", Font.BOLD, 20));
        tips.setForeground(Color.red);
        tips.setHorizontalAlignment(SwingConstants.CENTER);
        username_label.setHorizontalAlignment(SwingConstants.CENTER);
        password_label.setHorizontalAlignment(SwingConstants.CENTER);
        register.setForeground(new Color(157, 235, 231));
        register.setHorizontalAlignment(SwingConstants.CENTER);
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Register registerWin = new Register();
            }
        });
        KeyAdapter enter_lister = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String username = username_input.getText();
                    String password = password_input.getText();
                    tips.setText("");
                    if (username.equals("请输入账号") || password.equals("请输入密码") || username.trim().equals("") || password.trim().equals("")) {
                        tips.setText("输入不能为空");
                        return;
                    }
                    String reg = "^((?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,15})|([0-9A-Za-z]{3,15})$";
                    if (!username.matches(reg)) {
                        tips.setText("账号格式错误");
                        return;
                    } else if (!password.matches(reg)) {
                        tips.setText("密码格式错误");
                        return;
                    }
                    try {
                        if (User.login(username, password)) {
                            //登录成功
                            isLogin = true;
                            System.out.println("登录成功");
                            Session.session.put("username", username);
                            Session.session.put("password", password);
                        } else {
                            tips.setText("账号或密码输入错误");
                        }
                    } catch (Exception er) {
                        er.printStackTrace();
                    }
                }
            }
        };
        password_input.addKeyListener(enter_lister);
        username_input.addKeyListener(enter_lister);
        login.setPreferredSize(new Dimension(100, 20));
        login.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = username_input.getText();
                String password = password_input.getText();
                tips.setText("");
                if (username.equals("请输入账号") || password.equals("请输入密码") || username.trim().equals("") || password.trim().equals("")) {
                    tips.setText("输入不能为空");
                    return;
                }
                try {
                    if (User.login(username, password)) {
                        //登录成功
                        isLogin = true;
                        System.out.println("登录成功");
                        Session.session.put("username", username);
                        Session.session.put("password", password);
                    } else {
                        tips.setText("账号或密码输入错误");
                    }
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        });
        username_input.addFocusListener(new Tip(username_input, "请输入账号"));
        password_input.addFocusListener(new Tip(password_input, "请输入密码"));
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p5.setOpaque(false);
        p6.setOpaque(false);
        p7.setOpaque(false);

        //面板添加组件
        p1.add(welcome);
        p2.setLayout(new FlowLayout());
        p2.add(username_label);
        p2.add(username_input);
        p3.setLayout(new FlowLayout());
        p3.add(password_label);
        p3.add(password_input);
        p4.setLayout(new FlowLayout());
        p4.add(login);
        p5.setLayout(new GridLayout(2, 1));
        p5.add(register);
        p5.add(tips);

        //容器添加面板
        container.add(p1);
        container.add(p6);
        container.add(p2);
        container.add(p3);
        container.add(p7);
        container.add(p4);
        container.add(p5);
        container.repaint();
        add(container);

        //设置可见性
        setVisible(true);
    }

    public void try_login() {

    }
}

//输入提示类
class Tip implements FocusListener {
    private String hidentext;
    private JTextField jTextField;

    public Tip(JTextField jTextField, String hidentext) {
        this.hidentext = hidentext;
        this.jTextField = jTextField;
        jTextField.setText(hidentext);
    }

    @Override
    public void focusGained(FocusEvent e) {
        String text = jTextField.getText();
        if (text.equals(hidentext)) {
            jTextField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        String text = jTextField.getText();
        if (text.equals("")) {
            jTextField.setText(hidentext);
        }
    }
}
