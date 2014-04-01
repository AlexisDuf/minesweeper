/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Scores;

import Application.Demineur;
import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.Scores.Score;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author matt
 */
public class NewBestScoreFrame extends JFrame{
    
    
    public NewBestScoreFrame(String message) throws HeadlessException{
        super();
        this.setLayout(new BorderLayout());
        setTitle("Nouveau meilleur score");
        setSize(800, 100);  
        setLocationRelativeTo(null);
        setResizable(false);
        initPanel(message);
        this.setVisible(true);
    }
    
    private void initPanel(String message){
        final NewBestScoreFrame newthis = this;
        
        JLabel label = new JLabel(message);
        final JTextField textField = new JTextField("Entrez votre prénom");
        
        JButton button = new JButton("Enregistrer");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractDemineurModel currentModel = Demineur.getCurrentModel();
                currentModel.getBestScores(currentModel.getCurrentLevel()).addScore(new Score(textField.getText(), currentModel.getTimer() ));
                newthis.dispose();
            }
        });
        
        this.getContentPane().add(label, BorderLayout.NORTH);
        this.getContentPane().add(textField, BorderLayout.CENTER);
        this.getContentPane().add(button, BorderLayout.SOUTH);
        
    }
    
}
