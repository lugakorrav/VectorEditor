/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor.primitives;

import com.mycompany.vectoreditor.Figure;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Гриха
 */
public class Line extends Figure {

    public Line(int beginX, int beginY, int endX, int endY,
            Color color, BasicStroke stroke, boolean filled) {
        super(beginX, beginY, endX, endY, color, stroke, filled);
        type = new String("Line");
    }

    public Line() {
        super();
        type = new String("Line");
    }

    @Override
    public Line clone() throws CloneNotSupportedException {
        return (Line) super.clone();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawLine(beginX, beginY, endX, endY);
    }
}
