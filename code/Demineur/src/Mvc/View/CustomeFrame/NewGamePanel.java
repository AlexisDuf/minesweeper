/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.CustomeFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Delmaire & Dufour
 */
public class NewGamePanel extends JPanel{
    
    public ButtonGroup buttonGroup; 
    public RadioButtonCustomGame beginnerButton;
    public RadioButtonCustomGame intermediateButton;
    public RadioButtonCustomGame expertButton;
    public RadioButtonCustomGame customButton;
    public RadioButtonState currentState = RadioButtonState.Beginner;
    
    private class RadioButtonListener implements ActionListener{
        private RadioButtonState state;

        public RadioButtonListener(RadioButtonState _state) {
            this.state = _state;
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            setCurrentState(this.state);
        }
        
    }
            
    public NewGamePanel(){
        
        this.setLayout(new GridLayout(5,1));
        this.add(new JLabel("Select a level :"));
        buttonGroup = new ButtonGroup();
        
        /*
         * Création des ButtonRadio
         */
         beginnerButton = new RadioButtonCustomGame("Beginner: 10 mines in a 9x9 field", RadioButtonState.Beginner);
         beginnerButton.setSelected(true);
         beginnerButton.addActionListener(new RadioButtonListener(RadioButtonState.Beginner));
         buttonGroup.add(beginnerButton);
         this.add(beginnerButton);
         
         intermediateButton = new RadioButtonCustomGame("Intermediate: 40 mines in a 16x16 field", RadioButtonState.Intermediate);
         intermediateButton.addActionListener(new RadioButtonListener(RadioButtonState.Intermediate));
         buttonGroup.add(intermediateButton);
         this.add(intermediateButton);
         
         expertButton = new RadioButtonCustomGame("Expert: 99 mines in a 16x30 field", RadioButtonState.Expert);
         expertButton.addActionListener(new RadioButtonListener(RadioButtonState.Expert));
         buttonGroup.add(expertButton);
         this.add(expertButton);
         
         customButton = new RadioButtonCustomGame("Custom", RadioButtonState.Custom);
         customButton.addActionListener(new RadioButtonListener(RadioButtonState.Custom));
         buttonGroup.add(customButton);
         
  
         this.add(customButton);     
         
         
    }   
    
    public void setCurrentState(RadioButtonState state){
        currentState = state;
    }
    
}
