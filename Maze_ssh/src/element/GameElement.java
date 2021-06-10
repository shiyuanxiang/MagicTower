package element;

import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class GameElement implements Serializable {
    private int X;
    private int Y;
    private int width = default_size;
    private int height = default_size;
    private static int default_size = 65;
    private ImageIcon icon;

    GameElement() {
    }

    /**
     * @return x
     */
    public int getX() {
        return X;
    }

    /**
     * @param x Ҫ���õ� x
     */
    public void setX(int x) {
        X = x;
    }

    /**
     * @return y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param y Ҫ���õ� y
     */
    public void setY(int y) {
        Y = y;
    }

    /**
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width Ҫ���õ� width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height Ҫ���õ� height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return icon
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * @param icon Ҫ���õ� icon
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    GameElement(int X, int Y, int width, int height, ImageIcon icon) {
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
        this.icon = icon;
    }

    public synchronized void show(Graphics g) {
    }

    public static int getDefault_size() {
        return default_size;
    }

    public void clickKey(boolean b, int keyCode) {
    }
}
