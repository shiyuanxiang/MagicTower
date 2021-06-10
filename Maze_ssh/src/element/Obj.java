package element;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import controller.Auto;

public class Obj extends GameElement {
    public int type = 11;//墙的种类 默认为土墙, 11*11的格子    65像素*65像素     一行(列): 65*11=715 根据墙的类型判断占用几个格子

    public Obj(int X, int Y, ImageIcon icon, int type) {
        super(X, Y, GameElement.getDefault_size(), GameElement.getDefault_size(), icon);
        this.type = type;
    }

    public void show(Graphics g) {
        super.show(g);
        changeImg();
        g.drawImage(this.getIcon().getImage(), this.getX() * GameElement.getDefault_size(), getY() * GameElement.getDefault_size(), getWidth(), getHeight(), null);
        g.setFont(new Font("宋体", Font.BOLD, 23));//字号设置为100
        g.setColor(new Color(255, 255, 255));
        g.setColor(new Color(255, 0, 0));
        if (Manager.getManager(Auto.getKey()).thingnum[getX()][getY()] > 1) //判断此地是否有多个物体
            g.drawString(Manager.getManager(Auto.getKey()).thingnum[getX()][getY()] + "", getX() * GameElement.getDefault_size(), getY() * GameElement.getDefault_size() + 30);
    }

    private void changeImg() {
        this.setIcon(new ImageIcon("src/data/obj/" + type + ".jpg"));
    }
}
