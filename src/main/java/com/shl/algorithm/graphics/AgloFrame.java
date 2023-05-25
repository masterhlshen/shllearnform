package com.shl.algorithm.graphics;

import javax.swing.*;
import java.awt.*;

public class AgloFrame extends JFrame {

    public AgloFrame(String title) {
        this(title, 1024 * 2, 1024);
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

            Graphics2D gd = (Graphics2D) g;

            AgloVisHelper.setStrokeWidth(gd, 5);

            gd.setColor(Color.RED);
            AgloVisHelper.strokeCircle(gd, canvasWidth / 2, canvasHeight / 2, 500);
            gd.setColor(Color.BLUE);
            AgloVisHelper.fillCircle(gd, canvasWidth / 2, canvasHeight / 2 , 500);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
