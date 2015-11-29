/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Гриха
 */
public class Canvas extends JPanel {

    private final MainFrame parent;

    private ArrayList<MouseAdapter> mouseAdapters;
    private ArrayList<MouseMotionAdapter> mouseMotionAdapters;

    public Canvas(MainFrame c) {
        super();
        parent = c;
        mouseAdapters = new ArrayList<MouseAdapter>();
        mouseMotionAdapters = new ArrayList<MouseMotionAdapter>();
        setBackground(Color.white);
        setBorder(new LineBorder(Color.GRAY));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!parent.figures().isEmpty()) {
            for (Figure elem : parent.figures()) {
                elem.paint(g);
            }
        }
    }

    public void removeAdapters() {
        for (MouseAdapter elem : mouseAdapters) {
            removeMouseListener(elem);
        }
        for (MouseMotionAdapter elem : mouseMotionAdapters) {
            removeMouseMotionListener(elem);
        }
        mouseAdapters.clear();
        mouseMotionAdapters.clear();
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
        mouseMotionAdapters.add(dragAdapter);

        MouseAdapter releaseAdapter = new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                removeMouseMotionListener(dragAdapter);
                removeMouseListener(this);
            }
        };

        addMouseListener(releaseAdapter);
        mouseAdapters.add(releaseAdapter);
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
        mouseAdapters.add(mouseAdapter);
    }
}
