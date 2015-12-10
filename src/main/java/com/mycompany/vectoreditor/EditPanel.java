/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
        figureDefList = new DefaultListModel<String>();
        figureList = new JList<String>(figureDefList);
        figureList.setBorder(new LineBorder(Color.GRAY));
        scrollpane = new JScrollPane(figureList);
        scrollpane.setPreferredSize(new Dimension(128, 384));
        add(scrollpane);

        figureList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    parent.setSelectedFigure(figureList.getSelectedIndex());
                } catch (CloneNotSupportedException ex) {
                }
                if (figureList.getSelectedIndex() != -1) {
                    parent.editFigure();
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
