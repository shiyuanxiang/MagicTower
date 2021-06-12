package main;


import controller.Auto;
import controller.GameKeyListener;
import controller.Music;
import element.MazeGameLoad;
import session.Session;
import view.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MyMain {
    private static MyFrame frame = new MyFrame();
    public static Auto myauto;

    public static void main(String[] args) {
        //配置信息
        Config config = new Config();
        File file = new File("data/config/config.txt");
        if (file.length() == 0) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            config.init();
            while (!config.isRecorded) ;
        }
        config.readConfig();
        //保存屏幕信息
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        Session.screen_width = screensize.width;
        Session.screen_height = screensize.height;

        Login login = new Login();
        long i = 0;
        while (!login.isLogin()) {
            System.out.println();
        }
        login.dispose();
        File file1 = new File("data/archive/" + Session.session.get("username") + "_keep1.txt");
        File file2 = new File("data/archive/" + Session.session.get("username") + "_keep2.txt");
        File file3 = new File("data/archive/" + Session.session.get("username") + "_keep3.txt");
        try {
            if (!file1.exists())
                file1.createNewFile();
            if (!file2.exists())
                file2.createNewFile();
            if (!file3.exists())
                file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyFrame frame = new MyFrame();
        frame.setLayout(null);
        MyPanelright panelright = new MyPanelright();
        TalkPanel talkpanel = new TalkPanel();
        EnemyLookPanel enemylookpanel = new EnemyLookPanel();
        Compass compass = new Compass();
        MyPanelleft panelleft = new MyPanelleft();
        Auto auto = new Auto();
        myauto = auto;
        MazeGameLoad.createMan(5, 9);//创建人物
        GameKeyListener key = new GameKeyListener();
        frame.setPanelleft(panelleft);
        frame.setTalkpanel(talkpanel);
        frame.setEnemylookpanel(enemylookpanel);
        frame.setCompass(compass);
        frame.setPanelright(panelright);
        frame.setKey(key);
        frame.setThread(auto);
        frame.showThis();
        Music.play_op();
    }
}
