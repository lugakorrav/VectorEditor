/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor.primitives;

import com.mycompany.vectoreditor.Figure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Гриха
 */
public class Line extends Figure {

    public Line(int beginX, int beginY, int endX, int endY, Color color) {
        super(beginX, beginY, endX, endY, color);
    }

    public Line() {
        super();
    }

    @Override
    public Line clone() throws CloneNotSupportedException {
        return (Line) super.clone();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(stroke);

        g2d.drawLine(beginX, beginY, endX, endY);
    }
}
