package controller;

import java.io.Serializable;

public class AutoArc implements Serializable {
    public boolean canFly = false;
    public int key = 1;//�ڼ���
    public int canGostairs = 0;//1��ʾ��¥ -1��ʾ��¥
    public boolean isBeforing = true;
    public String res = "data/pro/";

    public AutoArc(boolean canFly, int key, int canGostairs, boolean isBeforing, String res) {
        this.canFly = canFly;
        this.key = key;
        this.canGostairs = canGostairs;
        this.isBeforing = isBeforing;
        this.res = res;
    }
}
