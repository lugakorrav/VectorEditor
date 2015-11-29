/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Гриха
 */
public class ColorDialog extends JDialog {

    private MainFrame parent;
    private JColorChooser chooser;

    public ColorDialog(final MainFrame parent) {
        super();
        this.parent = parent;
        setLayout(new FlowLayout());
        setSize(new Dimension(600, 380));
        setResizable(false);
        setModal(true);

        chooser = new JColorChooser();
        add(chooser);

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(parent.GetButtonDimension());
        add(okButton);
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setColor(chooser.getColor());
                setVisible(false);
            }
        });
    }
}
