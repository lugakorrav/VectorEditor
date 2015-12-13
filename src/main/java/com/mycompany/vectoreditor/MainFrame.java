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
    private Figure selectedFigure;
    private Dimension buttonDimension;

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

        setSize(800, 600);
        setMinimumSize(new Dimension(400, 400));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

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

    public Color getMainColor() {
        return mainColor;
    }

    public void setMainColor() {
        Color color = JColorChooser.showDialog(null,
                "Choose the color", mainColor);
        if (color != null) {
            mainColor = color;
        }
    }

    public void setFigureColor() {
        Color color = JColorChooser.showDialog(null,
                "Choose the color", selectedFigure.getColor());
        if (color != null && selectedFigure != null) {
            selectedFigure.setColor(color);
            canvas.repaint();
        }
    }

    public void setFigureStroke(BasicStroke stroke) {
        if (selectedFigure != null) {
            selectedFigure.setStroke(stroke);
            canvas.repaint();
        }
    }

    public void setFigureFilled(boolean b) {
        if (selectedFigure != null) {
            selectedFigure.setFilled(b);
            canvas.repaint();
        }
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

    public void setFilled(Boolean b) {
        filled = b;
    }

    public Dimension GetButtonDimension() {
        return buttonDimension;
    }

    public void clear() {
        figures.clear();
        editPanel.clear();
        canvas.repaint();
    }

    public void deleteSelectedFigure(int index) {
        System.out.println(figures.remove(index));
        selectedFigure = null;
        canvas.repaint();
    }

    public void removeCreatingAdapters() {
        canvas.removeCreatingAdapters();
    }

    public void removeEditingAdapters() {
        canvas.removeEditingAdapters();
    }

    public void setSelectedFigure(int index) throws CloneNotSupportedException {
        if (selectedFigure != null) {
            selectedFigure.setSelected(false);
        }
        if (index == -1) {
            selectedFigure = null;
            return;
        }

        selectedFigure = figures.get(index);
        selectedFigure.setSelected(true);
        System.out.println(index);
    }

    public void changeLayer(int oldLayer, int newLayer) {
        figures.add(newLayer, selectedFigure);
        figures.remove(oldLayer);
    }

    public Figure getSelectedFigure() {
        return selectedFigure;
    }

    public void clearSelection() {
        selectedFigure = null;
        editPanel.clearSelection();
        canvas.repaint();
    }

    public void editFigure() {
        canvas.editFigure();
    }

}
