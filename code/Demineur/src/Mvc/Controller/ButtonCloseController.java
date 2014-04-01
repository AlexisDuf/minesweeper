/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Delmaire & Dufour
 */
public class ButtonCloseController implements ActionListener {

    public ButtonCloseController() {
        super();
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    
    
}
