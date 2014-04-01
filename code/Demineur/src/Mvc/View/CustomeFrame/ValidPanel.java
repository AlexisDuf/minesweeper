/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.CustomeFrame;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Delmaire & Dufour
 */
public class ValidPanel extends JPanel{
    private JButton validButton;
    private JButton cancelButton;

    /**
     * 
     * @param validAction
     * @param cancelAction 
     */
    public ValidPanel(ActionListener validAction, ActionListener cancelAction) {
        this.validButton = new JButton("Ok");
        this.cancelButton = new JButton("Cancel");
        this.setLayout(new GridLayout(2, 1));
        this.validButton.addActionListener(validAction);
        this.cancelButton.addActionListener(cancelAction);
        this.add(validButton);
        this.add(cancelButton);
    }
    
}
