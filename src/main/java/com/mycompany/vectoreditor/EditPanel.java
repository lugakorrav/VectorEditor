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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
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
    SpinnerNumberModel layerSpinnerModel;

    public EditPanel(final MainFrame parent) {
        super();
        this.parent = parent;
        BoxLayout editPanelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(editPanelLayout);
        setPreferredSize(new Dimension(134, 512));

        figureDefList = new DefaultListModel<String>();
        figureList = new JList<String>(figureDefList);
        figureList.setBorder(new LineBorder(Color.GRAY));
        JScrollPane scrollpane = new JScrollPane(figureList);
        scrollpane.setPreferredSize(new Dimension(128, 256));
        add(scrollpane);

        final JPanel editPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        editPanel.setLayout(flowLayout);
        editPanel.setPreferredSize(new Dimension(128, 128));
        add(editPanel);

        final JButton colorButton = new JButton("Color");
        colorButton.setEnabled(false);
        colorButton.setPreferredSize(parent.getButtonDimension());
        colorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setFigureColor();
            }
        });
        editPanel.add(colorButton, BorderLayout.NORTH);

        JPanel strokeSet = new JPanel();
        final JSpinner strokeSpinner = new JSpinner();
        strokeSpinner.setPreferredSize(parent.getButtonDimension());
        strokeSpinner.setEnabled(false);
        strokeSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        strokeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                parent.setFigureStroke(new BasicStroke((int) strokeSpinner.getValue()));
            }
        });
        JLabel strokeLabel = new JLabel("thickness");
        GridLayout strokeSetLayout = new GridLayout(2, 1, 0, 0);
        strokeSet.setLayout(strokeSetLayout);
        strokeSet.add(strokeSpinner);
        strokeSet.add(strokeLabel);
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

        JPanel layerPanel = new JPanel();
        layerSpinnerModel = new SpinnerNumberModel(-1, -1, -1, 1);
        final JSpinner layerSpinner = new JSpinner(layerSpinnerModel);
        layerSpinner.setPreferredSize(parent.getButtonDimension());
        layerSpinner.setEnabled(false);
        layerSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = figureList.getSelectedIndex();
                if (selectedIndex != -1) {
                    int newIndex = (int) layerSpinner.getValue();
                    parent.changeLayer(selectedIndex, newIndex);
                    rewriteList();
                    figureList.setSelectedIndex(newIndex);
                }
            }
        });
        JLabel layerLabel = new JLabel("layer");
        GridLayout layerSetLayout = new GridLayout(2, 1, 0, 0);
        layerPanel.setLayout(layerSetLayout);
        layerPanel.add(layerSpinner);
        layerPanel.add(layerLabel);
        editPanel.add(layerPanel, BorderLayout.SOUTH);

        final JButton deleteButton = new JButton("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.deleteSelectedFigure(figureList.getSelectedIndex());
                figureDefList.remove(figureList.getSelectedIndex());
                int maximim = (int) layerSpinnerModel.getMaximum();
                layerSpinnerModel.setMaximum(maximim - 1);
                if (maximim == -1) {
                    layerSpinnerModel.setMinimum(-1);
                    layerSpinnerModel.setValue(-1);
                }
            }
        });
        editPanel.add(deleteButton);

        figureList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    parent.setSelectedFigure(figureList.getSelectedIndex());
                } catch (CloneNotSupportedException ex) {
                }
                int selectedIndex = figureList.getSelectedIndex();
                if (selectedIndex != -1) {
                    parent.editFigure();
                    colorButton.setEnabled(true);
                    strokeSpinner.setEnabled(true);
                    checkBox.setEnabled(true);
                    deleteButton.setEnabled(true);
                    layerSpinner.setEnabled(true);

                    Figure selected = parent.getSelectedFigure();
                    strokeSpinner.setValue((int) selected.getStroke().getLineWidth());
                    checkBox.setSelected(selected.isFilled());
                    if ((int) layerSpinner.getValue() != figureList.getSelectedIndex()) {
                        layerSpinner.setValue(selectedIndex);
                    }
                } else {
                    colorButton.setEnabled(false);
                    strokeSpinner.setEnabled(false);
                    strokeSpinner.setValue(1);
                    checkBox.setEnabled(false);
                    deleteButton.setEnabled(false);
                    layerSpinner.setEnabled(false);
                }
            }
        });

        figureList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                parent.removeCreatingAdapters();
            }
        });

    }

    public void rewriteList() {
        figureDefList.clear();
        for (Figure elem : parent.figures()) {
            figureDefList.addElement(elem.getType());
        }
    }

    public void addFigure() {
        figureDefList.addElement(parent.figures().getLast().getType());
        int maximum = (int) layerSpinnerModel.getMaximum();
        int minimum = (int) layerSpinnerModel.getMinimum();
        if (minimum == -1) {
            layerSpinnerModel.setMinimum(0);
        }
        layerSpinnerModel.setMaximum(maximum + 1);
    }

    public void clear() {
        figureDefList.clear();
        layerSpinnerModel.setMinimum(-1);
        layerSpinnerModel.setMaximum(-1);
        layerSpinnerModel.setValue(-1);
    }

    public void clearSelection() {
        figureList.clearSelection();
    }

}
