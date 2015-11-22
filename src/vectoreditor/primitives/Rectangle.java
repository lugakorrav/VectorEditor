/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectoreditor.primitives;

import java.awt.Color;
import java.awt.Graphics;
import vectoreditor.Figure;

/**
 *
 * @author Гриха
 */
public class Rectangle extends Figure {
    
    public Rectangle(int ax, int ay, int awidth, int aheight, Color c) {
        super(ax, ay, awidth, aheight, c);
    }
    
    public Rectangle() {
        super();
    }
    
    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return (Rectangle)super.clone();
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
