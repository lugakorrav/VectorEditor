/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Гриха
 */
public class EditPanel extends JPanel {

    MainFrame parent;
    JList<String> figureList;
    DefaultListModel<String> figureDefList;
    JScrollPane scrollpane;

    public EditPanel(final MainFrame parent) {
        super();
        this.parent = parent;
        FlowLayout editPanelLayout = new FlowLayout(FlowLayout.LEFT, 4, 4);
        setLayout(editPanelLayout);
        setPreferredSize(new Dimension(134, 512));

        figureDefList = new DefaultListModel<String>();
        figureList = new JList<String>(figureDefList);
        figureList.setBorder(new LineBorder(Color.GRAY));
        scrollpane = new JScrollPane(figureList);
        scrollpane.setPreferredSize(new Dimension(128, 384));
        add(scrollpane);

        final JPanel editPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        editPanel.setLayout(borderLayout);
        add(editPanel);

        final JButton colorButton = new JButton("Color");
        colorButton.setEnabled(false);
        colorButton.setPreferredSize(parent.GetButtonDimension());
        colorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setFigureColor();
            }
        });
        editPanel.add(colorButton, BorderLayout.NORTH);

        JPanel strokeSet = new JPanel();
        final JSpinner strokeSpinner = new JSpinner();
        strokeSpinner.setEnabled(false);
        strokeSpinner.setSize(strokeSet.getMinimumSize());
        strokeSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        strokeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                parent.setFigureStroke(new BasicStroke((int)strokeSpinner.getValue()));
            }
        });
        JLabel strokeLabel = new JLabel("thickness");
        GridLayout strokeSetLayout = new GridLayout(2, 1, 0, 0);
        strokeSet.setLayout(strokeSetLayout);
        strokeSet.add(strokeSpinner);
        strokeSet.add(strokeLabel);
        strokeSet.setAlignmentX(TOP_ALIGNMENT);
        editPanel.add(strokeSet, BorderLayout.SOUTH);

        final JCheckBox checkBox = new JCheckBox("filled");
        checkBox.setEnabled(false);
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    parent.setFigureFilled(true);
                } else {
                    parent.setFigureFilled(false);
                }
            }
        });
        editPanel.add(checkBox, BorderLayout.CENTER);

        figureList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    parent.setSelectedFigure(figureList.getSelectedIndex());
                } catch (CloneNotSupportedException ex) {
                }
                if (figureList.getSelectedIndex() != -1) {
                    parent.editFigure();
                    colorButton.setEnabled(true);
                    strokeSpinner.setEnabled(true);
                    checkBox.setEnabled(true);

                    Figure selected = parent.getSelectedFigure();
                    strokeSpinner.setValue((int) selected.getStroke().getLineWidth());
                    checkBox.setSelected(selected.isFilled());
                } else {
                    colorButton.setEnabled(false);
                    strokeSpinner.setEnabled(false);
                    strokeSpinner.setValue(1);
                    checkBox.setEnabled(false);
                }
            }
        });

        figureList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                parent.removeCreatingAdapters();
            }
        });

    }

    public void addFigure() {
        figureDefList.addElement(parent.figures().getLast().getType());
    }

    public void clear() {
        figureDefList.clear();
    }

    public void clearSelection() {
        figureList.clearSelection();
    }

}
