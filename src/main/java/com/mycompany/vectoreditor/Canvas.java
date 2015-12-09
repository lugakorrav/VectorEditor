/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 *
 * @author Гриха
 */
public class Canvas extends JPanel {

    private final MainFrame parent;

    private ArrayList<MouseAdapter> creatingMouseAdapters;
    private ArrayList<MouseMotionAdapter> creatingMouseMotionAdapters;

    private ArrayList<MouseAdapter> editingMouseAdapters;
    private ArrayList<MouseMotionAdapter> editingMouseMotionAdapters;

    public Canvas(MainFrame c) {
        super();
        parent = c;
        creatingMouseAdapters = new ArrayList<MouseAdapter>();
        creatingMouseMotionAdapters = new ArrayList<MouseMotionAdapter>();
        editingMouseAdapters = new ArrayList<MouseAdapter>();
        editingMouseMotionAdapters = new ArrayList<MouseMotionAdapter>();
        setBackground(Color.white);
        setBorder(new LineBorder(Color.GRAY));

        ActionListener timerListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Figure f = parent.getSelectedFigure();
                if (f != null) {
                    f.swapColors();
                    repaint();
                }
            }
        };

        Timer timer = new Timer(250, timerListener);
        timer.start();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!parent.figures().isEmpty()) {
            for (Figure elem : parent.figures()) {
                elem.paint(g);
            }
        }
        Figure selected = parent.getSelectedFigure();
        if (selected != null) {
            selected.paintBorder(g);
        }
    }

    public void removeCreatingAdapters() {
        for (MouseAdapter elem : creatingMouseAdapters) {
            removeMouseListener(elem);
        }
        for (MouseMotionAdapter elem : creatingMouseMotionAdapters) {
            removeMouseMotionListener(elem);
        }
        creatingMouseAdapters.clear();
        creatingMouseMotionAdapters.clear();
    }

    public void removeEditingAdapters() {
        for (MouseAdapter elem : editingMouseAdapters) {
            removeMouseListener(elem);
        }
        for (MouseMotionAdapter elem : editingMouseMotionAdapters) {
            removeMouseMotionListener(elem);
        }
        editingMouseAdapters.clear();
        editingMouseMotionAdapters.clear();
    }

    public void resizeFigure(final Figure f) {

        final MouseMotionAdapter dragAdapter = new MouseMotionAdapter() {

            int figureX = f.getX();
            int figureY = f.getY();

            @Override
            public void mouseDragged(MouseEvent e) {
                f.setEndX(e.getX());
                f.setEndY(e.getY());
                repaint();
            }
        };

        addMouseMotionListener(dragAdapter);
        creatingMouseMotionAdapters.add(dragAdapter);

        MouseAdapter releaseAdapter = new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                removeMouseMotionListener(dragAdapter);
                removeMouseListener(this);
            }
        };

        addMouseListener(releaseAdapter);
        creatingMouseAdapters.add(releaseAdapter);
    }

    public void createFigure(final Figure af) {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            Figure f;

            @Override
            public void mousePressed(MouseEvent e) {

                if ((e.getButton() == MouseEvent.BUTTON1) && (contains(e.getPoint()))) {
                    try {
                        f = af.clone();
                    } catch (CloneNotSupportedException exception) {
                    };
                }

                f.setColor(parent.getColor());
                f.setStroke(parent.getStroke());
                f.setFilled(parent.isFilled());

                f.setBeginX(e.getX());
                f.setBeginY(e.getY());
                f.setEndX(e.getX());
                f.setEndY(e.getY());

                parent.addFigure(f);

                repaint();

                resizeFigure(f);
            }

            public void mouseClicked(MouseEvent e) {

            }
        };
        addMouseListener(mouseAdapter);
        creatingMouseAdapters.add(mouseAdapter);
    }
}
