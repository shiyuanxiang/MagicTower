package controller;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Music {
    public static boolean bg_played = false;

    public static void play_op() {
        play("data/audio/ø™Õ∑“Ù¿÷.wav");
    }

    public static void play_bg() {
        try {
            FileInputStream fileau = new FileInputStream("data/audio/±≥æ∞“Ù¿÷.wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Timer music_bg_timer = new Timer(66000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fileau = new FileInputStream("data/audio/±≥æ∞“Ù¿÷.wav");
                    AudioStream as = new AudioStream(fileau);
                    AudioPlayer.player.start(as);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        music_bg_timer.start();
    }

    public static void play(String pathname) {
        try {
            URL url = new File(pathname).toURL();
            Applet.newAudioClip(url).play();
            JFrame jf = new JFrame();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
    }
}
