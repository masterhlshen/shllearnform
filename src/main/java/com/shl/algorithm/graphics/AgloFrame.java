package com.shl.algorithm.graphics;

import javax.swing.*;
import java.awt.*;

public class AgloFrame extends JFrame {

    public AgloFrame(String title) {
        this(title, 1024, 768);
    }

    public AgloFrame(String title, int canvasWidth, int canvasHeigth) {
        super(title);
        this.canvasHeight = canvasHeigth;
        this.canvasWidth = canvasWidth;
        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private int canvasHeight;
    private int canvasWidth;

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    private class AlgoCanvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawOval(50, 50, 300, 300);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
