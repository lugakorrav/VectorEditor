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
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Гриха
 */
public class CreatePanel extends JPanel {

    private MainFrame parent;

    public CreatePanel(MainFrame aparent) {

        super();
        parent = aparent;
        setLayout(new FlowLayout(FlowLayout.LEFT));

        RectButton rectButton = new RectButton(parent);
        OvalButton ovalButton = new OvalButton(parent);
        LineButton lineButton = new LineButton(parent);
        add(rectButton);
        add(ovalButton);
        add(lineButton);

        JButton colorButton = new JButton("Color");
        colorButton.setPreferredSize(parent.getButtonDimension());
        colorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setMainColor();
            }
        });
        add(colorButton);

        JButton backgroundButton = new JButton("Background");
        backgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setBackgroundColor();
            }
        });
        add(backgroundButton);

        JPanel strokeSet = new JPanel();
        final JSpinner strokeSpinner = new JSpinner();
        strokeSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        strokeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                parent.setStroke(new BasicStroke((int) strokeSpinner.getValue()));
            }
        });
        JLabel strokeLabel = new JLabel("thickness");
        BoxLayout strokeSetLayout = new BoxLayout(strokeSet, BoxLayout.Y_AXIS);
        strokeSet.setLayout(strokeSetLayout);
        strokeSet.add(strokeSpinner);
        strokeSet.add(strokeLabel);
        add(strokeSet);

        JPanel fillSet = new JPanel();
        final JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    parent.setFilled(Boolean.TRUE);
                } else {
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

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(parent.getButtonDimension());
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.clear();
                parent.removeCreatingAdapters();
                parent.removeEditingAdapters();
            }
        });
        add(clearButton);

    }
}
