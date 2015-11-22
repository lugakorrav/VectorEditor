/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectoreditor.figurebuttons;

import java.awt.Graphics;
import vectoreditor.FigureButton;
import vectoreditor.MainFrame;
import vectoreditor.primitives.Oval;

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
        g.fillOval(4, 4, 8, 8);
    }
}
