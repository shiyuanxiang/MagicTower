package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Auto;
import element.Man;
import element.Manager;

public class MyPanelleft extends JPanel implements Runnable {
    private Manager em = null;
    public static boolean issleep = true;
    String s = null;

    public MyPanelleft() {
    }

    public synchronized static void copy(String s, File file) {
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(s.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        em = Manager.getManager(1);
        //tj.setBounds(20, 650, 200, 100);
        this.setBackground(new Color(100, 100, 100));
        ImageIcon icon = new ImageIcon("data/man/2.jpg");
        g.drawImage(icon.getImage(), 20, 20, 36, 38, null);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.setColor(new Color(255, 255, 255));
        if (em.getManList().size() > 0) {
            Man m = (Man) em.getManList().get(0);
            g.drawString(new Integer(m.getLeval()).toString(), 80, 50);
            g.setFont(new Font("宋体", Font.BOLD, 20));//字号设置为100
            g.drawString(new Integer(m.getBlood()).toString(), 70, 90);
            g.drawString(new Integer(m.getAttack()).toString(), 70, 120);
            g.drawString(new Integer(m.getDefense()).toString(), 70, 150);
            g.drawString(new Integer(m.getMoney()).toString(), 70, 180);
            g.drawString(new Integer(m.getExe()).toString(), 70, 210);
            g.setFont(new Font("宋体", Font.BOLD, 20));
            g.drawString(new Integer(m.getYellowKey()).toString(), 70, 240);
            g.drawString(new Integer(m.getBlueKey()).toString(), 70, 290);
            g.drawString(new Integer(m.getRedKey()).toString(), 70, 340);
        }
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("级", 100, 50);
        g.drawString("生命", 20, 90);
        g.drawString("攻击", 20, 120);
        g.drawString("防御", 20, 150);
        g.drawString("金币", 20, 180);
        g.drawString("经验", 20, 210);

        icon = new ImageIcon("data/obj/8.jpg");
        g.drawImage(icon.getImage(), 20, 220, 36, 38, null);
        icon = new ImageIcon("data/obj/9.jpg");
        g.drawImage(icon.getImage(), 20, 270, 36, 38, null);
        icon = new ImageIcon("data/obj/10.jpg");
        g.drawImage(icon.getImage(), 20, 320, 36, 38, null);
        g.drawString("神秘道具", 90, 380);
        icon = new ImageIcon("data/obj/35.jpg");
        g.drawImage(icon.getImage(), 20, 390, 36, 38, null);
        g.drawImage(icon.getImage(), 20, 440, 36, 38, null);
        icon = new ImageIcon("data/obj/18.jpg");
        if (Man.haveBook) {
            g.drawImage(icon.getImage(), 20, 390, 36, 38, null);
            g.drawString("按L键查看这层怪物信息", 70, 410);
            g.drawString("再按L键可关闭", 70, 430);
        }
        icon = new ImageIcon("data/obj/29.jpg");
        if (Man.haveCompass) {
            g.drawImage(icon.getImage(), 20, 440, 36, 38, null);
            g.drawString("按J键楼层跳跃", 70, 460);
            g.drawString("再按J键可关闭", 70, 480);
        }
        g.drawString("个", 120, 240);
        g.drawString("个", 120, 290);
        g.drawString("个", 120, 340);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("第 " + Auto.getKey() + " 层", 160, 50);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}
