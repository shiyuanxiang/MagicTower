package element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Serializable {
    public Manager() {
    }

    public static Manager[] em = new Manager[25];//ÿһ�㶼��һ�����ݹ����

    public synchronized static Manager getManager(int k) {//����Ԫ����Դ��
        return em[k];
    }

    static {
        //��ʼ��������
        for (int i = 0; i < 25; i++) {
            em[i] = new Manager();
        }
    }

    int[][] thingnum = new int[11][11];//ÿ��λ�õļ�������(���ڸ������µ�ͼ)
    private int[][] things = new int[11][11];//ÿ��λ�õ�type
    private List<GameElement> objList = new ArrayList<GameElement>();
    String s = new String();
    String[] s1 = new String[3];

    public List<GameElement> getObjList() {
        return objList;
    }

    private List<GameElement> manList = new ArrayList<>();

    public List<GameElement> getManList() {
        return manList;
    }

    private List<GameElement> enemyList = new ArrayList<>();

    public List<GameElement> getEnemyList() {
        return enemyList;
    }

    public int[][] getThings() {
        return things;
    }

    public void setThings(int[][] things) {
        this.things = things;
    }

    public void setManList(List<GameElement> manList) {
        this.manList = manList;
    }

    public void setObjList(List<GameElement> objList) {
        this.objList = objList;
    }

    public void setEnemyList(List<GameElement> enemyList) {
        this.enemyList = enemyList;
    }
}
