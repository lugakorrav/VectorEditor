/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

/**
 *
 * @author Григорий
 */
public class MenuBar extends JMenuBar {

    final MainFrame parent;

    MenuBar(final MainFrame parent) {
        super();
        this.parent = parent;
        JMenu file = new JMenu("File");
        JMenuItem saveAs = new JMenuItem("Save as");
        JMenuItem open = new JMenuItem("Open");
        add(file);
        file.add(saveAs);
        file.add(open);

        saveAs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    parent.saveAs();
                } catch (FileNotFoundException ex) {

                }
            }
        });
    }
}
