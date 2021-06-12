package view;

import mysql.MyConfig;
import session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Config extends JDialog {
    private JTextField username_input = new JFormattedTextField("账号");
    private JTextField password_input = new JFormattedTextField("密码");
    private JTextField ip_input = new JFormattedTextField("密码");
    public boolean isRecorded = false;
    private JLabel tips = new JLabel();

    public Config() {
    }

    public void init() {
        //边框设置
        setTitle("您的mysql账号或密码有误，请输入正确信息");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(500, 500, 400, 350);
        setModal(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //组件定义
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p31 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JButton submit = new JButton("提交");
        JLabel welcome = new JLabel("配置界面");
        JLabel username_label = new JLabel("mysql账号：");
        JLabel password_label = new JLabel("mysql密码：");
        JLabel ip_label = new JLabel("服务器IP：");
        JTextField username_input = new JTextField(12);
        JTextField password_input = new JTextField(12);
        JTextField ip_input = new JTextField(12);
        ImageIcon icon = new ImageIcon("data/login/background.png");
        ImagePanel container = new ImagePanel(icon.getImage());

        //组件配置
        container.setLayout(new GridLayout(9, 1));
        container.setBounds(500, 500, 400, 350);
        welcome.setFont(new Font("宋体", Font.BOLD, 15));
        tips.setFont(new Font("宋体", Font.BOLD, 20));
        tips.setForeground(Color.red);
        tips.setHorizontalAlignment(SwingConstants.CENTER);
        username_label.setHorizontalAlignment(SwingConstants.CENTER);
        password_label.setHorizontalAlignment(SwingConstants.CENTER);
        ip_label.setHorizontalAlignment(SwingConstants.CENTER);
        KeyAdapter enter_lister = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String username = username_input.getText();
                    String password = password_input.getText();
                    String ip = ip_input.getText();
                    tips.setText("");
                    if (username.equals("请输入账号") || password.equals("请输入密码") || username.trim().equals("") || password.trim().equals("")) {
                        tips.setText("输入不能为空");
                        return;
                    }
                    writeConfig(username, password, ip);
                }
            }
        };
        password_input.addKeyListener(enter_lister);
        username_input.addKeyListener(enter_lister);
        ip_input.addKeyListener(enter_lister);
        submit.setPreferredSize(new Dimension(100, 20));
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = username_input.getText();
                String password = password_input.getText();
                String ip = ip_input.getText();
                if (username.equals("请输入账号") || password.equals("请输入密码") || username.trim().equals("") || password.trim().equals("")) {
                    tips.setText("输入不能为空");
                    return;
                }
                writeConfig(username, password, ip);
            }
        });
        username_input.addFocusListener(new Tip(username_input, "请输入账号"));
        password_input.addFocusListener(new Tip(password_input, "请输入密码"));
        ip_input.addFocusListener(new Tip(ip_input, "请输入服务器IP"));
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p5.setOpaque(false);
        p6.setOpaque(false);
        p7.setOpaque(false);
        p31.setOpaque(false);
        //面板添加组件
        p1.add(welcome);
        p2.setLayout(new FlowLayout());
        p2.add(username_label);
        p2.add(username_input);
        p3.setLayout(new FlowLayout());
        p3.add(password_label);
        p3.add(password_input);
        p31.setLayout(new FlowLayout());
        p31.add(ip_label);
        p31.add(ip_input);
        p4.setLayout(new FlowLayout());
        p4.add(submit);
        p5.setLayout(new GridLayout(2, 1));
        p5.add(tips);

        //容器添加面板
        container.add(p1);
        container.add(p6);
        container.add(p2);
        container.add(p3);
        container.add(p31);
        container.add(p7);
        container.add(p4);
        container.add(p5);
        container.repaint();
        add(container);

        //设置可见性
        setVisible(true);
    }

    public void writeConfig(String db_u, String db_p, String ip) {
        if (ip.equals("请输入服务器IP")) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                ip = addr.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        MyConfig config = new MyConfig(db_u, db_p, ip);
        File file = new File("data/config/config.txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tips.setText("配置修改成功！重启生效...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRecorded = true;
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

    public void readConfig() {
        MyConfig config = null;
        File file = new File("data/config/config.txt");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            config = (MyConfig) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Session.session.put("db_username", config.db_username);
        Session.session.put("db_password", config.db_password);
        Session.session.put("ip", config.ip);
    }
}
