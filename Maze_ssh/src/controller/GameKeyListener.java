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

public class GameKeyListener implements KeyListener {//������

    public void keyPressed(KeyEvent e) {//��37 ����39    ��38   ��40
        if (Auto.isBeforing && MyPanelright.AlphaPecent > 150) {//����ǰ��
            if (e.getKeyCode() == 32) {
                Auto.isBeforing = false;
                MyPanelright.location = 700;
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (TalkPanel.issleep == false && TalkPanel.windows == 4) {//ȷ����ȡ�����ֲ�
            TalkPanel.issleep = true;
            MyPanelright.issleep = false;
        } else if (TalkPanel.issleep == false && TalkPanel.windows == 6) {//ȷ����ȡ��֮����
            TalkPanel.issleep = true;
            MyPanelright.issleep = false;
        } else if (e.getKeyCode() == 76 && Man.haveBook) {//ͼ��
            if (EnemyLookPanel.issleep) {//���ͼ��û��
                MyPanelright.issleep = true;
                EnemyLookPanel.issleep = false;
            } else {//���ͼ������
                EnemyLookPanel.issleep = true;
                MyPanelright.issleep = false;
            }
        } else if (e.getKeyCode() == 74 && Man.haveCompass) {//��֮����
            if (Compass.issleep) {//�������û��
                System.out.println("��֮���̣���������");
                Compass.loca = 1;
                MyPanelright.issleep = true;
                Compass.issleep = false;
            } else {//������̴���
                Compass.issleep = true;
                MyPanelright.issleep = false;
            }
        } else if (Compass.issleep == false) {
            if (e.getKeyCode() == 38) {//���������
                if (Compass.loca > 1)
                    Compass.loca--;
            } else if (e.getKeyCode() == 40) {//���������
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
                //���ж��Ƿ�ȥ����㣬���ȥ��
                Auto.setKey(Compass.loca);
            }
        } else if (MyPanelright.issleep == false) {//�����ڣ����������ƶ�
            Manager.getManager(1).getManList().get(0).clickKey(true, e.getKeyCode());
        } else {//�Ի������ƶ�
            keypress(e);
        }
    }

    private void keypress(KeyEvent e) {
        if (e.getKeyCode() == 38) {//���������
            TalkPanel.loca--;
            if (TalkPanel.loca == 0)
                TalkPanel.loca = 1;
        } else if (e.getKeyCode() == 40) {//���������
            TalkPanel.loca++;
            if (TalkPanel.loca == 5)
                TalkPanel.loca = 4;
        } else if (e.getKeyCode() == 32) {//�ո��ʾȷ��
            Man man = (Man) Manager.getManager(1).getManList().get(0);
            switch (TalkPanel.loca) {
                case 1://����������
                    if (TalkPanel.windows == 1) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 25);
                            man.setBlood(man.getBlood() + 800);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
                        }
                    } else if (TalkPanel.windows == 100) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 100);
                            man.setBlood(man.getBlood() + 4000);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
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
                            MyPanelright.message = "���鲻��";
                        }
                    } else if (TalkPanel.windows == 200) {
                        if (man.getExe() >= 300) {
                            man.setExe(man.getExe() - 300);
                            man.setLeval(man.getLeval() + 3);
                            man.setBlood(man.getBlood() + 8000);
                            man.setAttack(man.getAttack() + 21);
                            man.setDefense(man.getDefense() + 21);
                        }
                    } else if (TalkPanel.windows == 3) {//�����Կ��
                        //System.out.println("��Ҫ���Կ��");
                        if (man.getMoney() >= 10) {
                            man.setMoney(man.getMoney() - 10);
                            man.setYellowKey(man.getYellowKey() + 1);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "���鲻��";
                        }
                    } else if (TalkPanel.windows == 300) {//���ջ�Կ��
                        if (man.getYellowKey() >= 1) {
                            man.setYellowKey(man.getYellowKey() - 1);
                            man.setMoney(man.getMoney() + 7);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Կ�ײ���";
                        }
                    } else if (TalkPanel.windows == 5) {//С͵
                        TalkPanel.windows = 500;
                        System.out.println("С͵��ת��500");
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
                        MyPanelright.message = "����ǹ⽣������+120��";
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
                        MyPanelright.message = "����ǹ�ܣ�����+120��";
                        Manager.getManager(15).getEnemyList().remove(Man.getElement(15, 6, 3));
                        Manager.getManager(15).getObjList().add(new Obj(6, 3, new ImageIcon(""), 11));
                        Manager.getManager(15).getThings()[6][3] = 11;
                        break;
                    } else if (TalkPanel.windows == 7000) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        man.setAttack(man.getAttack() + 30);
                        MyPanelright.message = "��øֽ�������+30��";
                        Manager.getManager(2).getEnemyList().remove(Man.getElement(2, 7, 10));
                        Manager.getManager(2).getObjList().add(new Obj(7, 10, new ImageIcon(""), 11));
                        Manager.getManager(2).getThings()[7][10] = 11;
                        break;
                    } else if (TalkPanel.windows == 70000) {
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        man.setDefense(man.getDefense() + 30);
                        MyPanelright.message = "��øֶܣ�����+30��";
                        Manager.getManager(2).getEnemyList().remove(Man.getElement(2, 9, 10));
                        Manager.getManager(2).getObjList().add(new Obj(9, 10, new ImageIcon(""), 11));
                        Manager.getManager(2).getThings()[9][10] = 11;
                        break;
                    } else if (TalkPanel.windows == 8) {//10,10���α仯
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        if (man.haveHoe != 2)
                            break;
                        Obj o = (Obj) Man.getElement(18, 10, 10);
                        o.type = 16;
                        Manager.getManager(18).getThings()[10][10] = 16;
                        break;
                    } else if (TalkPanel.windows == 9) {//10,10���α仯
                        TalkPanel.windows = 90;
                        break;
                    } else if (TalkPanel.windows == 90) {//10,10���α仯
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
                    } else if (TalkPanel.windows == 900) {//10,10���α仯
                        TalkPanel.issleep = true;
                        MyPanelright.issleep = false;
                        Man.haveCross = 2;
                        if (man.tallestFloor == 20) {//�Ѿ�ȥ��20��Ļ�
                            System.out.println("�Ѿ�ȥ��20��");
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
                case 2://����
                    if (TalkPanel.windows == 1) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 25);
                            man.setAttack(man.getAttack() + 4);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
                        }
                    } else if (TalkPanel.windows == 100) {
                        if (man.getMoney() >= 100) {
                            man.setMoney(man.getMoney() - 100);
                            man.setAttack(man.getAttack() + 20);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
                        }
                    } else if (TalkPanel.windows == 2) {
                        if (man.getExe() >= 30) {
                            man.setExe(man.getExe() - 30);
                            man.setAttack(man.getAttack() + 5);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "���鲻��";
                        }
                    } else if (TalkPanel.windows == 200) {
                        if (man.getExe() >= 95) {
                            man.setExe(man.getExe() - 95);
                            man.setAttack(man.getAttack() + 17);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "���鲻��";
                        }
                    } else if (TalkPanel.windows == 3) {//������Կ��
                        if (man.getMoney() >= 50) {
                            man.setMoney(man.getMoney() - 50);
                            man.setBlueKey(man.getBlueKey() + 1);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
                        }
                    } else if (TalkPanel.windows == 300) {//������Կ��
                        if (man.getBlueKey() >= 1) {
                            man.setBlueKey(man.getBlueKey() - 1);
                            man.setMoney(man.getMoney() + 35);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Կ�ײ���";
                        }
                    }
                    System.out.println("ֱ��case2break��");
                    break;
                case 3://����
                    if (TalkPanel.windows == 1) {
                        if (man.getMoney() >= 25) {
                            man.setMoney(man.getMoney() - 25);
                            man.setDefense(man.getDefense() + 4);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
                        }
                    } else if (TalkPanel.windows == 100) {
                        if (man.getMoney() >= 100) {
                            man.setMoney(man.getMoney() - 100);
                            man.setDefense(man.getDefense() + 20);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
                        }
                    } else if (TalkPanel.windows == 2) {
                        if (man.getExe() >= 30) {
                            man.setExe(man.getExe() - 30);
                            man.setDefense(man.getDefense() + 5);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "���鲻��";
                        }
                    } else if (TalkPanel.windows == 200) {
                        if (man.getExe() >= 95) {
                            man.setExe(man.getExe() - 95);
                            man.setDefense(man.getDefense() + 17);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "���鲻��";
                        }
                    } else if (TalkPanel.windows == 3) {//�����Կ��
                        if (man.getMoney() >= 100) {
                            man.setMoney(man.getMoney() - 100);
                            man.setRedKey(man.getRedKey() + 1);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Ҳ���";
                        }
                    } else if (TalkPanel.windows == 300) {//���պ�Կ��
                        if (man.getRedKey() >= 1) {
                            man.setRedKey(man.getRedKey() - 1);
                            man.setMoney(man.getMoney() + 70);
                        } else {
                            TalkPanel.issleep = true;
                            MyPanelright.issleep = false;
                            MyPanelright.message = "��Կ�ײ���";
                        }
                    }
                    break;
                case 4://�˳�
                    TalkPanel.issleep = true;
                    MyPanelright.issleep = false;
                    break;
                default:
                    System.out.println("��������");
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {//�ɿ�
        List<GameElement> list = Manager.getManager(Auto.getKey()).getManList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).clickKey(false, e.getKeyCode());
        }
    }
}