/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.CustomeFrame;

import javax.swing.JRadioButton;

/**
 *
 * @author alexisdufour
 */
public class RadioButtonCustomGame extends JRadioButton{
    
    private RadioButtonState state;
    
    public RadioButtonCustomGame(String text, RadioButtonState state) {
        super(text);
        this.state = state;
    }

    public RadioButtonState getState() {
        return state;
    }
    
    
}
