package view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import controller.Auto;
import controller.GameKeyListener;
import element.GameElement;
import element.Man;
import element.Manager;
import element.Obj;

public class MyPanelright extends JPanel implements Runnable {
    private Manager em = null;
    public static String message = null;//当吃了一些道具的时候，会告诉面板信息，面板检测有无信息，进行显示
    private int display = 1;//默认显示主界面，2表示显示手册
    public static int location = 740;
    public int Alf = 0;
    public static int AlphaPecent = 0;
    public static boolean issleep = false;

    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon icon1 = new ImageIcon("data/others/结束语1.png");
        ImageIcon icon2 = new ImageIcon("data/others/结束语3.png");
        ImageIcon icon3 = new ImageIcon("data/others/遮罩.png");
        ImageIcon icon4 = new ImageIcon("data/others/op_bg.jpg");
        ImageIcon icon5 = new ImageIcon("data/others/序幕语2.png");
        this.setBackground(new Color(0, 0, 0));
        em = Manager.getManager(Auto.getKey());
        setBounds(320, 0, 720, 720);
        Man man = (Man) Manager.getManager(1).getManList().get(0);
        if (Auto.isBeforing) {//游戏前奏
            AlphaComposite ac = null;
            AlphaPecent += 1;
            if (AlphaPecent >= 100)
                ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            else
                ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) AlphaPecent / 100.0f);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(ac);
            if (AlphaPecent <= 150) {
                g2d.drawImage(icon4.getImage(), 0, 0, icon4.getIconWidth(), icon4.getIconHeight(), this);
            } else {
                location -= 2;
                g.drawImage(icon5.getImage(), 50, location, null);
                g.drawImage(icon3.getImage(), 0, 504, null);
                if (location < -930) {//-930     直接过：1000
                    location = 740;
                    Auto.isBeforing = false;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
        //判断是否通关
        if (man.tallestFloor == 21 && Man.getElement(21, 5, 1) instanceof Obj) {//魔王变成了空地
            setBackground(new Color(0, 0, 0));
            //g.setFont(new Font("楷体",700,90));
            if (location > -200) {//文字移动
                location -= 2;
                System.out.println(location);
                g.drawImage(icon1.getImage(), 70, location, null);
                g.drawImage(icon3.getImage(), 0, 504, null);
            } else {//终
                Alf += 3;
                if (Alf > 255)
                    Alf = 255;
                g.drawImage(icon2.getImage(), 0, 200, new Color(255, 255, 255, Alf), null);
            }
            return;
        }
        List<GameElement> enemyList = em.getEnemyList();
        showList(enemyList, g);
        List<GameElement> objList = em.getObjList();
        showList(objList, g);
        showList(Manager.getManager(1).getManList(), g);
        if (message != null) {
            g.setColor(new Color(50, 50, 50));
            g.fillRect(0, 160, 720, 160);
            g.setColor(new Color(200, 200, 200));
            g.setFont(new Font("黑体", 400, 40));
            g.drawString(message, 360 - message.length() * 20, 250);//360-length*汉字/2
        }
    }

    private void showList(List<GameElement> list, Graphics g) {
        for (int i = list.size() - 1; i >= 0; i--) {
            list.get(i).show(g);
        }
    }

    public void run() {
        System.out.println("主面板开始");
        JPanel that = this;
        that.addKeyListener(new GameKeyListener());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("click");
                that.requestFocus();
            }
        });
        while (true) {
            this.repaint();
            if (message != null) {
                try {
                    Thread.sleep(450);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                message = null;
            }
            try {
                while (issleep) {
                    Thread.sleep(1000);
                }
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
