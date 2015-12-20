/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import com.mycompany.vectoreditor.primitives.Line;
import com.mycompany.vectoreditor.primitives.Oval;
import com.mycompany.vectoreditor.primitives.Rectangle;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Гриха
 */
public class MainFrame extends JFrame {

    private Canvas canvas;
    private CreatePanel createPanel;
    private EditPanel editPanel;
    private MenuBar menuBar;

    private ArrayList<Figure> figures;
    
    private Color mainColor;
    private BasicStroke mainStroke;
    private boolean filled;
    private Figure selectedFigure;
    private Dimension buttonDimension;
    String fileExtension;

    public MainFrame(String s) {
        super(s);
        mainColor = Color.BLACK;
        mainStroke = new BasicStroke(1);
        fileExtension = new String(".vdg");
        filled = false;
        buttonDimension = new Dimension(72, 24);

        figures = new ArrayList<Figure>();
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

    public ArrayList<Figure> figures() {
        return figures;
    }

    public void addFigure(Figure f) {
        figures.add(f);
        editPanel.addFigure();
    }

    public void createFigure(Figure f) {
        canvas.createFigure(f);
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

    public void setBackgroundColor() {
        Color color = JColorChooser.showDialog(null,
                "Choose the color", canvas.getBackground());
        if (color != null) {
            canvas.setBackground(color);
            canvas.repaint();
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

    public void deleteSelectedFigure() {
        figures.remove(selectedFigure);
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

    public void saveAs() throws FileNotFoundException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().endsWith(fileExtension);
            }

            @Override
            public String getDescription() {
                return fileExtension;
            }
        };
        fileChooser.setFileFilter(fileFilter);
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            String path = file.getAbsolutePath();
            if (!path.endsWith(fileExtension)) {
                file = new File(path + fileExtension);
            }
            PrintStream oStream = new PrintStream(file);
            oStream.print(canvas.getBackground().getRGB());
            oStream.print(" ");
            for (Figure elem : figures()) {
                elem.write(oStream);
            }
            oStream.close();
        }
    }

    public void open() throws FileNotFoundException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().endsWith(fileExtension);
            }

            @Override
            public String getDescription() {
                return fileExtension;
            }
        };
        fileChooser.setFileFilter(fileFilter);
        fileChooser.setFileFilter(fileFilter);
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            clear();
            removeCreatingAdapters();
            removeEditingAdapters();
            Scanner scanner = new Scanner(file);
            try {
                if (scanner.hasNext()) {
                    Color backGroundColor = new Color(scanner.nextInt());
                    canvas.setBackground(backGroundColor);
                }
                while (scanner.hasNext()) {
                    int srokeWidth = scanner.nextInt();
                    BasicStroke stroke = new BasicStroke(srokeWidth);
                    boolean filled = scanner.nextBoolean();
                    int beginX = scanner.nextInt();
                    int beginY = scanner.nextInt();
                    int endX = scanner.nextInt();
                    int endY = scanner.nextInt();
                    int rgb = scanner.nextInt();
                    Color color = new Color(rgb);

                    String type = new String();
                    type = scanner.next();
                    switch (type) {
                        case "Rectangle":
                            Rectangle rectangle = new Rectangle(beginX, beginY,
                                    endX, endY, color, stroke, filled);
                            figures.add(rectangle);
                            break;
                        case "Oval":
                            Oval oval = new Oval(beginX, beginY, endX, endY,
                                    color, stroke, filled);
                            figures.add(oval);
                            break;
                        case "Line":
                            Line line = new Line(beginX, beginY, endX, endY,
                                    color, stroke, filled);
                            figures.add(line);
                            break;
                    }
                    editPanel.addFigure();
                }
            } catch (InputMismatchException ex) {
                clear();
                canvas.setBackground(Color.WHITE);
                removeCreatingAdapters();
                removeEditingAdapters();
                JOptionPane.showMessageDialog(null, "Corrupted file");
                return;
            }
            scanner.close();
        }
    }

    public void removeInvisible() {
        Iterator<Figure> it = figures.iterator();
        while (it.hasNext()) {
            Figure elem = it.next();
            int beginX = elem.getBeginX();
            int beginY = elem.getBeginY();
            int endX = elem.getEndX();
            int endY = elem.getEndY();
            Point p1 = new Point(beginX, beginY);
            Point p2 = new Point(endX, endY);
            Point p3 = new Point(beginX, endY);
            Point p4 = new Point(endX, beginY);

            if (!(canvas.contains(p1) || canvas.contains(p2)
                    || canvas.contains(p3) || canvas.contains(p4))) {
                it.remove();
                editPanel.rewriteList();
                canvas.repaint();
            }
        }
    }
}
