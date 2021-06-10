package view;

import controller.Auto;
import controller.AutoArc;
import element.Manager;
import session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.RoundingMode;
import java.net.Socket;
import java.net.SocketException;
import java.text.DecimalFormat;

public class Client extends JPanel {
    ImageIcon bg_icon = new ImageIcon("src/data/chatroom/chat_bg.jpg");
    private JTextArea textArea = new JTextArea(10, 20);
    private JTextField textField = new JTextField(20);
    private JScrollPane tapane = new JScrollPane(textArea);
    private Socket socket = null;
    private final String serverHost = "2001:da8:bc:5ad0:c87c:9d37:392f:cdc0";
    private DataOutputStream dataOutputStream;
    private boolean isConn = false;
    private JPanel panel;
    private static DecimalFormat df = null;
    private Thread thread;
    private DataInputStream dis = null;

    static {
        // �������ָ�ʽ������һλ��ЧС��
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }

    public Client() {
//        �������
        setBackground(new Color(100, 100, 100));
        setLayout(new FlowLayout());
//        �������
        JLabel bg_lable = new JLabel();
        JButton keep_bt = new JButton("�浵");
        JButton restore_bt = new JButton("����");
        JLabel skip = new JLabel(" ����Ƭͷ...");
        JComboBox keep = new JComboBox();
        JComboBox restore = new JComboBox();
//        �������
        bg_lable.setSize(300, 300);
        bg_lable.setIcon(new ImageIcon(bg_icon.getImage().getScaledInstance(bg_lable.getWidth(), bg_lable.getHeight(), bg_icon.getImage().SCALE_DEFAULT)));
        textArea.setOpaque(false);
        tapane.setOpaque(false);
        tapane.getViewport().setOpaque(false);
        tapane.setLocation(1150, 120);
        tapane.setPreferredSize(new Dimension(300, 300));
        textField.setPreferredSize(new Dimension(250, 30));
        textArea.setDisabledTextColor(Color.black);
        textField.setFont(new Font("����", Font.BOLD, 15));
        textArea.setFont(new Font("����", Font.BOLD, 15));
        textArea.setEnabled(false);
        skip.setForeground(Color.white);
        skip.setFont(new Font("���Ĳ���", Font.BOLD, 50));
        skip.setPreferredSize(new Dimension(300, 150));
        keep.setPreferredSize(new Dimension(200, 30));
        keep.setToolTipText("ѡ��浵");
        keep.addItem("                        �浵 1");
        keep.addItem("                        �浵 2");
        keep.addItem("                        �浵 3");
        restore.setPreferredSize(new Dimension(200, 30));
        restore.setToolTipText("ѡ�����");
        restore.addItem("                        ���� 1");
        restore.addItem("                        ���� 2");
        restore.addItem("                        ���� 3");
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = textField.getText();
                textField.setText("");
                if (msg.trim().length() == 0) {
                    return;
                }
                System.out.println(msg);
                sendmsg(msg);
            }
        });
//        ������
        tapane.add(bg_lable);
        add(tapane);
        add(textField);
        add(skip);
        add(keep);
        add(keep_bt);
        add(restore);
        add(restore_bt);

//        ���ӷ�����
        try {
            socket = new Socket(serverHost, 8888);
            System.out.println("���ӷ������ɹ�");
            textArea.append("���ӷ������ɹ�\n");
            //������������������
            sendmsg("���  " + Session.session.get("username") + "  ����");
            isConn = true;
        } catch (SocketException e) {
            System.out.println("������������ֹ");
            textArea.append("������������ֹ");
        } catch (IOException e) {
            System.out.println("���ӷ�����ʧ��");
            e.printStackTrace();
        }
        skip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Timer timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        skip.setText("  ");
                        skip.disable();
                        Auto.isBeforing = false;
                        MyPanelright.location = 700;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                timer.start();
                timer.setRepeats(false);
            }
        });
        keep_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("�����˴浵��");
                int index = (keep.getSelectedIndex() + 1);
                File file = new File("src/data/archive/" + Session.session.get("username") + "_keep" + index + ".txt");
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    for (int i = 0; i < 25; i++) {
                        oos.writeObject(Manager.em[i]);
                    }
                    AutoArc autoArc = Auto.getArc();
                    oos.writeObject(autoArc);
                    sendmsg("client keeps file to server...");
                    keepToServer(index);
                    Thread.sleep(100);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });
        restore_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = (restore.getSelectedIndex() + 1);
                File file = new File("src/data/archive/" + Session.session.get("username") + "_keep" + index + ".txt");
                if (file.length() == 0) {
                    sendmsg("server sends file to client");
                    sendmsg(index + "");
                    return;
                }
                System.out.println("�����˶�����");
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    for (int i = 0; i < 25; i++) {
                        Manager.em[i] = (Manager) ois.readObject();
                    }
                    AutoArc autoArc = (AutoArc) ois.readObject();
                    Auto.setKey(autoArc.key);
                    Auto.setCanGostairs(autoArc.canGostairs);
                    Auto.setCanFly(autoArc.canFly);
                    Auto.setIsBeforing(autoArc.isBeforing);
                    Auto.setRes(autoArc.res);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });
        setVisible(true);
        thread = new Thread(new Client.Recieve());
        thread.start();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea.grabFocus();
            }
        });
    }

    //������Ϣ����������
    public void sendmsg(String msg) {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //���߳̽�����Ϣ����
    class Recieve implements Runnable {
        @Override
        public void run() {
            try {
                while (isConn) {
                    dis = new DataInputStream(socket.getInputStream());
                    String msg = dis.readUTF();
                    if (msg.matches("���� \\d+")) {
                        int index = Integer.parseInt(msg.split("\\s+")[1]);
                        restoreFileFromServer(index, dis);
                    }
                    System.out.println(msg);
                    textArea.append(msg + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //    �ӷ�������ȡ�浵
    public void restoreFileFromServer(int index, DataInputStream dis) {
        FileOutputStream fos = null;
        try {
            // �ļ����ͳ���
            String fileName = Session.session.get("username") + "_keep" + index + ".txt";
            File file = new File("src/data/archive/" + File.separatorChar + fileName);
            System.out.println("======== ��ʼ�����ļ� " + fileName + " =========");
            fos = new FileOutputStream(file);
            // ��ʼ�����ļ�
            fileName = dis.readUTF();
            System.out.println("get name" + fileName);
            long filelen = dis.readLong();
            System.out.println("get len" + filelen);
            byte[] bytes = new byte[1024];
            int len = 0;
            System.out.println("��ʼ");
            while ((len = dis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, len);
                fos.flush();
                System.out.println(len);
                if (len < 1024) {
                    break;
                }
            }
            System.out.println("======== �ļ����ճɹ� [File Name��" + fileName + "] [Size��" + getFormatFileSize(filelen) + "] ========");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (Exception e) {
            }
        }
    }

    private String getFormatFileSize(long len) {
        double size = ((double) len) / (1 << 30);
        if (size >= 1) {
            return df.format(size) + "GB";
        }
        size = ((double) len) / (1 << 20);
        if (size >= 1) {
            return df.format(size) + "MB";
        }
        size = ((double) len) / (1 << 10);
        if (size >= 1) {
            return df.format(size) + "KB";
        }
        return len + "B";
    }

    //�浵���浽������
    public void keepToServer(int index) throws IOException {
        FileInputStream fis = null;
        DataOutputStream dos = dataOutputStream;
        try {
            File file = new File("src/data/archive/" + Session.session.get("username") + "_keep" + index + ".txt");
            if (file.exists()) {
                fis = new FileInputStream(file);
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();
                System.out.println("========= ��ʼ�����ļ� " + file.getName() + " =========");
                byte[] bytes = new byte[1024];
                long size = file.length();
                int len = 0;
                long progress = 0;
                while ((len = fis.read(bytes, 0, bytes.length)) != -1) {
                    dos.write(bytes, 0, len);
                    dos.flush();
                    progress += len;
                    System.out.println("| " + (100 * progress / size) + "% |");
                }
                System.out.println();
                System.out.println("========= ����ɹ� =========");
            } else {
                System.out.println("���浵�ļ�������");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                fis.close();
//            if (dos != null)
//                dos.close();
        }
    }
}
