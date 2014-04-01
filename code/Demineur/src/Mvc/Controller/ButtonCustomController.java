/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controller;

import Mvc.View.CustomeFrame.NewGameFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Delmaire & Dufour
 */
public class ButtonCustomController implements ActionListener {

    public ButtonCustomController() {
        
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame custom = NewGameFrame.getInstance();
        custom.setVisible(true);
    }
    
    
}
