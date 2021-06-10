package view;

import session.Session;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

public class MyFrame extends JFrame {
    private JPanel panelleft;
    private JPanel panelright;
    private TalkPanel talkpanel;
    private EnemyLookPanel enemylookpanel;
    private Compass compass;
    private Client client = new Client();
    public static Thread panelleftThread;
    public static Thread panelrightThread;
    public static Thread talkpanelThread;
    public static Thread enemylookpanelThread;
    public static Thread compassThread;
    public static Thread clientThread;
    private Thread thread;
    private KeyListener key;
    public ScrollEnemyLookPanel scrollEnemyLookPanel;

    public static Thread scpThread;


    public MyFrame() {
        init();
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void init() {
        setTitle("魔塔");
        setIconImage(new ImageIcon("src/data/man/2.jpg").getImage());
        setBounds(100, 200, 1120, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        MyFrame that = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //存档到服务器
                that.setVisible(false);
                that.dispose();
                try {
                    for (int i = 1; i <= 3; i++) {
                        File file = new File("src/data/archive/" + Session.session.get("username") + "_keep" + i + ".txt");
                        if (file.length() == 0) {
                            continue;
                        }
                        client.sendmsg("client keeps file to server...");
                        client.keepToServer(i);
                        Thread.sleep(1000);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    public void showThis() {
//        组件配置
        this.setLocation(new Point(100, 100));
        this.setSize(1440, 750);
        panelright.setBounds(220, 0, 720, 720);
        compass.setBounds(270, 50, 620, 620);
        compass.setBackground(new Color(255, 255, 255));
        panelright.setBackground(new Color(100, 100, 100));
        enemylookpanel.setPreferredSize(new Dimension(720, 1000));
        panelleft.setBounds(0, 0, 300, 720);
        talkpanel.setBounds(220, 0, 720, 720);
        client.setBounds(1060, 0, 370, 750);

//        组件添加
        this.add(panelright);
        this.add(talkpanel);
        scrollEnemyLookPanel = new ScrollEnemyLookPanel(enemylookpanel, this);
        scrollEnemyLookPanel.setBounds(300, 0, 770, 750);
        this.add(scrollEnemyLookPanel);
        this.add(compass);
        this.add(panelleft);
        this.add(client);
        this.addKeyListener(key);
        this.setVisible(true);//显示窗体
        this.thread.start();
//        开启线程
        if (panelright instanceof Runnable) {
            panelrightThread = new Thread((Runnable) panelright);
            panelrightThread.start();
        }
        if (talkpanel instanceof Runnable) {
            talkpanelThread = new Thread((Runnable) talkpanel);
            talkpanelThread.start();
        }
        if (panelleft instanceof Runnable) {
            panelleftThread = new Thread((Runnable) panelleft);
            panelleftThread.start();
        }
        if (compass instanceof Runnable) {
            compassThread = new Thread((Runnable) compass);
            compassThread.start();
        }
        if (client instanceof Runnable) {
            clientThread = new Thread((Runnable) compass);
            clientThread.start();
        }
        if (scrollEnemyLookPanel instanceof Runnable) {
            scpThread = new Thread((Runnable) scrollEnemyLookPanel);
            scpThread.start();
        }
    }

    public KeyListener getKey() {
        return key;
    }

    public void setKey(KeyListener key) {
        this.key = key;
    }

    public JPanel getPanelleft() {
        return panelleft;
    }

    public void setPanelleft(JPanel panelleft) {
        this.panelleft = panelleft;
    }

    public JPanel getPanelright() {
        return panelright;
    }

    public void setPanelright(JPanel panelright) {
        this.panelright = panelright;
    }

    public void setTalkpanel(TalkPanel talkpanel) {
        this.talkpanel = talkpanel;
    }

    public EnemyLookPanel getEnemylookpanel() {
        return enemylookpanel;
    }

    public void setEnemylookpanel(EnemyLookPanel enemylookpanel) {
        this.enemylookpanel = enemylookpanel;
    }

    public void setCompass(Compass compass) {
        this.compass = compass;
    }

    public Compass getCompass() {
        return compass;
    }
}