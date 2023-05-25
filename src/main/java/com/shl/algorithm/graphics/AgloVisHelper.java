package com.shl.algorithm.graphics;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AgloVisHelper {

    private AgloVisHelper() {
    }

    /**
     * 设置线条宽度
     * @param g2d
     * @param w 宽度
     */
    public static void setStrokeWidth(Graphics2D g2d, int w) {
        g2d.setStroke(
                new BasicStroke(
                        w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND
                )
        );
    }

    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);

    }

    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);

    }
}
