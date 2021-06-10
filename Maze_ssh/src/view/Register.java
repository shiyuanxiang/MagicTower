package view;

import mysql.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Register extends JFrame {
    private JTextField username_input = new JFormattedTextField("用户名");
    private JTextField password_input = new JFormattedTextField("密码");

    public Register() {
        init();
    }

    public void init() {
        //边框设置
        setTitle("欢迎注册");
        setBounds(200, 200, 400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //组件定义
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JButton register = new JButton("注册");
        JLabel tips = new JLabel();
        JLabel welcome = new JLabel("欢迎注册魔塔游戏");
        JLabel username_label = new JLabel("账号：");
        JLabel password_label = new JLabel("密码：");
        JTextField username_input = new JTextField(12);
        JTextField password_input = new JTextField(12);
        ImageIcon bg_icon = new ImageIcon("src/data/register/background.png");
        ImagePanel container = new ImagePanel(bg_icon.getImage());

        //组件配置
        container.setLayout(new GridLayout(7, 1));
        welcome.setFont(new Font("宋体", Font.BOLD, 15));
        tips.setFont(new Font("宋体", Font.BOLD, 20));
        tips.setForeground(Color.red);
        tips.setHorizontalAlignment(SwingConstants.CENTER);
        username_label.setHorizontalAlignment(SwingConstants.CENTER);
        password_label.setHorizontalAlignment(SwingConstants.CENTER);
        register.setPreferredSize(new Dimension(100, 20));
        KeyAdapter enter_lister = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String username = username_input.getText();
                    String password = password_input.getText();
                    tips.setText("");
                    if (username.equals("3-15位数字、字母组合") || password.equals("3-15位数字、字母组合")) {
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
                        if (User.register(username, password) == 1) {
                            tips.setText("注册成功");
                        } else {
                            tips.setText("该账户已存在");
                        }
                    } catch (Exception er) {
                        er.printStackTrace();
                    }
                }
            }
        };
        username_input.addKeyListener(enter_lister);
        password_input.addKeyListener(enter_lister);
        register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = username_input.getText();
                String password = password_input.getText();
                tips.setText("");
                if (username.equals("3-15位数字、字母组合") || password.equals("3-15位数字、字母组合")) {
                    tips.setText("输入不能为空");
                    return;
                }
                try {
                    if (User.register(username, password) == 1) {
                        tips.setText("注册成功");
                    } else {
                        tips.setText("该账户已存在");
                    }
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        });
        username_input.addFocusListener(new Tip(username_input, "3-15位数字、字母组合"));
        password_input.addFocusListener(new Tip(password_input, "3-15位数字、字母组合"));
        welcome.setFont(new Font("宋体", Font.BOLD, 15));
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
        p4.add(register);
        p5.setLayout(new GridLayout(1, 1));
        p5.add(tips);

        //容器添加面板
        container.add(p1);
        container.add(p6);
        container.add(p2);
        container.add(p3);
        container.add(p7);
        container.add(p4);
        container.add(p5);
        add(container);

        //设置可见性
        setVisible(true);
    }
}
