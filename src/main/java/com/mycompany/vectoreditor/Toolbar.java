/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.mycompany.vectoreditor.figurebuttons.OvalButton;
import com.mycompany.vectoreditor.figurebuttons.RectButton;

/**
 *
 * @author Гриха
 */
public class Toolbar extends JPanel {

    private MainFrame parent;

    public Toolbar(MainFrame aparent) {

        super();
        parent = aparent;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        RectButton rectButton = new RectButton(parent);
        OvalButton ovalButton = new OvalButton(parent);
        add(rectButton);
        add(ovalButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.clear();
            }
        });
        add(clearButton);
    }
}
