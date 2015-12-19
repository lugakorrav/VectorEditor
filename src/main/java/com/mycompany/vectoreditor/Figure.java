/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Гриха
 */
public class Figure implements Cloneable {

    protected String type;
    protected Color color;
    protected BasicStroke stroke;
    protected boolean filled;
    protected boolean selected;

    static private int handleRadius = 8;
    static private int handleVisibleRadius = 8;
    static private Color dashColor = Color.BLUE;
    static private Color solidColor = Color.WHITE;
    static private Color handleFilledColor = Color.GREEN;
    static private Color handleBorderColor = Color.BLACK;

    protected int beginX, beginY, endX, endY;

    public Figure(int beginX, int beginY, int endX, int endY,
            Color color, BasicStroke stroke, boolean filled) {
        this.color = color;
        this.stroke = stroke;
        this.filled = filled;
        this.beginX = beginX;
        this.beginY = beginY;
        this.endX = endX;
        this.endY = endY;
    }

    public Figure() {
        this.color = Color.RED;
        this.beginX = 0;
        this.beginY = 0;
        this.endX = 0;
        this.endY = 0;
    }

    @Override
    public Figure clone() throws CloneNotSupportedException {
        return (Figure) super.clone();
    }

    protected void setPainter(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setPainter(g2d);
    }

    public void setBeginX(int beginX) {
        this.beginX = beginX;
    }

    public void setBeginY(int beginY) {
        this.beginY = beginY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public void setFilled(boolean b) {
        this.filled = b;
    }

    public void setSelected(boolean b) {
        this.selected = b;
    }

    public int getBeginX() {
        return beginX;
    }

    public int getBeginY() {
        return beginY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public String getType() {
        return type;
    }

    public int getHandleRadius() {
        return handleRadius;
    }

    public Color getColor() {
        return color;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public boolean isFilled() {
        return filled;
    }

    public void swapColors() {
        Color c = new Color(dashColor.getRGB());
        dashColor = new Color(solidColor.getRGB());
        solidColor = new Color(c.getRGB());
    }

    public boolean cornerHandleContains(int x, int y) {
        int dx = Math.abs(x - endX);
        int dy = Math.abs(y - endY);
        return (Math.pow(dx, 2) + Math.pow(dy, 2) < Math.pow(handleRadius, 2));
    }

    public boolean centerHandleContains(int x, int y) {
        int dx = Math.abs(x - (endX + beginX) / 2);
        int dy = Math.abs(y - (endY + beginY) / 2);
        return (Math.pow(dx, 2) + Math.pow(dy, 2) < Math.pow(handleRadius, 2));
    }

    public void paintBorder(Graphics g) {
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

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke());
        g2d.setColor(solidColor);
        g2d.drawRect(x, y, Math.abs(width), Math.abs(height));
        g2d.setColor(dashColor);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 1, new float[]{8}, 0));
        g2d.drawRect(x, y, Math.abs(width), Math.abs(height));

        g2d.setStroke(new BasicStroke());
        g2d.setColor(handleFilledColor);
        int offset = handleVisibleRadius / 2;
        g2d.fillOval(endX - offset, endY - offset, handleVisibleRadius, handleVisibleRadius);
        g2d.fillOval((endX + beginX) / 2 - offset,
                (endY + beginY) / 2 - offset, handleVisibleRadius, handleVisibleRadius);
        g2d.setColor(handleBorderColor);
        g2d.drawOval(endX - offset, endY - offset, handleVisibleRadius, handleVisibleRadius);
        g2d.drawOval((endX + beginX) / 2 - offset,
                (endY + beginY) / 2 - offset, handleVisibleRadius, handleVisibleRadius);
    }

    public void write(PrintStream oStream) {
        oStream.print((int)stroke.getLineWidth());
        oStream.print(" ");
        oStream.print(filled);
        oStream.print(" ");
        oStream.print(beginX);
        oStream.print(" ");
        oStream.print(beginY);
        oStream.print(" ");
        oStream.print(endX);
        oStream.print(" ");
        oStream.print(endY);
        oStream.print(" ");
        oStream.print(color.getRGB());
        oStream.print(" ");
        oStream.print(type);
        oStream.print(" ");
    }
}
