package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TalkPanel extends JPanel implements Runnable {
    public static boolean issleep = true;
    public static int windows = 2;
    public static int index = 1;//ҳ������һ�¿ո񷭵���һҳ
    String str1 = " ��Ҫ�������������?";
    String str21 = "�������25�����,";
    String str22 = "��������㹻�ľ���,";
    String str3 = "���������ѡ��һ��:";
    String str4 = "���¼�ѡ�񣬿ո��ȷ��";
    public static int loca = 1;//���Ĭ��ָ���һ��

    public void paint(Graphics g) {
        super.paint(g);
        setBackground(new Color(100, 100, 100));
        this.setBounds(450, 80, 400, 400);
        g.setFont(new Font("����", Font.BOLD, 26));//�ֺ�����
        g.setColor(new Color(255, 255, 255));

        if (windows == 1 || windows == 2) {//��������ר��
            g.drawString(str1, 200 - str1.length() * 13, 30);
            if (windows == 1) {
                g.drawString(str21, 200 - str21.length() * 13, 65);
                g.drawString("����800������", 200 - "����800������".length() * 13, 185);//225-length*����/2
                g.drawString("����4�㹥��", 200 - "����4�㹥��".length() * 13, 240);
                g.drawString("����4�����", 200 - "����4�����".length() * 13, 310);
            } else if (windows == 2) {
                g.drawString(str22, 200 - str22.length() * 13, 65);
                g.drawString("100������һ��", 200 - "100������һ��".length() * 13, 185);
                g.drawString("30��������5�㹥��", 200 - "30��������5�㹥��".length() * 13, 240);
                g.drawString("30��������5�����", 200 - "30��������5�����".length() * 13, 310);
            }
            g.drawString(str3, 200 - str3.length() * 13, 100);
            g.drawString(str4, 200 - str4.length() * 13, 135);
            g.drawString("�˳�", 200 - "�˳�".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//ѡ��ľ��η���
        } else if (windows == 100 || windows == 200) {//�߼�����ר��
            g.drawString(str1, 200 - str1.length() * 13, 30);
            if (windows == 100) {
                g.drawString("�������100�����", 200 - "�������100�����".length() * 13, 65);
                g.drawString("����4000������", 200 - "����4000������".length() * 13, 185);//225-length*����/2
                g.drawString("����20�㹥��", 200 - "����20�㹥��".length() * 13, 240);
                g.drawString("����20�����", 200 - "����20�����".length() * 13, 310);
            } else if (windows == 200) {
                g.drawString(str22, 200 - str22.length() * 13, 65);
                g.drawString("270����������", 200 - "270����������".length() * 13, 185);
                g.drawString("95��������17�㹥��", 200 - "95��������17�㹥��".length() * 13, 240);
                g.drawString("95��������17�����", 200 - "95��������17�����".length() * 13, 310);
            }
            g.drawString(str3, 200 - str3.length() * 13, 100);
            g.drawString(str4, 200 - str4.length() * 13, 135);
            g.drawString("�˳�", 200 - "�˳�".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//ѡ��ľ��η���
        } else if (windows == 3) {//�ý�һ�ȡԿ��
            g.drawString("��������㹻�Ľ��", 200 - "��������㹻�Ľ��".length() * 13, 30);
            g.drawString("�ҿ�������һЩԿ��", 200 - "�ҿ�������һЩԿ��".length() * 13, 65);
            g.drawString("�������ѡһ��", 200 - "�������ѡһ��".length() * 13, 100);
            g.drawString("10������Կ��", 200 - "10������Կ��".length() * 13, 185);
            g.drawString("50�������Կ��", 200 - "50�������Կ��".length() * 13, 240);
            g.drawString("100������Կ��", 200 - "100������Կ��".length() * 13, 310);
            g.drawString("�˳�", 200 - "�˳�".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//ѡ��ľ��η���
        } else if (windows == 300) {//��Կ�׻����
            g.drawString("�������ҪһЩ���", 200 - "�������ҪһЩ���".length() * 13, 30);
            g.drawString("�ҿ��Ի���һЩԿ��", 200 - "�ҿ��Ի���һЩԿ��".length() * 13, 65);
            g.drawString("�������ѡһ��", 200 - "�������ѡһ��".length() * 13, 100);
            g.drawString("7��һ���һ�ѻ�Կ��", 200 - "7��һ���һ�ѻ�Կ��".length() * 13, 185);
            g.drawString("35��һ���һ����Կ��", 200 - "35��һ���һ����Կ��".length() * 13, 240);
            g.drawString("70��һ���һ�Ѻ�Կ��", 200 - "70��һ���һ�Ѻ�Կ��".length() * 13, 310);
            g.drawString("�˳�", 200 - "�˳�".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//ѡ��ľ��η���
        } else if (windows == 4) {//�����ֲ�
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("��ϲ���ù����ֲ�", 200 - "��ϲ���ù����ֲ�".length() * 13, 30);
            g.drawString("��L���ɲ鿴�͹رչ�����Ϣ", 200 - "��L���ɲ鿴�͹رչ�����Ϣ".length() * 13, 65);
            g.drawString("�������ȷ��", 200 - "�������ȷ��".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 5) {//С͵������¥����  5--��6   �ҵ�С͵���ж���û��������win7��û��win
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("�ǳ���л��������������", 200 - "�ǳ���л��������������".length() * 13, 30);
            g.drawString("Ϊ�˱�����", 200 - "Ϊ�˱�����".length() * 13, 65);
            g.drawString("һ����һ�ѵڶ���İ��Ŵ�", 200 - "һ����һ�ѵڶ���İ��Ŵ�".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 500) {//С͵������¥����  5--��6   �ҵ�С͵���ж���û��������win7��û��win
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("����", 200 - "����".length() * 13, 30);
            g.drawString("Ҫ��������ҵ��ҵ�����", 200 - "Ҫ��������ҵ��ҵ�����".length() * 13, 65);
            g.drawString("�Ҿ��ܰ����ͨ18������", 200 - "�Ҿ��ܰ����ͨ18������".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 5000) {//�ҵ�������
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("̫���ˣ����ҵ����ˣ�", 200 - "̫���ˣ����ҵ����ˣ�".length() * 13, 30);
            g.drawString("�����ھͰ�����ͨ18������", 200 - "�����ھͰ�����ͨ18������".length() * 13, 65);
            //g.drawString("�Ҿ��ܰ����ͨ18������", 200-"�Ҿ��ܰ����ͨ18������".length()*13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 6) {//��֮����
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("��ϲ���÷�֮����", 200 - "��ϲ���÷�֮����".length() * 13, 30);
            g.drawString("��J��ѡ����ȥ��¥��", 200 - "��J��ѡ����ȥ��¥��".length() * 13, 65);
            g.drawString("���ո��ȷ��", 200 - "���ո��ȷ��".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 7) {//15��������ˣ��ӹ���120
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("��������ˣ������и��ϵ�����", 200 - "��������ˣ������и��ϵ�����".length() * 13, 30);
            g.drawString("ֻҪ���500�����Ҿ�������", 200 - "ֻҪ���500�����Ҿ�������".length() * 13, 65);
            g.drawString("���ո��ȷ��", 200 - "���ո��ȷ��".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 700) {//15���Ů���ˣ��ӷ���120
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("��������ˣ������и��ϵȷ���", 200 - "��������ˣ������и��ϵȷ���".length() * 13, 30);
            g.drawString("ֻҪ���500����Ҿ�������", 200 - "ֻҪ���500����Ҿ�������".length() * 13, 65);
            g.drawString("���ո��ȷ��", 200 - "���ո��ȷ��".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 7000) {//2��������ˣ��ֽ��ӹ���----30
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ
            g.drawString("Ŷ���ҵĺ��ӣ�����̫лл����", 200 - "Ŷ���ҵĺ��ӣ�����̫лл����".length() * 13, 30);
            g.drawString("��������������", 200 - "��������������".length() * 13, 65);
            g.drawString("�����������ʱ���õ�", 200 - "�����������ʱ���õ�".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 70000) {//2���Ů���ˣ��ֶܼӷ���----30
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ
            g.drawString("Ŷ���죬����̫��л����", 200 - "Ŷ���죬����̫��л����".length() * 13, 30);
            g.drawString("Ϊ�˱���ҵ�л��", 200 - "Ϊ�˱���ҵ�л��".length() * 13, 65);
            g.drawString("����������͸�����", 200 - "����������͸�����".length() * 13, 100);
            g.drawString("ȷ��", 200 - "ȷ��".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 8) {//18��Ĺ���
            g.setFont(new Font("����", Font.BOLD, 25));//�ֺ�����Ϊ100
            g.drawString("�������������ҵ���", 200 - "�������������ҵ���".length() * 13, 30);
            g.drawString("�����һ�������", 200 - "�����һ�������".length() * 13, 65);
            g.drawString("�������ۿ��Ŵ�ħ����ɱ��", 200 - "�������ۿ��Ŵ�ħ����ɱ��".length() * 13, 100);
            g.drawString("�õ�", 200 - "�õ�".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 9) {//0�����Ů
            g.setFont(new Font("����", Font.BOLD, 23));//�ֺ�����Ϊ100
            g.drawString("������������ӣ�������������", 200 - "������������ӣ�������������".length() * 13, 30);
            g.drawString("�ҿ��Ը�������һЩ����", 200 - "�ҿ��Ը�������һЩ����".length() * 13, 65);
            g.drawString("��������Ȱ����ҵ����߲��ʮ�ּ�", 200 - "��������Ȱ����ҵ����߲��ʮ�ּ�".length() * 13, 100);
            g.drawString("�õ�", 200 - "�õ�".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 90) {//0�����Ů
            g.setFont(new Font("����", Font.BOLD, 24));//�ֺ�����Ϊ100
            g.drawString("�ҵ���������������", 200 - "�ҵ���������������".length() * 12, 30);
            g.drawString("�Ҿ��ܰ����������ȥ�ȹ�����", 200 - "�Ҿ��ܰ����������ȥ�ȹ�����".length() * 12, 65);
            g.drawString("���Ұ���򿪵�21�����", 200 - "���Ұ���򿪵�21�����".length() * 13, 100);
            g.drawString("�õ�", 200 - "�õ�".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        } else if (windows == 900) {//�ҵ���ʮ�ּ�
            g.setFont(new Font("����", Font.BOLD, 24));//�ֺ�����Ϊ100
            g.drawString("���ҵ������������ھͰ������������", 200 - "���ҵ������������ھͰ������������".length() * 12, 30);
            g.drawString("��������壡", 200 - "��������壡".length() * 12, 65);
            g.drawString("���ˣ���ȥ����Ĺ�����", 200 - "���ˣ���ȥ����Ĺ�����".length() * 13, 100);
            g.drawString("�õ�", 200 - "�õ�".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//ѡ��ľ��η���
        }
    }

    public void run() {
        while (true) {
            try {
                while (issleep) {
                    Thread.sleep(1000);
                }
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}
