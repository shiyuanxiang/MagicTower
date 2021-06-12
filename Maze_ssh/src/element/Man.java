package element;

import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.*;

import controller.Auto;
import controller.Music;
import view.*;

//要实现的功能--
//攻击野怪要提示：击杀xx.name,金币+x,经验+y，要是打不过提示无法攻击
//商人的交互界面
//查看敌人的属性:增加一个panel
public class Man extends GameElement {
    private int type = 2;//默认朝下走路
    private int leval = 1;
    private int blood = 1000;
    private int attack = 10;
    private int defense = 10;
    private int money = 0;
    private int exe = 0;
    private int yellowKey = 1;
    private int blueKey = 1;
    private int redKey = 1;
    private int objx = getX();//存储目标位置
    private int objy = getY();
    public int tallestFloor = 0;//最高去过哪层
    private boolean cl = false;
    public static boolean haveBook = false;//怪物手册
    public static boolean haveCompass = false;//风之罗盘
    public static int haveHoe = 0;//铁镐
    public static int haveCross = 0;//十字架
    private static ImageIcon[] icon = new ImageIcon[4];

    public Man(int X, int Y, ImageIcon imag) {
        super(X, Y, GameElement.getDefault_size(), GameElement.getDefault_size(), imag);
    }

    public int KO(Enemy e) {//不能打败返回-1，能击败返回要损失的血量
        int b = 0;//要损失的血量
        int myb = blood;
        int enb = e.getBlood();
        if (attack <= e.getDefense())
            return -1;
        while (enb > 0) {
            enb -= Math.max(attack - e.getDefense(), 0);
            if (enb <= 0)
                break;
            myb -= Math.max(e.getAttack() - defense, 0);
            b += Math.max(e.getAttack() - defense, 0);
        }
        if (e.getType() == 157) {//白衣武士
            b = Math.max(b, Math.max(blood / 4, 300));
        }
        if (e.getType() == 173) {//灵法师
            b = Math.max(b, Math.max(blood / 3, 800));
        }
        if (e.getType() == 171) {//灵武士
            b = Math.max(b, Math.max(blood / 7, 500));
        }
        if (e.getType() == 179) {//影子战士
            b = Math.max(b, 1000);
        }
        return b;
    }

    public void clickKey(boolean b, int keyCode) {//左37 ，右39    上38   下40
        cl = b;
        if (b == false) {//如果沒有按下键盘，直接结束
            return;
        }
        if (ifNoWall(keyCode)) {//如果可以移动
            objx = getX();//存储目标位置
            objy = getY();
            switch (keyCode) {//先修正目标位置,可能有道具，或者门，或者怪物
                case 37:
                    type = 1;
                    objx--;
                    break;
                case 39:
                    type = 3;
                    objx++;
                    break;
                case 38:
                    type = 0;
                    objy--;
                    break;
                case 40:
                    type = 2;
                    objy++;
                    break;
            }
            GameElement e = getElement(objx, objy);
            if (e instanceof Obj) {
                Obj o = (Obj) e;
                if (o.type < 11 || o.type == 13 || o.type == 18 || (o.type >= 20 && o.type <= 34 && o.type != 25 && o.type != 26)) {//是普通道具
                    //System.out.println("吃！");
                    if (!eatit(o)) {
                        return;
                    }
                }
                if (o.type == 16) {//上楼
                    if (Auto.getKey() < 21)
                        Auto.setCanGostairs(1);
                }
                if (o.type == 17) {//下楼
                    if (Auto.getKey() > 0)
                        Auto.setCanGostairs(-1);
                }
            }
            if (e instanceof Enemy) {
                Enemy en = (Enemy) e;
                System.out.println("碰到怪物" + en.getBlood());
                if (en.getType() == 111 && Auto.getKey() == 3) {//垃圾金钱商人
                    TalkPanel.windows = 1;
                    TalkPanel.issleep = false;//启用窗口
                    TalkPanel.loca = 1;//选择框默认在第一行
                    MyPanelright.issleep = true;
                    System.out.println("金钱商人");
                    return;
                } else if (en.getType() == 111 && Auto.getKey() == 11) {//高级金钱商人
                    TalkPanel.windows = 100;
                    TalkPanel.issleep = false;//启用窗口
                    TalkPanel.loca = 1;//选择框默认在第一行
                    MyPanelright.issleep = true;
                    //System.out.println("金钱商人");
                    return;
                }
                if (en.getType() == 103) {//仙子
                    if (haveCross == 0) {
                        TalkPanel.windows = 9;
                        TalkPanel.issleep = false;
                        TalkPanel.loca = 1;
                        MyPanelright.issleep = true;
                        return;
                    } else {
                        TalkPanel.windows = 900;
                        TalkPanel.issleep = false;
                        TalkPanel.loca = 1;
                        MyPanelright.issleep = true;
                        return;
                    }
                }
                if (en.getType() == 107) {//公主
                    TalkPanel.windows = 8;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                }
                if (en.getType() == 117 && Auto.getKey() == 5) {//垃圾经验商人
                    TalkPanel.windows = 2;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("经验商人");
                    return;
                } else if (en.getType() == 117 && Auto.getKey() == 13) {//高级经验商人
                    TalkPanel.windows = 200;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("经验商人");
                    return;
                } else if (en.getType() == 117 && Auto.getKey() == 2) {//刚剑商人
                    TalkPanel.windows = 7000;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("经验商人");
                    return;
                } else if (en.getType() == 117 && Auto.getKey() == 15) {//星光剑商人
                    TalkPanel.windows = 7;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("经验商人");
                    return;
                }
                if (en.getType() == 113) {//小偷
                    if (haveHoe == 0) {//没有铁镐
                        TalkPanel.windows = 5;
                        TalkPanel.loca = 1;
                        TalkPanel.issleep = false;
                        MyPanelright.issleep = true;
                        Obj obj = (Obj) getElement(2, 1, 6);
                        Manager.getManager(2).getThings()[1][6] = 11;
                        obj.type = 11;
                        return;
                    } else if (haveHoe == 1) {//有铁镐了
                        haveHoe = 2;
                        //en.setType(11);
                        TalkPanel.windows = 5000;
                        TalkPanel.issleep = false;
                        MyPanelright.issleep = true;
                        return;
                    }
                }
                if (en.getType() == 115 && Auto.getKey() == 5) {//老太婆
                    TalkPanel.windows = 3;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                } else if (en.getType() == 115 && Auto.getKey() == 12) {//老太婆
                    TalkPanel.windows = 300;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                } else if (en.getType() == 115 && Auto.getKey() == 2) {//老太婆
                    TalkPanel.windows = 70000;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                } else if (en.getType() == 115 && Auto.getKey() == 15) {//老太婆
                    TalkPanel.windows = 700;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                }
                //开启战斗画面
                if (KO(en) == -1 || blood <= KO(en)) {
//                    Battle battle = new Battle(this, en, false, 0, 0, 0);
                    MyPanelright.message = "敌人太强了！";
                    return;
                }
                blood -= KO(en);
                money += en.getMoney();
                exe += en.getExe();
                Battle battle = new Battle(this, en, true, KO(en), en.getMoney(), en.getExe());
                Manager.getManager(Auto.getKey()).getEnemyList().remove(e);
                ImageIcon icon = new ImageIcon("data/obj/11.jpg");
                Manager.getManager(Auto.getKey()).getObjList().add(new Obj(objx, objy, icon, 11));
                Manager.getManager(Auto.getKey()).getThings()[objx][objy] = 11;
                Music.play("data/audio/攻击.wav");
            }
            setX(objx);
            setY(objy);
        } else {
            System.out.println("被挡住");
        }
    }

    //能吃就true，不能就false
    private boolean eatit(Obj o) {
        boolean caneat = true;
        switch (o.type) {
            case 1:
                setAttack(getAttack() + 3);
                MyPanelright.message = "获得红宝石 攻击+3";
                break;
            case 2:
                setDefense(getDefense() + 3);
                MyPanelright.message = "获得蓝宝石 防御+3";
                break;
            case 3:
                setBlood(getBlood() + 200);
                MyPanelright.message = "获得红血瓶 生命+200";
                break;
            case 4:
                setBlood(getBlood() + 500);
                MyPanelright.message = "获得蓝血瓶 生命+500";
                break;
            case 5:
            case 6://三种门
            case 7:
                if (canOpenDoor(objx, objy) == false) {
                    System.out.println(objx + "," + objy + "过不去");
                    caneat = false;
                }
                break;
            case 8:
                setYellowKey(getYellowKey() + 1);
                MyPanelright.message = "黄钥匙+1";
                break;
            case 9:
                setBlueKey(getBlueKey() + 1);
                MyPanelright.message = "蓝钥匙+1";

                break;
            case 10:
                setRedKey(getRedKey() + 1);
                MyPanelright.message = "红钥匙+1";

                break;
            case 13://铁剑
                setAttack(getAttack() + 10);
                MyPanelright.message = "获得铁剑 攻击+10";
                break;
            case 18://怪物手冊
                haveBook = true;
                TalkPanel.windows = 4;
                TalkPanel.issleep = false;
                MyPanelright.issleep = true;
                break;
            case 20://铁门
                if (Auto.getKey() == 7) {
                    if (getX() == 5 || getX() == 7)
                        caneat = false;
                } else if (Auto.getKey() == 13) {
                    if (getX() == 3 || getX() == 4)
                        caneat = false;
                } else if (Auto.getKey() == 21) {
                    caneat = false;
                }
                break;
            case 21://仨钥匙
                yellowKey++;
                blueKey++;
                redKey++;
                MyPanelright.message = "所有钥匙数量+1";
                break;
            case 22://盾牌
                setDefense(getDefense() + 10);
                MyPanelright.message = "获得铁盾，防御+10";
                break;
            case 23://升级
                if (Auto.getKey() != 13) {
                    setLeval(getLeval() + 1);
                    setBlood(getBlood() + 1500);
                    setAttack(getAttack() + 6);
                    setDefense(getDefense() + 6);
                    MyPanelright.message = "获得祝福 升级！";
                    break;
                } else {
                    setLeval(getLeval() + 3);
                    setBlood(getBlood() + 8000);
                    setAttack(getAttack() + 21);
                    setDefense(getDefense() + 21);
                    MyPanelright.message = "获得祝福 升了三级！";
                    break;
                }
            case 24://十字架
                haveCross = 1;
                //Obj o1 = (Obj)Man.getElement(20, 5, 7);
                //o1.type=16;
                //Manager.getManager(20).getThings()[5][7]=16;
                MyPanelright.message = "获得十字架！ 快去0层找仙女吧！";
                break;
            case 27://大金币
                money += 300;
                MyPanelright.message = "金币+300！";
                break;
            case 28://银剑
                attack += 80;
                MyPanelright.message = "获得银剑，攻击+80";
                break;
            case 29://风之罗盘
                haveCompass = true;
                TalkPanel.windows = 6;
                TalkPanel.issleep = false;
                MyPanelright.issleep = true;
                break;
            case 30://黄金盾
                defense += 90;
                MyPanelright.message = "获得银盾，防御+90";
                break;
            case 31://铁镐
                haveHoe = 1;
                MyPanelright.message = "获得铁镐！快去4层抓小偷吧！";
                break;
            case 32://圣水
                blood += blood;
                MyPanelright.message = "获得圣水，生命值翻倍！";
                break;
            case 33://大宝剑
                attack += 160;
                MyPanelright.message = "获得圣剑，攻击+160";
                break;
            case 34://大盾牌
                defense += 200;
                MyPanelright.message = "获得圣盾，防御+200";
                break;
        }
        if (caneat) {
            o.type = 11;
            Music.play("data/audio/吃道具.wav");
        }
        return caneat;
    }

    public boolean canOpenDoor(int x, int y) {//对x,y的门响应
        if (Manager.getManager(Auto.getKey()).getThings()[x][y] == 5) {//黄门
            if (getYellowKey() == 0)
                return false;
            setYellowKey(getYellowKey() - 1);
            return true;
        }
        if (Manager.getManager(Auto.getKey()).getThings()[x][y] == 6) {//蓝门
            if (getBlueKey() == 0)
                return false;
            setBlueKey(getBlueKey() - 1);
            return true;
        }
        if (Manager.getManager(Auto.getKey()).getThings()[x][y] == 7) {//红门
            if (getRedKey() == 0)
                return false;
            setRedKey(getRedKey() - 1);
            return true;
        }
        return false;
    }

    public static GameElement getElement(int x, int y) {//返回x,y上的对象
        List<GameElement> objList = Manager.getManager(Auto.getKey()).getObjList();
        List<GameElement> enemyList = Manager.getManager(Auto.getKey()).getEnemyList();
        for (GameElement e : objList) {
            if (e.getX() == x && e.getY() == y)
                return e;
        }
        for (GameElement e : enemyList) {
            if (e.getX() == x && e.getY() == y)
                return e;
        }
        return null;
    }

    public static GameElement getElement(int key, int x, int y) {//返回x,y上的对象
        List<GameElement> objList = Manager.getManager(key).getObjList();
        List<GameElement> enemyList = Manager.getManager(key).getEnemyList();
        for (GameElement e : objList) {
            if (e.getX() == x && e.getY() == y)
                return e;
        }
        for (GameElement e : enemyList) {
            if (e.getX() == x && e.getY() == y)
                return e;
        }
        return null;
    }

    private boolean ifNoWall(int keyCode) {
        int mx = this.getX();
        int my = getY();
        int typ = 0;
        switch (keyCode) {
            case 37:
                if (mx == 0)
                    return false;
                typ = Manager.getManager(Auto.getKey()).getThings()[mx - 1][my];
                if (typ == 12 || typ == 19 || typ == 109) {//如果在最左边或者左边有墙
                    return false;
                }
                return true;
            case 38:
                if (my == 0)
                    return false;
                typ = Manager.getManager(Auto.getKey()).getThings()[mx][my - 1];
                if (typ == 12 || typ == 19 || typ == 25 || typ == 26 || typ == 109) {//如果在最上边或者上边有墙
                    return false;
                }
                return true;
            case 39:
                if (mx == 10)
                    return false;
                typ = Manager.getManager(Auto.getKey()).getThings()[mx + 1][my];
                if (typ == 12 || typ == 19 || typ == 109) {//如果在最右边或者右边有墙
                    return false;
                }
                return true;
            case 40:
                if (my == 10)
                    return false;
                typ = Manager.getManager(Auto.getKey()).getThings()[mx][my + 1];
                if (typ == 12 || typ == 19 || typ == 109) {//如果在最下边或者下边有墙
                    return false;
                }
                return true;
        }
        return false;
    }

    public static Man createMan(int X, int Y) {
        for (int i = 0; i < 4; i++) {
            String url = "data/man/" + i + ".jpg";
            icon[i] = new ImageIcon(url);
        }
        return new Man(X, Y, icon[2]);
    }

    public void show(Graphics g) {
        super.show(g);
        changeType();
        g.drawImage(this.getIcon().getImage(), getX() * GameElement.getDefault_size(), getY() * GameElement.getDefault_size(), getWidth(), getHeight(), null);
    }

    public void changeType() {
        this.setIcon(icon[type]);
    }

    public int getLeval() {
        return leval;
    }

    public void setLeval(int leval) {
        this.leval = leval;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExe() {
        return exe;
    }

    public void setExe(int exe) {
        this.exe = exe;
    }

    public int getYellowKey() {
        return yellowKey;
    }

    public void setYellowKey(int yellowKey) {
        this.yellowKey = yellowKey;
    }

    public int getBlueKey() {
        return blueKey;
    }

    public void setBlueKey(int blueKey) {
        this.blueKey = blueKey;
    }

    public int getRedKey() {
        return redKey;
    }

    public void setRedKey(int redKey) {
        this.redKey = redKey;
    }
}
