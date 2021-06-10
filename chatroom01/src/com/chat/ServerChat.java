package com.chat;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerChat extends JFrame {
    private static DecimalFormat df = null;

    static {
        // 设置数字格式，保留一位有效小数
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }

    //组件定义
    JTextArea serverTa = new JTextArea(10, 20);
    JButton startbt = new JButton("启动");
    JButton stopbt = new JButton("停止");
    JPanel btpanel = new JPanel();
    JScrollPane tapanel = new JScrollPane(serverTa);
    //服务器属性
    private static final int PORT = 8888;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private List<ClientConn> clientConnList = new ArrayList<>();
    private boolean isStart = false;
    private DataOutputStream dos;

    public ServerChat() {
        init();
    }

    public void init() {
        //窗口设置
        setTitle("服务器端");
        setBounds(100, 100, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //组件配置
        serverTa.setFont(new Font("宋体", Font.BOLD, 15));
        serverTa.setEnabled(false);
        tapanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //事件
        startbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (serverSocket == null) {
                        serverSocket = new ServerSocket(PORT);
                    }
                    isStart = true;
                    appendJTextArea("服务器已启动\n");
                } catch (SocketException socketException) {
                    appendJTextArea("服务器意外终止");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        stopbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (serverSocket != null) {
                        serverSocket.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                    isStart = false;
                    appendJTextArea("服务器断开");
                    System.exit(0);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        if (isStart) {
            appendJTextArea("服务器已启动\n");
        } else {
            appendJTextArea("服务器未启动，请点击按钮启动\n");
        }

        //组件添加
        btpanel.add(startbt);
        btpanel.add(stopbt);
        add(tapanel, BorderLayout.CENTER);
        add(btpanel, BorderLayout.SOUTH);

        setVisible(true);
        startServer();
    }

    //启动服务器
    public void startServer() {
        try {
            try {
                if (serverSocket == null) {
                    serverSocket = new ServerSocket(PORT);
                }
                isStart = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (isStart) {
                socket = serverSocket.accept();
                ClientConn conn = new ClientConn(socket);
                if (clientConnList.contains(conn)) continue;
                clientConnList.add(conn);
                String str = "一个客户端连接服务器：" + socket.getInetAddress() + "/" + socket.getPort();
                System.out.println(str);
                appendJTextArea(str + "\n");
            }
        } catch (SocketException e) {
            System.out.println("服务器中断了");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //这是属于服务器端的一个连接对象类
    class ClientConn implements Runnable {
        Socket socket = null;

        public ClientConn(Socket socket) {
            this.socket = socket;
            (new Thread(this)).start();
        }

        //实现多线程接收客户端数据
        @Override
        public void run() {
            try {
                //为了接受每个客户端多次发送的信息
                while (isStart) {
                    System.out.println("连接信息+ " + socket.getInetAddress() + "/" + socket.getPort());
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    System.out.println("111111111");
                    //保存玩家的名称
                    if (Session.session.get(socket.getInetAddress() + "/" + socket.getPort()) == null) {
                        Session.session.put(socket.getInetAddress() + "/" + socket.getPort(), dataInputStream.readUTF().split("\\s+")[1]);
                        try {
                            Thread.sleep(100);
                            for (int i = 1; i <= 3; i++) {
                                File file = new File("archive/" + Session.session.get(socket.getInetAddress() + "/" + socket.getPort()) + "_keep" + i + ".txt");
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    String read = dataInputStream.readUTF();
                    System.out.println("222222222");
                    String msg = Session.session.get(socket.getInetAddress() + "/" + socket.getPort()) + " :  " + read + "\n";//阻塞型
                    if (read.equals("client keeps file to server...")) {
                        restoreClientFile();
                        continue;
                    }
                    String username = (String) Session.session.get(socket.getInetAddress() + "/" + socket.getPort());
                    if (read.equals("server sends file to client")) {
                        int index = Integer.parseInt(dataInputStream.readUTF());
                        Iterator<ClientConn> connIterator = clientConnList.iterator();
                        while (connIterator.hasNext()) {
                            ClientConn clientConn = connIterator.next();
                            if (clientConn.socket.equals(socket)) {
                                sendFileToClient(username + "_keep" + index + ".txt", clientConn, index);
                            }
                        }
                        continue;
                    }
                    System.out.println(msg);
                    appendJTextArea(msg);
                    Iterator<ClientConn> connIterator = clientConnList.iterator();
                    while (connIterator.hasNext()) {
                        ClientConn clientConn = connIterator.next();
                        clientConn.sendmsg(msg);
                    }
                }
            } catch (SocketException e) {
                String msg = Session.session.get(socket.getInetAddress() + "/" + socket.getPort()) + ": 客户端下线";
                System.out.println(msg);
                appendJTextArea(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendmsg(String msg) {
            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendFileToClient(String fileName, ClientConn conn, int index) {
            Socket socket = conn.socket;
            File file = new File("archive/" + fileName);
            if (!file.exists() || file.length() == 0) {
                return;
            }
            conn.sendmsg("是你 " + index);
            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                FileInputStream fis = new FileInputStream(file);
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();
                System.out.println("========= 开始传输文件 " + file.getName() + " =========");
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
                System.out.println("======== 文件发送成功 [FileName：" + fileName + "] [Size：" + getFormatFileSize(progress) + "] ========");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void restoreClientFile() {
            System.out.println("======== 开始接收文件 =========");
            DataInputStream dis = null;
            FileOutputStream fos = null;
            try {
                dis = new DataInputStream(socket.getInputStream());
                // 文件名和长度
                String fileName = dis.readUTF();
                long fileLength = dis.readLong();
                File directory = new File("archive");
                if (!directory.exists()) {
                    directory.mkdir();
                }
                File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
                fos = new FileOutputStream(file);

                // 开始接收文件
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
                    fos.write(bytes, 0, length);
                    fos.flush();
                    if (length < 1024) {
                        break;
                    }
                }
                System.out.println("======== 文件接收成功 [File Name：" + fileName + "] [Size：" + getFormatFileSize(fileLength) + "] ========");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null)
                        fos.close();
//                if (dis != null)
//                    dis.close();
                } catch (Exception e) {
                }
            }
        }
    }

    //实现实时刷新textArea
    public void appendJTextArea(String msg) {
        serverTa.append(msg + "\n");
        serverTa.paintImmediately(serverTa.getBounds());
    }


    private String getFormatFileSize(long length) {
        double size = ((double) length) / (1 << 30);
        if (size >= 1) {
            return df.format(size) + "GB";
        }
        size = ((double) length) / (1 << 20);
        if (size >= 1) {
            return df.format(size) + "MB";
        }
        size = ((double) length) / (1 << 10);
        if (size >= 1) {
            return df.format(size) + "KB";
        }
        return length + "B";
    }

    public static void main(String[] args) {
        ServerChat serverChat = new ServerChat();
    }

}
