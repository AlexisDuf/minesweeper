/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Scores;

import Mvc.Model.Scores.AbstractBestScores;
import Mvc.Model.Scores.Score;
import java.awt.GridLayout;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class BestScoreFramePanel extends JPanel {

    public BestScoreFramePanel(AbstractBestScores bs) {
        this.setLayout(new GridLayout(bs.numberOfScores,1,5,5));
        Iterator<Score> it = bs.iterator();
        Score currentScore;
        while(it.hasNext()){
            currentScore = it.next();
            this.add(new BestScoreItem(currentScore));
        }
    }
    
}
