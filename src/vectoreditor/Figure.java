/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectoreditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Гриха
 */
public class Figure implements  Cloneable {
    
    protected Color color;
    
    protected int x, y, width, height;
    
    public Figure(int ax, int ay, int awidth, int aheight, Color c) {
        x = ax;
        y = ay;
        width = awidth;
        height = aheight;
        color = c;
    }
    
    public Figure() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        color = Color.RED;
    }
    
    @Override
    public Figure clone() throws CloneNotSupportedException {
        return (Figure)super.clone();
    }
    
    public void paint(Graphics g) {};
    
    public void setX (int ax) {
        x = ax;
    }
    
    public void setY (int ay) {
        y = ay;
    }
    
    public void setColor (Color c) {
        color = c;
    }
    
    public void setWidth (int awidth) {
        width = awidth;
    }
    
    public void setHeight (int aheight) {
        height = aheight;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
