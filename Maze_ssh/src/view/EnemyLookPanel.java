package view;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import controller.Auto;
import element.Enemy;
import element.GameElement;
import element.Man;
import element.Manager;
import element.MazeGameLoad;

public class EnemyLookPanel extends JPanel {
    public static Manager em = null;
    public static boolean issleep = true;

    public void paint(Graphics g) {//遍历当前层的所有怪物的信息，并且显示到页面上
        super.paint(g);
        em = Manager.getManager(Auto.getKey());
        this.setBackground(new Color(50, 50, 50, 255));
        List<GameElement> enemyList = em.getEnemyList();//这一关的怪物集合
        showbookList(enemyList, g);
    }

    private void showbookList(List<GameElement> enemyList, Graphics g) {
        int num = 0;
        Man man = (Man) Manager.getManager(1).getManList().get(0);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < enemyList.size(); i++) {
            Enemy e = (Enemy) enemyList.get(i);//将怪物的每一个元素强转换成为enemy
            if (e.getType() > 120) {//先把所有敌人放到map里面，最后再统一输出集合元素的信息
                set.add(e.getType());
            }
        }
        if (set.isEmpty()) {
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("宋体", Font.BOLD, 50));
            g.drawString("本层无怪物", 200, 400);
            return;
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Enemy e = MazeGameLoad.getEnemyByType(iterator.next());
            Image image = null;
            try {
                image = e.getIcon().getImage();//测试用
                g.drawImage(e.getIcon().getImage(), 10, num * 78 + 10 * (num + 1), 80, 80, null);
            } catch (Exception e1) {
                System.out.println(e.getType() + "   " + image);
            }//image为null
            //g.drawStrin
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100

            g.drawString("名称           攻击      金/经", 100, num * 78 + 10 * (num + 1) + 25);
            g.drawString("生命           防御       损失", 100, num * 78 + 10 * (num + 1) + 65);
            g.setFont(new Font("楷体", Font.BOLD, 25));//字号设置为100
            g.drawString(e.getName(), 160, num * 78 + 10 * (num + 1) + 25);
            g.drawString(e.getAttack() + "", 370, num * 78 + 10 * (num + 1) + 25);
            g.drawString(e.getMoney() + "/" + e.getExe(), 530, num * 78 + 10 * (num + 1) + 25);
            g.drawString(e.getBlood() + "", 160, num * 78 + 10 * (num + 1) + 65);
            g.drawString(e.getBlood() + "", 160, num * 78 + 10 * (num + 1) + 65);
            g.drawString(e.getDefense() + "", 370, num * 78 + 10 * (num + 1) + 65);
            String result = man.KO(e) + "";
            if (man.KO(e) == -1)
                result = "无法攻击";
            g.drawString(result, 530, num * 78 + 10 * (num + 1) + 65);
            num++;
        }
    }
}