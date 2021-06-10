package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import controller.Auto;
import element.Man;
import element.Manager;


//楼层跳跃
public class Compass extends JPanel implements Runnable {
    public static Manager em = null;
    public static boolean issleep = true;
    public static int loca = 1;//光标默认指向第一层

    public void paint(Graphics g) {//遍历当前层的所有怪物的信息，并且显示到页面上
        super.paint(g);
        em = Manager.getManager(Auto.getKey());
        this.setBounds(370, 0, 610, 610);
        //this.setBounds(370, 50, 620, 620);
        this.setBackground(new Color(50, 50, 50));
        g.setFont(new Font("楷体", 700, 70));//90
        g.setColor(new Color(250, 250, 250));
        g.drawString("楼层跳跃", 150, 70);//y100
        g.setFont(new Font("宋体", 400, 33));
        g.drawString("(按空格键确认)", 170, 100);
        g.setFont(new Font("宋体", 400, 33));
        Man man = (Man) Manager.getManager(1).getManList().get(0);
        int stairnow = man.tallestFloor;
        if (stairnow == 1 || stairnow == 0) {
            g.drawString("第1层", 103, 115 + 65);
        } else if (stairnow < 8 && stairnow >= 2) {
            for (int j = 1; j < stairnow; j++) {
                g.drawString("第" + j + "层", 103, 115 + j * 65);
            }
        } else if (stairnow < 15 && stairnow >= 8) {
            for (int j = 1; j < 8; j++) {
                g.drawString("第" + j + "层", 103, 115 + j * 65);
            }
            for (int j = 1; j < stairnow - 7; j++) {
                g.drawString("第" + (7 + j) + "层", 103 + 207 - 15 * ("第" + (7 + j) + "层").length(), 115 + j * 65);
            }
        } else {
            for (int j = 1; j < 8; j++) {
                g.drawString("第" + j + "层", 103, 115 + j * 65);
            }
            for (int j = 1; j < 8; j++) {
                g.drawString("第" + (7 + j) + "层", 103 + 207 - 15 * ("第" + (7 + j) + "层").length(), 115 + j * 65);
            }
            for (int j = 1; j < stairnow - 14; j++) {
                g.drawString("第" + (14 + j) + "层", 103 + 2 * 207 - 15 * ("第" + (14 + j) + "层").length(), 115 + j * 65);
            }
        }
        int row = loca % 7;//   14--7,2
        int column = loca / 7;
        if (row == 0) {
            row = 7;
            column = 0;
        }
        if (loca == 14)
            column = 1;
        if (loca == 21)
            column = 2;
        g.drawRect(column * 203 + 20, (row - 1) * 65 + 143, 160, 50);
    }

    public void run() {
        while (true) {
            this.repaint();
            try {
                while (issleep || !Man.haveCompass) {
                    Thread.sleep(200);
                }
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
