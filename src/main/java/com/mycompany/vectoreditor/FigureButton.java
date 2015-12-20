/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Гриха
 */
public class FigureButton extends JButton {

    protected MainFrame parent;

    protected FigureButton(MainFrame aparent, final Figure f) {
        super();
        parent = aparent;
        setPreferredSize(new Dimension(24, 24));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                parent.setMode(MainFrame.MODE.RECT);
                parent.removeCreatingAdapters();
                parent.removeEditingAdapters();
                parent.clearSelection();
                parent.createFigure(f);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

}
