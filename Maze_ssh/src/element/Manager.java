package element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Serializable {
    public Manager() {
    }

    public static Manager[] em = new Manager[25];//每一层都有一个数据管理库

    public synchronized static Manager getManager(int k) {//返回元素资源库
        return em[k];
    }

    static {
        //初始化管理类
        for (int i = 0; i < 25; i++) {
            em[i] = new Manager();
        }
    }

    int[][] thingnum = new int[11][11];//每个位置的加载数量(用于辅助更新地图)
    private int[][] things = new int[11][11];//每个位置的type
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
