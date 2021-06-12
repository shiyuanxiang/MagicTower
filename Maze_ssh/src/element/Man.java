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

//Ҫʵ�ֵĹ���--
//����Ұ��Ҫ��ʾ����ɱxx.name,���+x,����+y��Ҫ�Ǵ򲻹���ʾ�޷�����
//���˵Ľ�������
//�鿴���˵�����:����һ��panel
public class Man extends GameElement {
    private int type = 2;//Ĭ�ϳ�����·
    private int leval = 1;
    private int blood = 1000;
    private int attack = 10;
    private int defense = 10;
    private int money = 0;
    private int exe = 0;
    private int yellowKey = 1;
    private int blueKey = 1;
    private int redKey = 1;
    private int objx = getX();//�洢Ŀ��λ��
    private int objy = getY();
    public int tallestFloor = 0;//���ȥ���Ĳ�
    private boolean cl = false;
    public static boolean haveBook = false;//�����ֲ�
    public static boolean haveCompass = false;//��֮����
    public static int haveHoe = 0;//����
    public static int haveCross = 0;//ʮ�ּ�
    private static ImageIcon[] icon = new ImageIcon[4];

    public Man(int X, int Y, ImageIcon imag) {
        super(X, Y, GameElement.getDefault_size(), GameElement.getDefault_size(), imag);
    }

    public int KO(Enemy e) {//���ܴ�ܷ���-1���ܻ��ܷ���Ҫ��ʧ��Ѫ��
        int b = 0;//Ҫ��ʧ��Ѫ��
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
        if (e.getType() == 157) {//������ʿ
            b = Math.max(b, Math.max(blood / 4, 300));
        }
        if (e.getType() == 173) {//�鷨ʦ
            b = Math.max(b, Math.max(blood / 3, 800));
        }
        if (e.getType() == 171) {//����ʿ
            b = Math.max(b, Math.max(blood / 7, 500));
        }
        if (e.getType() == 179) {//Ӱ��սʿ
            b = Math.max(b, 1000);
        }
        return b;
    }

    public void clickKey(boolean b, int keyCode) {//��37 ����39    ��38   ��40
        cl = b;
        if (b == false) {//����]�а��¼��̣�ֱ�ӽ���
            return;
        }
        if (ifNoWall(keyCode)) {//��������ƶ�
            objx = getX();//�洢Ŀ��λ��
            objy = getY();
            switch (keyCode) {//������Ŀ��λ��,�����е��ߣ������ţ����߹���
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
                if (o.type < 11 || o.type == 13 || o.type == 18 || (o.type >= 20 && o.type <= 34 && o.type != 25 && o.type != 26)) {//����ͨ����
                    //System.out.println("�ԣ�");
                    if (!eatit(o)) {
                        return;
                    }
                }
                if (o.type == 16) {//��¥
                    if (Auto.getKey() < 21)
                        Auto.setCanGostairs(1);
                }
                if (o.type == 17) {//��¥
                    if (Auto.getKey() > 0)
                        Auto.setCanGostairs(-1);
                }
            }
            if (e instanceof Enemy) {
                Enemy en = (Enemy) e;
                System.out.println("��������" + en.getBlood());
                if (en.getType() == 111 && Auto.getKey() == 3) {//������Ǯ����
                    TalkPanel.windows = 1;
                    TalkPanel.issleep = false;//���ô���
                    TalkPanel.loca = 1;//ѡ���Ĭ���ڵ�һ��
                    MyPanelright.issleep = true;
                    System.out.println("��Ǯ����");
                    return;
                } else if (en.getType() == 111 && Auto.getKey() == 11) {//�߼���Ǯ����
                    TalkPanel.windows = 100;
                    TalkPanel.issleep = false;//���ô���
                    TalkPanel.loca = 1;//ѡ���Ĭ���ڵ�һ��
                    MyPanelright.issleep = true;
                    //System.out.println("��Ǯ����");
                    return;
                }
                if (en.getType() == 103) {//����
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
                if (en.getType() == 107) {//����
                    TalkPanel.windows = 8;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                }
                if (en.getType() == 117 && Auto.getKey() == 5) {//������������
                    TalkPanel.windows = 2;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("��������");
                    return;
                } else if (en.getType() == 117 && Auto.getKey() == 13) {//�߼���������
                    TalkPanel.windows = 200;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("��������");
                    return;
                } else if (en.getType() == 117 && Auto.getKey() == 2) {//�ս�����
                    TalkPanel.windows = 7000;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("��������");
                    return;
                } else if (en.getType() == 117 && Auto.getKey() == 15) {//�ǹ⽣����
                    TalkPanel.windows = 7;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    //System.out.println("��������");
                    return;
                }
                if (en.getType() == 113) {//С͵
                    if (haveHoe == 0) {//û������
                        TalkPanel.windows = 5;
                        TalkPanel.loca = 1;
                        TalkPanel.issleep = false;
                        MyPanelright.issleep = true;
                        Obj obj = (Obj) getElement(2, 1, 6);
                        Manager.getManager(2).getThings()[1][6] = 11;
                        obj.type = 11;
                        return;
                    } else if (haveHoe == 1) {//��������
                        haveHoe = 2;
                        //en.setType(11);
                        TalkPanel.windows = 5000;
                        TalkPanel.issleep = false;
                        MyPanelright.issleep = true;
                        return;
                    }
                }
                if (en.getType() == 115 && Auto.getKey() == 5) {//��̫��
                    TalkPanel.windows = 3;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                } else if (en.getType() == 115 && Auto.getKey() == 12) {//��̫��
                    TalkPanel.windows = 300;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                } else if (en.getType() == 115 && Auto.getKey() == 2) {//��̫��
                    TalkPanel.windows = 70000;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                } else if (en.getType() == 115 && Auto.getKey() == 15) {//��̫��
                    TalkPanel.windows = 700;
                    TalkPanel.issleep = false;
                    TalkPanel.loca = 1;
                    MyPanelright.issleep = true;
                    return;
                }
                //����ս������
                if (KO(en) == -1 || blood <= KO(en)) {
//                    Battle battle = new Battle(this, en, false, 0, 0, 0);
                    MyPanelright.message = "����̫ǿ�ˣ�";
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
                Music.play("data/audio/����.wav");
            }
            setX(objx);
            setY(objy);
        } else {
            System.out.println("����ס");
        }
    }

    //�ܳԾ�true�����ܾ�false
    private boolean eatit(Obj o) {
        boolean caneat = true;
        switch (o.type) {
            case 1:
                setAttack(getAttack() + 3);
                MyPanelright.message = "��ú챦ʯ ����+3";
                break;
            case 2:
                setDefense(getDefense() + 3);
                MyPanelright.message = "�������ʯ ����+3";
                break;
            case 3:
                setBlood(getBlood() + 200);
                MyPanelright.message = "��ú�Ѫƿ ����+200";
                break;
            case 4:
                setBlood(getBlood() + 500);
                MyPanelright.message = "�����Ѫƿ ����+500";
                break;
            case 5:
            case 6://������
            case 7:
                if (canOpenDoor(objx, objy) == false) {
                    System.out.println(objx + "," + objy + "����ȥ");
                    caneat = false;
                }
                break;
            case 8:
                setYellowKey(getYellowKey() + 1);
                MyPanelright.message = "��Կ��+1";
                break;
            case 9:
                setBlueKey(getBlueKey() + 1);
                MyPanelright.message = "��Կ��+1";

                break;
            case 10:
                setRedKey(getRedKey() + 1);
                MyPanelright.message = "��Կ��+1";

                break;
            case 13://����
                setAttack(getAttack() + 10);
                MyPanelright.message = "������� ����+10";
                break;
            case 18://�����փ�
                haveBook = true;
                TalkPanel.windows = 4;
                TalkPanel.issleep = false;
                MyPanelright.issleep = true;
                break;
            case 20://����
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
            case 21://��Կ��
                yellowKey++;
                blueKey++;
                redKey++;
                MyPanelright.message = "����Կ������+1";
                break;
            case 22://����
                setDefense(getDefense() + 10);
                MyPanelright.message = "������ܣ�����+10";
                break;
            case 23://����
                if (Auto.getKey() != 13) {
                    setLeval(getLeval() + 1);
                    setBlood(getBlood() + 1500);
                    setAttack(getAttack() + 6);
                    setDefense(getDefense() + 6);
                    MyPanelright.message = "���ף�� ������";
                    break;
                } else {
                    setLeval(getLeval() + 3);
                    setBlood(getBlood() + 8000);
                    setAttack(getAttack() + 21);
                    setDefense(getDefense() + 21);
                    MyPanelright.message = "���ף�� ����������";
                    break;
                }
            case 24://ʮ�ּ�
                haveCross = 1;
                //Obj o1 = (Obj)Man.getElement(20, 5, 7);
                //o1.type=16;
                //Manager.getManager(20).getThings()[5][7]=16;
                MyPanelright.message = "���ʮ�ּܣ� ��ȥ0������Ů�ɣ�";
                break;
            case 27://����
                money += 300;
                MyPanelright.message = "���+300��";
                break;
            case 28://����
                attack += 80;
                MyPanelright.message = "�������������+80";
                break;
            case 29://��֮����
                haveCompass = true;
                TalkPanel.windows = 6;
                TalkPanel.issleep = false;
                MyPanelright.issleep = true;
                break;
            case 30://�ƽ��
                defense += 90;
                MyPanelright.message = "������ܣ�����+90";
                break;
            case 31://����
                haveHoe = 1;
                MyPanelright.message = "������䣡��ȥ4��ץС͵�ɣ�";
                break;
            case 32://ʥˮ
                blood += blood;
                MyPanelright.message = "���ʥˮ������ֵ������";
                break;
            case 33://�󱦽�
                attack += 160;
                MyPanelright.message = "���ʥ��������+160";
                break;
            case 34://�����
                defense += 200;
                MyPanelright.message = "���ʥ�ܣ�����+200";
                break;
        }
        if (caneat) {
            o.type = 11;
            Music.play("data/audio/�Ե���.wav");
        }
        return caneat;
    }

    public boolean canOpenDoor(int x, int y) {//��x,y������Ӧ
        if (Manager.getManager(Auto.getKey()).getThings()[x][y] == 5) {//����
            if (getYellowKey() == 0)
                return false;
            setYellowKey(getYellowKey() - 1);
            return true;
        }
        if (Manager.getManager(Auto.getKey()).getThings()[x][y] == 6) {//����
            if (getBlueKey() == 0)
                return false;
            setBlueKey(getBlueKey() - 1);
            return true;
        }
        if (Manager.getManager(Auto.getKey()).getThings()[x][y] == 7) {//����
            if (getRedKey() == 0)
                return false;
            setRedKey(getRedKey() - 1);
            return true;
        }
        return false;
    }

    public static GameElement getElement(int x, int y) {//����x,y�ϵĶ���
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

    public static GameElement getElement(int key, int x, int y) {//����x,y�ϵĶ���
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
                if (typ == 12 || typ == 19 || typ == 109) {//���������߻��������ǽ
                    return false;
                }
                return true;
            case 38:
                if (my == 0)
                    return false;
                typ = Manager.getManager(Auto.getKey()).getThings()[mx][my - 1];
                if (typ == 12 || typ == 19 || typ == 25 || typ == 26 || typ == 109) {//��������ϱ߻����ϱ���ǽ
                    return false;
                }
                return true;
            case 39:
                if (mx == 10)
                    return false;
                typ = Manager.getManager(Auto.getKey()).getThings()[mx + 1][my];
                if (typ == 12 || typ == 19 || typ == 109) {//��������ұ߻����ұ���ǽ
                    return false;
                }
                return true;
            case 40:
                if (my == 10)
                    return false;
                typ = Manager.getManager(Auto.getKey()).getThings()[mx][my + 1];
                if (typ == 12 || typ == 19 || typ == 109) {//��������±߻����±���ǽ
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
