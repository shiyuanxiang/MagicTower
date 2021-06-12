package element;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import controller.Auto;

public class Enemy extends GameElement {
    public int type;
    private int blood;
    private String name;
    private int attack;
    private int defense;
    private int money;
    private int exe;
    private int time = 0;

    public Enemy(int X, int Y, ImageIcon icon, int type) {
        super(X, Y, GameElement.getDefault_size(), GameElement.getDefault_size(), icon);
        this.setType(type);
        switch (type) {
            case 121:
                setName("ʷ��ķ");
                blood = 50;
                attack = 20;
                defense = 1;
                money = 1;
                exe = 1;
                break;
            case 123:
                setName("��ɫʷ��ķ");
                blood = 70;
                attack = 15;
                defense = 2;
                money = 2;
                exe = 2;
                break;
            case 125:
                setName("����");
                blood = 100;
                attack = 20;
                defense = 5;
                money = 3;
                exe = 3;
                break;
            case 127:
                setName("��ͷ��");
                blood = 200;
                attack = 35;
                defense = 10;
                money = 5;
                exe = 3;
                break;
            case 129:
                setName("������ʦ");
                blood = 125;
                attack = 50;
                defense = 25;
                money = 15;
                exe = 10;
                break;
            case 131:
                setName("������");
                blood = 110;
                attack = 25;
                defense = 5;
                money = 7;
                exe = 6;
                break;
            case 133:
                setName("����ʿ��");
                blood = 150;
                attack = 40;
                defense = 20;
                money = 10;
                exe = 8;
                break;
            case 135:
                setName("������");
                blood = 300;
                attack = 70;
                defense = 40;
                money = 16;
                exe = 13;
                break;
            case 137:
                setName("����ʿ");
                blood = 850;
                attack = 345;
                defense = 200;
                money = 50;
                exe = 40;
                break;
            case 139:
                setName("��ӳ�");
                blood = 900;
                attack = 750;
                defense = 650;
                money = 250;
                exe = 80;
                break;
            case 141:
                setName("������");
                blood = 150;
                attack = 65;
                defense = 30;
                money = 15;
                exe = 12;
                break;
            case 143:
                setName("������");
                blood = 550;
                attack = 150;
                defense = 90;
                money = 40;
                exe = 30;
                break;
            case 145:
                setName("�߼���ʦ");
                blood = 100;
                attack = 200;
                defense = 110;
                money = 40;
                exe = 40;
                break;
            case 147:
                setName("��������");
                blood = 450;
                attack = 140;
                defense = 90;
                money = 35;
                exe = 35;
                break;
            case 149:
                setName("������ʿ");
                blood = 900;
                attack = 450;
                defense = 330;
                money = 55;
                exe = 45;
                break;
            case 151:
                setName("ʯͷ����");
                blood = 500;
                attack = 115;
                defense = 65;
                money = 15;
                exe = 15;
                break;
            case 153:
                setName("����");
                blood = 700;
                attack = 250;
                defense = 125;
                money = 50;
                exe = 50;
                break;
            case 155:
                setName("���öӳ�");
                blood = 400;
                attack = 80;
                defense = 50;
                money = 30;
                exe = 30;
                break;
            case 157:
                setName("������ʿ");
                blood = 1000;
                attack = 300;
                defense = 150;
                money = 50;
                exe = 40;
                break;
            case 159:
                setName("���·�ʦ");
                blood = 1000;
                attack = 120;
                defense = 70;
                money = 30;
                exe = 30;
                break;
            case 161:
                setName("���·�ʦ");
                blood = 500;
                attack = 400;
                defense = 260;
                money = 60;
                exe = 50;
                break;
            case 163:
                setName("ڤ����");
                blood = 1250;
                attack = 500;
                defense = 400;
                money = 70;
                exe = 60;
                break;
            case 165:
                setName("�߼�����");
                blood = 1500;
                attack = 550;
                defense = 460;
                money = 65;
                exe = 70;
                break;
            case 167:
                setName("˫�ֽ�ʿ");
                blood = 1200;
                attack = 620;
                defense = 520;
                money = 70;
                exe = 85;
                break;
            case 169:
                setName("ڤսʿ");
                blood = 2000;
                attack = 680;
                defense = 590;
                money = 80;
                exe = 80;
                break;
            case 171:
                setName("����ʿ");
                blood = 1305;
                attack = 1200;
                defense = 900;
                money = 80;
                exe = 80;
                break;
            case 173:
                setName("�鷨ʦ");
                blood = 1500;
                attack = 1700;
                defense = 1700;
                money = 90;
                exe = 90;
                break;
            case 175:
                setName("ڤ�ӳ�");
                if (Auto.getKey() < 17) {
                    blood = 2500;
                    attack = 900;
                    defense = 850;
                    money = 70;
                    exe = 70;
                } else {
                    blood = 3000;
                    attack = 1200;
                    defense = 1150;
                    money = 100;
                    exe = 95;
                }
                break;
            case 177:
                setName("����ħ��");
                blood = 15000;
                attack = 1400;
                defense = 1300;
                money = 300;
                exe = 300;
                break;
            case 179:
                setName("Ӱ��սʿ");
                //if(Auto.getKey()>=17)
                blood = 3100;
                attack = 1150;
                defense = 1050;
                money = 100;
                exe = 90;
                break;
            case 181:
                if (Auto.getKey() != 21) {
                    setName("ڤ��ħ��");
                    blood = 30000;
                    attack = 1800;
                    defense = 1800;
                    money = 250;
                    exe = 220;
                    break;
                } else {
                    setName("ڤ��ħ��");
                    blood = 50000;
                    attack = 3200;
                    defense = 3100;
                    money = 250;
                    exe = 220;
                    break;
                }
        }
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

    public void show(Graphics g) {
        super.show(g);
        changeImg();
        g.drawImage(this.getIcon().getImage(), this.getX() * GameElement.getDefault_size(), getY() * GameElement.getDefault_size(), getWidth(), getHeight(), null);
        g.setFont(new Font("����", Font.BOLD, 23));//�ֺ�����Ϊ100
        g.setColor(new Color(255, 255, 255));
        if (Manager.getManager(Auto.getKey()).thingnum[getX()][getY()] > 1) {
            g.drawString(Manager.getManager(Auto.getKey()).thingnum[getX()][getY()] + "", getX() * GameElement.getDefault_size(), getY() * GameElement.getDefault_size() + 30);

            g.drawString(this.getX() + "," + getY(), getX() * GameElement.getDefault_size(), getY() * GameElement.getDefault_size() + 55);
        }
    }

    private void changeImg() {
        time++;
        if (time % 2 == 1) {//������  ż����
            if (time % 7 == 1) // ����
                this.setIcon(new ImageIcon("data/change/" + (getType() + 1) + ".jpg"));
        } else {//ż��
            if (time % 7 == 1)
                this.setIcon(new ImageIcon("data/change/" + getType() + ".jpg"));
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
