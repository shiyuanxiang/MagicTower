package view;

import element.Enemy;
import element.Man;
import session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Battle extends JDialog {
    private Man man;
    private Enemy enemy;
    private Battle battle;
    private static int man_photo_cnt = 0;

    public Battle(Man man, Enemy enemy, boolean isWin, int dec_blood, int add_money, int add_exe) {
        this.setModal(true);
        this.enemy = enemy;
        this.man = man;
        this.battle = this;
        int w = Session.screen_width;
        int h = Session.screen_height;
        // 组件定义
        JLabel title = new JLabel("战斗吧!");
        JPanel battle_background = new JPanel();
        JLabel battle_lable = new JLabel();
        ImageIcon battle_photo = new ImageIcon("data/battle/battling.gif");
        Image battle_image;
        JLabel man_label = new JLabel();
        JLabel man_attack = new JLabel("攻:" + man.getAttack());
        JLabel man_defense = new JLabel("防:" + man.getDefense());
        JLabel man_blood = new JLabel("血:" + man.getBlood());
        ImageIcon man_photo = new ImageIcon("data/man/" + Battle.man_photo_cnt + ".jpg");
        JLabel enemy_label = new JLabel();
        ImageIcon enemy_photo = new ImageIcon("data/change/" + enemy.getType() + ".jpg");
        JLabel enemy_attack = new JLabel("攻:" + enemy.getAttack());
        JLabel enemy_defense = new JLabel("防:" + enemy.getDefense());
        JLabel enemy_blood = new JLabel("血量:" + enemy.getBlood());

        //组件配置
        battle.setBounds(w / 2, h / 2, 300, 150);
        battle.setUndecorated(true);
        title.setFont(new Font("宋体", Font.BOLD, 20));
        title.setBounds(w / 2 + 100, h / 2, 100, 20);
        title.setForeground(Color.RED);
        man_label.setSize(32, 32);
        man_label.setIcon(new ImageIcon(man_photo.getImage().getScaledInstance(man_label.getWidth(), man_label.getHeight(), man_photo.getImage().SCALE_DEFAULT)));
        man_label.setBounds(10, 25, 32, 32);
        man_attack.setBounds(45, 28, 80, 20);
        man_defense.setBounds(45, 60, 80, 20);
        man_blood.setBounds(10, 100, 100, 20);
        man_blood.setFont(new Font("宋体", Font.BOLD, 18));
        man_blood.setForeground(Color.green);
        //战斗场景
        battle_lable.setBounds(100, 30, 100, 70);
        battle_image = battle_photo.getImage().getScaledInstance(battle_lable.getWidth(), battle_lable.getHeight(), battle_photo.getImage().SCALE_DEFAULT);
        battle_photo = new ImageIcon(battle_image);
        battle_lable.setIcon(battle_photo);
        //怪物
        enemy_label.setSize(32, 32);
        enemy_label.setIcon(new ImageIcon(enemy_photo.getImage().getScaledInstance(enemy_label.getWidth(), enemy_label.getHeight(), enemy_photo.getImage().SCALE_DEFAULT)));
        enemy_label.setBounds(163 + 50, 25, 32, 32);
        enemy_attack.setBounds(163 + 85, 28, 80, 20);
        enemy_defense.setBounds(163 + 85, 60, 80, 20);
        enemy_blood.setBounds(163 + 50, 100, 100, 20);
        enemy_blood.setFont(new Font("宋体", Font.BOLD, 18));
        enemy_blood.setForeground(Color.green);
        //战斗面板
        battle_background.setLayout(null);
        battle_background.setBackground(Color.gray);
        battle_background.add(title);
        battle_background.add(man_label);
        battle_background.add(man_attack);
        battle_background.add(man_defense);
        battle_background.add(man_blood);
        battle_background.add(battle_lable);
        battle_background.add(enemy_label);
        battle_background.add(enemy_attack);
        battle_background.add(enemy_defense);
        battle_background.add(enemy_blood);
        //动画
        Timer change = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Battle.man_photo_cnt++;
                Battle.man_photo_cnt %= 4;
                ImageIcon man_photo = new ImageIcon("data/man/" + Battle.man_photo_cnt + ".jpg");
                man_label.setIcon(new ImageIcon(man_photo.getImage().getScaledInstance(man_label.getWidth(), man_label.getHeight(), man_photo.getImage().SCALE_DEFAULT)));

                ImageIcon enemy_photo_change;
                if ((Battle.man_photo_cnt % 2) == 0) {
                    enemy_photo_change = new ImageIcon("data/change/" + enemy.getType() + ".jpg");
                } else {
                    enemy_photo_change = new ImageIcon("data/change/" + (enemy.getType() + 1) + ".jpg");
                }
                enemy_label.setIcon(new ImageIcon(enemy_photo_change.getImage().getScaledInstance(enemy_label.getWidth(), enemy_label.getHeight(), enemy_photo_change.getImage().SCALE_DEFAULT)));

                repaint();
            }
        });
        change.start();
        //定时关闭
        Timer close = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("close");
                battle.setVisible(false);
                battle.dispose();
                change.setRepeats(false);
                MyPanelright.message = "击败了" + enemy.getName() + " 金币+" + add_money + " 经验+" + add_exe;
            }
        });
        close.start();
        close.setRepeats(false);

        //组件添加
        battle.add(battle_background);
        battle.setVisible(true);
    }
}
