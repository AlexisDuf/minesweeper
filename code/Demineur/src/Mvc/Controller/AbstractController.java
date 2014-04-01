/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controller;

import Mvc.Model.AbstractDemineurModel;

/**
 *
 * @author Delmaire & Dufour
 */
public interface AbstractController {
    public void initializeListenners();
    
    /**
     * 
     * @return Retourne le modèle qu'il contrôle
     */
    public AbstractDemineurModel getModel();
}
