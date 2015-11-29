/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vectoreditor;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Гриха
 */
public class EditPanel extends JPanel {

    public EditPanel() {
        super();
        JList<String> figureList = new JList<String>();
        figureList.setPreferredSize(new Dimension(128,384));
        figureList.setBorder(new LineBorder(Color.GRAY));
        add(figureList);
    }
    
}
