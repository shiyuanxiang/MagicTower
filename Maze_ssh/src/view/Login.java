package view;

import mysql.User;
import session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField username_input = new JFormattedTextField("�˺�");
    private JTextField password_input = new JFormattedTextField("����");
    private JLabel tips = new JLabel();

    public Login() {
        init();
    }

    public boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void init() {
        //�߿�����
        setTitle("��ӭ��¼");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(Session.screen_width / 2 - 200, Session.screen_height / 2 - 170, 400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //�������
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JButton login = new JButton("��¼");
        JLabel welcome = new JLabel("��ӭ��¼ħ����Ϸ");
        JLabel username_label = new JLabel("�˺ţ�");
        JLabel password_label = new JLabel("���룺");
        JLabel register = new JLabel("��û���˺ţ����ע��");
        JTextField username_input = new JTextField(12);
        JTextField password_input = new JTextField(12);
        ImageIcon icon = new ImageIcon("data/login/background.png");
        ImagePanel container = new ImagePanel(icon.getImage());

        //�������
        container.setLayout(new GridLayout(7, 1));
        container.setBounds(Session.screen_width / 2 - 200, Session.screen_height / 2 - 170, 400, 350);
        welcome.setFont(new Font("����", Font.BOLD, 15));
        tips.setFont(new Font("����", Font.BOLD, 20));
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
                    if (username.equals("�������˺�") || password.equals("����������") || username.trim().equals("") || password.trim().equals("")) {
                        tips.setText("���벻��Ϊ��");
                        return;
                    }
                    String reg = "^((?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,15})|([0-9A-Za-z]{3,15})$";
                    if (!username.matches(reg)) {
                        tips.setText("�˺Ÿ�ʽ����");
                        return;
                    } else if (!password.matches(reg)) {
                        tips.setText("�����ʽ����");
                        return;
                    }
                    try {
                        if (User.login(username, password)) {
                            //��¼�ɹ�
                            isLogin = true;
                            System.out.println("��¼�ɹ�");
                            Session.session.put("username", username);
                            Session.session.put("password", password);
                        } else {
                            tips.setText("�˺Ż������������");
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
                if (username.equals("�������˺�") || password.equals("����������") || username.trim().equals("") || password.trim().equals("")) {
                    tips.setText("���벻��Ϊ��");
                    return;
                }
                try {
                    if (User.login(username, password)) {
                        //��¼�ɹ�
                        isLogin = true;
                        System.out.println("��¼�ɹ�");
                        Session.session.put("username", username);
                        Session.session.put("password", password);
                    } else {
                        tips.setText("�˺Ż������������");
                    }
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        });
        username_input.addFocusListener(new Tip(username_input, "�������˺�"));
        password_input.addFocusListener(new Tip(password_input, "����������"));
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p5.setOpaque(false);
        p6.setOpaque(false);
        p7.setOpaque(false);

        //���������
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

        //����������
        container.add(p1);
        container.add(p6);
        container.add(p2);
        container.add(p3);
        container.add(p7);
        container.add(p4);
        container.add(p5);
        container.repaint();
        add(container);

        //���ÿɼ���
        setVisible(true);
    }

    public void try_login() {

    }
}

//������ʾ��
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
