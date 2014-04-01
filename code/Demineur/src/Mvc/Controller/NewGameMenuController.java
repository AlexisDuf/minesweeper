/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controller;

import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author matt
 */
public class NewGameMenuController implements ActionListener {
    AbstractDemineurModel model;
    Level level;

    /**
     *
     * @param _model Model à controler
     * @param _level Niveau
     */
    public NewGameMenuController(AbstractDemineurModel _model, Level _level) {
        this.model = _model;
        this.level = _level;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.startGame(true, this.level);
    }
    
}
