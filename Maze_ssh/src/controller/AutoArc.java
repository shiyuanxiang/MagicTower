package controller;

import java.io.Serializable;

public class AutoArc implements Serializable {
    public boolean canFly = false;
    public int key = 1;//第几关
    public int canGostairs = 0;//1表示上楼 -1表示下楼
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
