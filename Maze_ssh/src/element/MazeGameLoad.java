package element;

import java.io.*;
import java.util.Properties;
import javax.swing.ImageIcon;

import controller.Auto;

public class MazeGameLoad {
    private static int pictureNum = 200;
    private static Manager em = null;//��ȡ��Դ������
    public static final ImageIcon[] imgArr = new ImageIcon[pictureNum];

    public static void createMan(int x, int y) {
        Man man = Man.createMan(x, y);
        Manager.getManager(1).getManList().add(man);
    }

    /**
     * �˺������ڹ����ֲ��ȥ�ظ�����
     *
     * @param type
     * @return ��Ϊenemy��дequals��hashcode������������ʾ��������ֻ�ܸ��ݹ���type���жϣ�
     * ����ǰ�����й���type�Ž�set���ظ������У������ȡ������type�����ô˺�������Enemy���ͣ�
     * �Ӷ��õ����ֹ������ϸ��Ϣ
     */
    public static Enemy getEnemyByType(int type) {
        return new Enemy(0, 0, imgArr[type], type);
    }

    public static void mapLoad(int key, String res) {
        System.out.println("mapload����" + key + "��ͼ");

        em = Manager.getManager(key);
        if (imgArr[1] == null) {
            for (int i = 1; i <= 50; i++) {
                imgArr[i] = new ImageIcon("src/data/obj/" + i + ".jpg");
            }
            for (int i = 113; i < pictureNum; i++) {
                imgArr[i] = new ImageIcon("src/data/change/" + i + ".jpg");
            }
        }
        //�жϴ˹ؿ��Ƿ��Ѿ������ع���
        boolean f = false;
        if (!Auto.isLoad[key]) {
            Auto.isLoad[key] = true;
            System.out.println("���ص�" + key + "��");
            loadPro(key, res);
        }
    }

    private static Properties ps = new Properties();

    private static void loadPro(int key, String res) {
        //�Լ�����д��ȡ�ļ��ĺ���
        try {
            String url = res + key + ".map";
            File file = new File("src/data/pro/" + key + ".map");
            InputStream in = new FileInputStream(file);
            BufferedReader bis = new BufferedReader(new InputStreamReader(in));
            String readLine = bis.readLine();
            Manager.getManager(Auto.getKey()).thingnum = new int[11][11];
            System.out.println("loadPro  " + url);
            while ((readLine = bis.readLine()) != null) {
                if (readLine.equals("")) continue;
                String[] indexname = readLine.split("=");
                int name = Integer.parseInt(indexname[0]);
                String[] local = indexname[1].split(";");
                for (int i = 0; i < local.length; i++) {//ÿһ������
                    String s = local[i];//ȡ��ÿ�������   100,20
                    String arrS[] = s.split(","); //{"100","20"}  //��x��y������
                    int x = Integer.parseInt(arrS[0]);//������
                    int y = Integer.parseInt(arrS[1]);//������
                    (Manager.getManager(Auto.getKey()).thingnum[x][y])++;
                    if (Manager.getManager(Auto.getKey()).thingnum[x][y] == 2) {
                        System.out.print(em.getThings()[x][y] + "----");
                        Manager.getManager(Auto.getKey()).getThings()[x][y] = name;
                        System.out.println(em.getThings()[x][y]);
                    } else {
                        Manager.getManager(Auto.getKey()).getThings()[x][y] = name;
                    }
                    if (name > 100) {//���ʹ���100
                        em.getEnemyList().add(new Enemy(x, y, imgArr[name], name));
                        if (name == 159)
                            System.out.println("������һ���������" + imgArr[name]);
                        //System.out.println("������һ���������"+name+": "+x+","+y);
                    } else {
                        if (Man.haveCross == 2 && Auto.getKey() == 20 && x == 5 && y == 7 && name == 11) {//����ʮ�ּܣ���ô����20���ʱ��
                            System.out.println("û��ȥ��20��");
                            em.getObjList().add(new Obj(x, y, imgArr[16], 16));
                            Manager.getManager(Auto.getKey()).getThings()[x][y] = 16;
                        } else
                            em.getObjList().add(new Obj(x, y, imgArr[name], name));
                        //System.out.println("������һ����Ʒ");
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
