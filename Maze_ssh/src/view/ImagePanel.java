package view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    Image im;

    //���캯���ƶ�Jpanel�Ĵ�С
    public ImagePanel(Image im) {
        this.im = im;
        //ϣ����Panel�Ĵ�С������Ӧ��
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(width, height);
    }

    //��������
    @Override
    protected void paintComponent(Graphics g) {
        // ����
        super.paintComponent(g);
        g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}