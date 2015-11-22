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
public class Oval extends Figure {

    public Oval(int ax, int ay, int awidth, int aheight, Color c) {
        super(ax, ay, awidth, aheight, c);
    }

    public Oval() {
        super();
    }
    
    @Override
    public Oval clone() throws CloneNotSupportedException {
        return (Oval)super.clone();
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
}
