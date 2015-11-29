/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor.primitives;

import java.awt.Color;
import java.awt.Graphics;
import com.mycompany.vectoreditor.Figure;
import java.awt.Graphics2D;

/**
 *
 * @author Гриха
 */
public class Oval extends Figure {

    public Oval(int beginX, int beginY, int endX, int endY, Color color) {
        super(beginX, beginY, endX, endY, color);
    }

    public Oval() {
        super();
    }

    @Override
    public Oval clone() throws CloneNotSupportedException {
        return (Oval) super.clone();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(stroke);
        int x, y;
        int width = endX - beginX;
        int height = endY - beginY;

        if (width < 0) {
            x = endX;
        } else {
            x = beginX;
        }
        if (height < 0) {
            y = endY;
        } else {
            y = beginY;
        }
        g2d.drawOval(x, y, Math.abs(width), Math.abs(height));
    }
}
