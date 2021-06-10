package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.ImageIcon;

import element.Enemy;
import element.GameElement;
import element.Man;
import element.Manager;
import element.Obj;
import view.Compass;
import view.EnemyLookPanel;
import view.MyPanelright;
import view.TalkPanel;

public class GameKeyListener implements KeyListener {//监听器

    public void keyPressed(KeyEvent e) {//左37 ，右39    上38   下40
        if (Auto.isBeforing && MyPanelright.AlphaPecent > 150) {//正在前奏
            if (e.getKeyCode() == 32) {
                Auto.isBeforing = false;
                MyPanelright.location = 700;
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (TalkPanel.issleep == false && TalkPanel.windows == 4) {//确定领取怪物手册
            TalkPanel.issleep = true;
            MyPanelright.issleep = false;
        } else if (TalkPanel.issleep == false && TalkPanel.windows == 6) {//确定领取风之罗盘
            TalkPanel.issleep = true;
            MyPanelright.issleep = false;
        } else if (e.getKeyCode() == 76 && Man.haveBook) {//图鉴
            if (EnemyLookPanel.issleep) {//如果图鉴没打开
                MyPanelright.issleep = true;
                EnemyLookPanel.issleep = false;
            } else {//如果图鉴打开了
                EnemyLookPanel.issleep = true;
                MyPanelright.issleep = false;
            }
        } else if (e.getKeyCode() == 74 && Man.haveCompass) {//风之罗盘
            if (Compass.issleep) {//如果罗盘没打开
                System.out.println("风之罗盘！！！！！");
                Compass.loca = 1;
                MyPanelright.issleep = true;
                Compass.issleep = false;
            } else {//如果罗盘打开了
                Compass.issleep = true;
                MyPanelright.issleep = false;
            }
        } else if (Compass.issleep == false) {
            if (e.getKeyCode() == 38) {//光标向上走
                if (Compass.loca > 1)
                    Compass.loca--;
            } else if (e.getKeyCode() == 40) {//光标向下走
                if (Compass.loca < 21)
                    Compass.loca++;
            } else if (e.getKeyCode() == 32) {
                Man man = (Man) Manager.getManager(1).getManList().get(0);
                if (man.tallestFloor <= Compass.loca)
                    return;
                for (int i = 0; i < 11; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (Man.getElement(Compass.loca, i, j) instanceof Obj) {
                            Obj o = (Obj) Man.getElement(Compass.loca, i, j);
                            if (o.type == 16 || o.type == 17) {
                                man.setX(i);
                                man.setY(j);
                            }
                        }
                    }
                }
                Compass.issleep = true;
                MyPanelright.issleep = false;
                Auto.canFly = true;
                //先判断是否去过这层，如果去过
                Auto.setKey(Compass.loca);
            }
        } else if (MyPanelright.issleep == false) {//主窗口，控制人物移动
            Manager.getManager(1).getManList().get(0).clickKey(true, e.getKeyCode());
        } else {//对话窗口移动
            keypress(e);
        }
    }

    private void keypress(KeyEvent e) {
        if (e.getKeyCode() == 38) {//光标向上走
            TalkPanel.loca--;
            if (TalkPanel.loca == 0)
                TalkPanel.loca = 1;
        } else if (e.getKeyCode() == 40) {//光标向下走
            TalkPanel.loca++;
            if (TalkPanel.loca == 5)
                TalkPanel.loca = 4;
        } else if (e.getKeyCode() == 32) {//空格表示确认
            Man man = (Man) Manager.getManager(1).getManList().get(0);
            switch (TalkPanel.loca) {
                case 1://生命或升级
                    if (TalkPanel.windows == 1) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 25);
                            man.setBlood(man.getBlood() + 800);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 100) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 100);
                            man.setBlood(man.getBlood() + 4000);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 2) {
                        if (man.getExe() >= 100) {
                            man.setExe(man.getExe() - 100);
                            man.setLeval(man.getLeval() + 1);
                            man.setBlood(man.getBlood() + 1500);
                            man.setAttack(man.getAttack() + 6);
                            man.setDefense(man.getDefense() + 6);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "经验不足";
                        }
                    } else if (TalkPanel.windows == 200) {
                        if (man.getExe() >= 300) {
                            man.setExe(man.getExe() - 300);
                            man.setLeval(man.getLeval() + 3);
                            man.setBlood(man.getBlood() + 8000);
                            man.setAttack(man.getAttack() + 21);
                            man.setDefense(man.getDefense() + 21);
                        }
                    } else if (TalkPanel.windows == 3) {//购买黄钥匙
                        //System.out.println("想要买黄钥匙");
                        if (man.getMoney() >= 10) {
                            man.setMoney(man.getMoney() - 10);
                            man.setYellowKey(man.getYellowKey() + 1);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "经验不足";
                        }
                    } else if (TalkPanel.windows == 300) {//回收黄钥匙
                        if (man.getYellowKey() >= 1) {
                            man.setYellowKey(man.getYellowKey() - 1);
                            man.setMoney(man.getMoney() + 7);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "黄钥匙不足";
                        }
                    } else if (TalkPanel.windows == 5) {//小偷
                        TalkPanel.windows = 500;
                        System.out.println("小偷跳转到500");
                        break;
                    } else if (TalkPanel.windows == 500) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        break;
                    } else if (TalkPanel.windows == 5000) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        Manager.getManager(4).getEnemyList().remove(Man.getElement(4, 5, 0));
                        Manager.getManager(4).getObjList().add(new Obj(5, 0, new ImageIcon(""), 11));
                        Manager.getManager(4).getThings()[5][0] = 11;
                        break;
                    } else if (TalkPanel.windows == 7) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        if (man.getExe() < 500)
                            break;
                        man.setAttack(man.getAttack() + 120);
                        man.setExe(man.getExe() - 500);
                        MyPanelright.message = "获得星光剑，攻击+120！";
                        Manager.getManager(15).getEnemyList().remove(Man.getElement(15, 4, 3));
                        Manager.getManager(15).getObjList().add(new Obj(4, 3, new ImageIcon(""), 11));
                        Manager.getManager(15).getThings()[4][3] = 11;
                        break;
                    } else if (TalkPanel.windows == 700) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        if (man.getMoney() < 500)
                            break;
                        man.setDefense(man.getDefense() + 120);
                        man.setMoney(man.getMoney() - 500);
                        MyPanelright.message = "获得星光盾，防御+120！";
                        Manager.getManager(15).getEnemyList().remove(Man.getElement(15, 6, 3));
                        Manager.getManager(15).getObjList().add(new Obj(6, 3, new ImageIcon(""), 11));
                        Manager.getManager(15).getThings()[6][3] = 11;
                        break;
                    } else if (TalkPanel.windows == 7000) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        man.setAttack(man.getAttack() + 30);
                        MyPanelright.message = "获得钢剑，攻击+30！";
                        Manager.getManager(2).getEnemyList().remove(Man.getElement(2, 7, 10));
                        Manager.getManager(2).getObjList().add(new Obj(7, 10, new ImageIcon(""), 11));
                        Manager.getManager(2).getThings()[7][10] = 11;
                        break;
                    } else if (TalkPanel.windows == 70000) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        man.setDefense(man.getDefense() + 30);
                        MyPanelright.message = "获得钢盾，防御+30！";
                        Manager.getManager(2).getEnemyList().remove(Man.getElement(2, 9, 10));
                        Manager.getManager(2).getObjList().add(new Obj(9, 10, new ImageIcon(""), 11));
                        Manager.getManager(2).getThings()[9][10] = 11;
                        break;
                    } else if (TalkPanel.windows == 8) {//10,10地形变化
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        if (man.haveHoe != 2)
                            break;
                        Obj o = (Obj) Man.getElement(18, 10, 10);
                        o.type = 16;
                        Manager.getManager(18).getThings()[10][10] = 16;
                        break;
                    } else if (TalkPanel.windows == 9) {//10,10地形变化
                        TalkPanel.windows = 90;
                        break;
                    } else if (TalkPanel.windows == 90) {//10,10地形变化
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        Enemy enemy = null;
                        Obj o = null;
                        try {
                            enemy = (Enemy) Man.getElement(0, 5, 8);
                            o = (Obj) Man.getElement(0, 4, 8);
                        } catch (Exception e1) {
                            System.out.println("");
                        }
                        //enemy.setX(enemy.getX()-1);
                        Manager.getManager(0).getEnemyList().remove(enemy);
                        Manager.getManager(0).getObjList().remove(o);
                        Manager.getManager(0).getObjList().add(new Obj(5, 8, new ImageIcon(""), 11));
                        Manager.getManager(0).getThings()[5][8] = 11;
                        Manager.getManager(0).getObjList().add(new Enemy(4, 8, new ImageIcon(""), 103));
                        Manager.getManager(0).getThings()[5][8] = 11;
                        break;
                    } else if (TalkPanel.windows == 900) {//10,10地形变化
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        Man.haveCross = 2;
                        if (man.tallestFloor == 20) {//已经去过20层的话
                            System.out.println("已经去过20层");
                            Obj o1 = (Obj) Man.getElement(20, 5, 7);
                            o1.type = 16;
                            Manager.getManager(20).getThings()[5][7] = 16;
                        }
                        man.setAttack((int) (man.getAttack() * 1.3));
                        man.setDefense((int) (man.getDefense() * 1.3));
                        man.setBlood((int) (man.getBlood() * 1.3));
                        break;
                    }
                    break;
                case 2://攻击
                    if (TalkPanel.windows == 1) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 25);
                            man.setAttack(man.getAttack() + 4);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 100) {
                        if (man.getMoney() >= 100) {
                            man.setMoney(man.getMoney() - 100);
                            man.setAttack(man.getAttack() + 20);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 2) {
                        if (man.getExe() >= 30) {
                            man.setExe(man.getExe() - 30);
                            man.setAttack(man.getAttack() + 5);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "经验不足";
                        }
                    } else if (TalkPanel.windows == 200) {
                        if (man.getExe() >= 95) {
                            man.setExe(man.getExe() - 95);
                            man.setAttack(man.getAttack() + 17);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "经验不足";
                        }
                    } else if (TalkPanel.windows == 3) {//购买蓝钥匙
                        if (man.getMoney() >= 50) {
                            man.setMoney(man.getMoney() - 50);
                            man.setBlueKey(man.getBlueKey() + 1);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 300) {//回收蓝钥匙
                        if (man.getBlueKey() >= 1) {
                            man.setBlueKey(man.getBlueKey() - 1);
                            man.setMoney(man.getMoney() + 35);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "蓝钥匙不足";
                        }
                    }
                    System.out.println("直接case2break了");
                    break;
                case 3://防御
                    if (TalkPanel.windows == 1) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 25);
                            man.setDefense(man.getDefense() + 4);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 100) {
                        if (man.getMoney() >= 100) {
                            man.setMoney(man.getMoney() - 100);
                            man.setDefense(man.getDefense() + 20);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 2) {
                        if (man.getExe() >= 30) {
                            man.setExe(man.getExe() - 30);
                            man.setDefense(man.getDefense() + 5);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "经验不足";
                        }
                    } else if (TalkPanel.windows == 200) {
                        if (man.getExe() >= 95) {
                            man.setExe(man.getExe() - 95);
                            man.setDefense(man.getDefense() + 17);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "经验不足";
                        }
                    } else if (TalkPanel.windows == 3) {//购买红钥匙
                        if (man.getMoney() >= 100) {
                            man.setMoney(man.getMoney() - 100);
                            man.setRedKey(man.getRedKey() + 1);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "金币不足";
                        }
                    } else if (TalkPanel.windows == 300) {//回收红钥匙
                        if (man.getRedKey() >= 1) {
                            man.setRedKey(man.getRedKey() - 1);
                            man.setMoney(man.getMoney() + 70);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "红钥匙不足";
                        }
                    }
                    break;
                case 4://退出
                    TalkPanel.issleep = true;
                    MyPanelright.issleep = false;
                    break;
                default:
                    System.out.println("代码有误");
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {//松开
        List<GameElement> list = Manager.getManager(Auto.getKey()).getManList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).clickKey(false, e.getKeyCode());
        }
    }
}