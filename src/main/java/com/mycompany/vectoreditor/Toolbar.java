/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import com.mycompany.vectoreditor.figurebuttons.LineButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.mycompany.vectoreditor.figurebuttons.OvalButton;
import com.mycompany.vectoreditor.figurebuttons.RectButton;
import java.awt.BasicStroke;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        LineButton lineButton = new LineButton(parent);
        add(rectButton);
        add(ovalButton);
        add(lineButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(parent.GetButtonDimension());
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.clear();
                parent.removeCreatingAdapters();
                parent.removeEditingAdapters();
            }
        });
        add(clearButton);

        JButton colorButton = new JButton("Color");
        colorButton.setPreferredSize(parent.GetButtonDimension());
        colorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.showColorFrame();
            }
        });
        add(colorButton);

        JPanel strokeSet = new JPanel();
        final JSpinner strokeSpinner = new JSpinner();
        strokeSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        strokeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                parent.setStroke(new BasicStroke((int) strokeSpinner.getValue()));
            }
        });
        JLabel storokeLabel = new JLabel("thickness");
        BoxLayout strokeSetLayout = new BoxLayout(strokeSet, BoxLayout.Y_AXIS);
        strokeSet.setLayout(strokeSetLayout);
        strokeSet.add(strokeSpinner);
        strokeSet.add(storokeLabel);
        add(strokeSet);
        
        JPanel fillSet = new JPanel();
        final JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    parent.setFilled(Boolean.TRUE);
                }
                else {
                    parent.setFilled(Boolean.FALSE);
                }
            }
        });
        JLabel fillLabel = new JLabel("filled");
        BoxLayout fillSetLayout = new BoxLayout(fillSet, BoxLayout.Y_AXIS);
        fillSet.setLayout(fillSetLayout);
        fillSet.add(checkBox);
        fillSet.add(fillLabel);
        
        add(fillSet);
        
    }
}
