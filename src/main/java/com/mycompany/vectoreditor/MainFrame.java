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
import java.awt.Paint;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author Гриха
 */
public class MainFrame extends JFrame {

    private Canvas canvas;
    private Toolbar toolbar;
    private EditPanel editPanel;
    private ColorDialog colorDialog;
    private Dimension buttonDimension;

    private LinkedList<Figure> figures;

    public enum MODE {

        NONE,
        RECT,
        OVAL;
    }

    MODE mode;

    private Color mainColor;
    private BasicStroke mainStroke;
    private boolean filled;

    public MainFrame(String s) {
        super(s);
        mode = MODE.NONE;
        mainColor = Color.BLACK;
        mainStroke = new BasicStroke(1);
        filled = false;
        buttonDimension = new Dimension(72, 24);

        figures = new LinkedList<Figure>();
        canvas = new Canvas(this);
        toolbar = new Toolbar(this);
        editPanel = new EditPanel(this);
        colorDialog = new ColorDialog(this);

        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        add(editPanel, BorderLayout.WEST);
    }

    public LinkedList<Figure> figures() {
        return figures;
    }

    public void addFigure(Figure f) {
        figures.addLast(f);
        editPanel.addFigure();
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

    public Color getColor() {
        return mainColor;
    }

    public void setColor(Color color) {
        this.mainColor = color;
    }

    public BasicStroke getStroke() {
        return mainStroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.mainStroke = stroke;
    }
    
    public Boolean isFilled() {
        return filled;
    }
    
    public void setFilled (Boolean b) {
        filled = b;
    }

    public Dimension GetButtonDimension() {
        return buttonDimension;
    }

    public void showColorFrame() {
        colorDialog.setVisible(true);
    }

    public void clear() {
        figures.clear();
        canvas.repaint();
    }

    public void removeAdapters() {
        canvas.removeAdapters();
    }

}
