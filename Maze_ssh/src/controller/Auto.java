package controller;


import element.Man;
import element.Manager;
import element.MazeGameLoad;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 游戏运行控制
 *
 * @author Administrator
 * 多线程: 1.用于屏幕的刷新
 * 第2种方式： 继承与 Thread多线程类  重写 run方法
 */
public class Auto extends Thread {
    private Manager em = Manager.getManager(key);//获取这关的管理器
    private int time = 0;//用于调整怪物的图片变动的快慢
    public static boolean canFly = false;
    private static int key = 1;//第几关
    private static int canGostairs = 0;//1表示上楼 -1表示下楼
    public static boolean isBeforing = true;
    public static String res = "src/data/pro/";
    public static boolean[] isLoad = new boolean[30];

    //	游戏的主程序     游戏开发  游戏主程
    public void run() {
        while (true) {
            if (isBeforing) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                while (true) {
                    gameOpen();
                    gameRun();
                    gameOver();
                }
            }
        }
    }

    /**
     * 前戏：游戏进入场地的动画，或者是 游戏的元素加载
     */
    public void gameOpen() {
//		1.加载资源。
//		2.加载场景
        System.out.println("调用game open");
        Man man = (Man) Manager.getManager(1).getManList().get(0);
        if (key > man.tallestFloor)
            man.tallestFloor = key;
        MazeGameLoad.mapLoad(key, res);//第1关     em.getManList().get(0).getX(),em.getManList().get(0).getX()
        if (key == 10 && Manager.getManager(1).getManList().get(0).getY() == 6) {
            Manager.getManager(1).getManList().get(0).setX(5);
        }
        if (key == 9 && Manager.getManager(1).getManList().get(0).getY() == 6) {
            Manager.getManager(1).getManList().get(0).setX(6);
        }
        if (key == 21) {
            Manager.getManager(1).getManList().get(0).setY(5);
        }
        if (key == 1 && Manager.getManager(1).getManList().get(0).getX() == 5) {
            Manager.getManager(1).getManList().get(0).setY(10);
        }
        if (key == 0 && Manager.getManager(1).getManList().get(0).getY() == 10) {
            Manager.getManager(1).getManList().get(0).setY(0);
        }
        canGostairs = 0;
    }

    /**
     * 游戏运行时: 人物动作，敌人出现，等等；   物理碰撞
     */
    public void gameRun() {
        if (!Music.bg_played) {
            Music.play_bg();
            Music.bg_played = true;
        }
        System.out.println("game run");
        while (canGostairs == 0 && !canFly) {
//			所有的元素的 移动等事项
            try {
                time++;
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (canFly)
            canFly = false;
        if (canGostairs != 0)
            key += canGostairs;

    }

    public static AutoArc getArc() {
        AutoArc autoArc = new AutoArc(Auto.canFly, Auto.getKey(), Auto.getCanGostairs(), Auto.isIsBeforing(), Auto.res);
        return autoArc;
    }


    private void gameOver() {
    }

    public Manager getEm() {
        return em;
    }

    public void setEm(Manager em) {
        this.em = em;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static boolean isCanFly() {
        return canFly;
    }

    public static void setCanFly(boolean canFly) {
        Auto.canFly = canFly;
    }

    public static boolean isIsBeforing() {
        return isBeforing;
    }

    public static void setIsBeforing(boolean isBeforing) {
        Auto.isBeforing = isBeforing;
    }

    public static String getRes() {
        return res;
    }

    public static void setRes(String res) {
        Auto.res = res;
    }

    public static int getKey() {
        return key;
    }

    public static void setKey(int key) {
        Auto.key = key;
    }

    public static int getCanGostairs() {
        return canGostairs;
    }

    public static void setCanGostairs(int canGostairs) {
        Auto.canGostairs = canGostairs;
    }

}
