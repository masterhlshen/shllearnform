package com.shl.playalgorithm.gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Test {

    public static void main(String[] args) {
        Frame f = new Frame("测试");
        Panel p = new Panel();
        TestCanvas canvas = new TestCanvas();
        // canvas.setPreferredSize(new Dimension(250, 180));
        canvas.repaint();
        f.add(canvas);
        f.add(p);
        f.pack();
        f.setBounds(0, 0, 1000, 1000);
        f.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        f.setVisible(true);
    }


}

class TestCanvas extends Canvas{

    @Override
    public void print(Graphics g) {
        // super.print(g);
        g.setColor(new Color(220, 100, 80));
        g.drawRect(200, 200, 2000, 200);

    }
}
