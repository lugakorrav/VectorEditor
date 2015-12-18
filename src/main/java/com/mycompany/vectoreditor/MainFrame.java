/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import com.mycompany.vectoreditor.primitives.Rectangle;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author Гриха
 */
public class MainFrame extends JFrame {

    private Canvas canvas;
    private CreatePanel createPanel;
    private EditPanel editPanel;
    private MenuBar menuBar;

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
        createPanel = new CreatePanel(this);
        editPanel = new EditPanel(this);
        menuBar = new MenuBar(this);

        setSize(800, 600);
        setMinimumSize(new Dimension(400, 400));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        setJMenuBar(menuBar);
        add(canvas, BorderLayout.CENTER);
        add(createPanel, BorderLayout.NORTH);
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

    public Dimension getButtonDimension() {
        return buttonDimension;
    }

    public void clear() {
        figures.clear();
        editPanel.clear();
        canvas.repaint();
    }

    public void deleteSelectedFigure(int index) {
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
    }

    public void changeLayer(int oldLayer, int newLayer) {
        figures.remove(oldLayer);
        figures.add(newLayer, selectedFigure);
        canvas.repaint();
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

    public void saveAs() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            FileOutputStream fout = new FileOutputStream(file);
            for (Figure elem : figures()) {
                elem.write(fout);
            }
        }
    }

    public void open() throws FileNotFoundException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            BufferedReader fin = new BufferedReader(new FileReader(file));
            while (true) {
                
                
                String type = new String();
                type = fin.readLine();
                switch (type) {
                    case "Rectangle":
                        Rectangle rectangle = new Rectangle
                }
            }
        }
    }

}
