/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Гриха
 */
public class Figure implements Cloneable {

    protected Color color;
    protected BasicStroke stroke;

    protected int beginX, beginY, endX, endY;

    public Figure(int beginX, int beginY, int endX, int endY, Color color) {
        this.beginX = beginX;
        this.beginY = beginY;
        this.endX = endX;
        this.endY = endY;

//        x = ax;
//        y = ay;
//        width = awidth;
//        height = aheight;
        this.color = color;
    }

    public Figure() {
        this.beginX = 0;
        this.beginY = 0;
        this.endX = 0;
        this.endY = 0;
        this.color = Color.RED;
    }

    @Override
    public Figure clone() throws CloneNotSupportedException {
        return (Figure) super.clone();
    }

    public void paint(Graphics g) {
    }

    ;
    
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

    public int getX() {
        return beginX;
    }

    public int getY() {
        return beginY;
    }
}
