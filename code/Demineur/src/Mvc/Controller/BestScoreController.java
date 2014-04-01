/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controller;

import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.Level;
import Mvc.Model.Scores.AbstractBestScores;
import Mvc.View.Scores.BestScoreFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author matt
 */
public class BestScoreController implements ActionListener {
    private AbstractDemineurModel model;
    private Level level;

    public BestScoreController(AbstractDemineurModel model, Level level) {
        this.model = model;
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractBestScores abs = this.model.getBestScores(this.level);
        BestScoreFrame bsf = new BestScoreFrame(abs);
    }
    
}
