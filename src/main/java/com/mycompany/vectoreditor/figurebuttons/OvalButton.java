/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor.figurebuttons;

import java.awt.Graphics;
import com.mycompany.vectoreditor.FigureButton;
import com.mycompany.vectoreditor.MainFrame;
import com.mycompany.vectoreditor.primitives.Oval;

/**
 *
 * @author Гриха
 */
public class OvalButton extends FigureButton {

    public OvalButton(MainFrame aparent) {
        super(aparent, new Oval());
    }

    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth()/2;
        int x = (getWidth() - width)/2;
        int height = getHeight()/2;
        int y = (getHeight()- height)/2;
        g.fillOval(x, y, width, height);
    }
}
