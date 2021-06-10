package view;

import element.Man;

import javax.swing.*;

public class ScrollEnemyLookPanel extends JScrollPane implements Runnable {
    private EnemyLookPanel enemyLookPanel;
    private MyFrame myFrame;

    public ScrollEnemyLookPanel(EnemyLookPanel enemyLookPanel, MyFrame myFrame) {
        this.myFrame = myFrame;
        this.enemyLookPanel = enemyLookPanel;
        this.setViewportView(enemyLookPanel);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void run() {
        while (true) {
            enemyLookPanel.repaint();
            myFrame.scrollEnemyLookPanel.setVisible(true);
            try {
                while (EnemyLookPanel.issleep || !Man.haveBook) {
                    myFrame.scrollEnemyLookPanel.setVisible(false);
                    Thread.sleep(200);
                }
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
