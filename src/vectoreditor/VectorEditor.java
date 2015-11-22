/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectoreditor;

import javax.swing.SwingUtilities;

/**
 *
 * @author Григорий
 */
public class VectorEditor {
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable () {
            @Override
            public void run() {
                new MainFrame("Vector Editor");
            }
        });
    }
}
