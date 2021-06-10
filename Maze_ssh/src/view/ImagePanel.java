package view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    Image im;

    //构造函数制定Jpanel的大小
    public ImagePanel(Image im) {
        this.im = im;
        //希望该Panel的大小事自适应的
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(width, height);
    }

    //画出背景
    @Override
    protected void paintComponent(Graphics g) {
        // 清屏
        super.paintComponent(g);
        g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}