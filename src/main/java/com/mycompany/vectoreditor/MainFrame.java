/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.BorderLayout;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author Гриха
 */
public class MainFrame extends JFrame {

    private Canvas canvas;
    private Toolbar toolbar;

    private LinkedList<Figure> figures;

    public enum MODE {

        NONE,
        RECT,
        OVAL;
    }

    MODE mode;

    public MainFrame(String s) {
        super(s);
        mode = MODE.NONE;
        figures = new LinkedList<Figure>();
        canvas = new Canvas(this);
        toolbar = new Toolbar(this);
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
    }

    public LinkedList<Figure> figures() {
        return figures;
    }

    public void addFigure(Figure f) {
        figures.add(f);
    }

    public void createFigure(Figure f) {
        canvas.createFigure(f);
    }

    public MODE getMode() {
        return mode;
    }

    public void setMode(MODE m) {
        mode = m;
    }

    public void clear() {
        figures.clear();
        canvas.repaint();
    }
    
    public void removeAdapters() {
        canvas.removeAdapters();
    }

}
