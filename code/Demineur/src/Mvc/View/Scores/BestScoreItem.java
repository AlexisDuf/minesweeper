/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Scores;

import Mvc.Model.Scores.Score;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class BestScoreItem extends JPanel {

    public BestScoreItem(Score score) {
        this.setLayout(new BorderLayout());
        JLabel name = new JLabel(score.getName()+ " : ");
        name.setIcon(new ImageIcon("./img/score.png"));
        JLabel time = new JLabel(score.getTime().getMinutes()+" min et "+score.getTime().getSecondes()+" secondes ");
        this.add(name,BorderLayout.WEST);
        this.add(time,BorderLayout.EAST);
    }
    
}
