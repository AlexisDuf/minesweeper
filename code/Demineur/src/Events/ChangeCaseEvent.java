/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Exceptions.BadModelException;
import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.Cell.DemineurCell;

/**
 *
 * @author Delmaire & Dufour
 */

public class ChangeCaseEvent extends Event {
    private DemineurCell cell;
   /**
   * 
   * @throws BadModelException 
   */
    public ChangeCaseEvent() throws BadModelException {
    }

    /**
     * 
     * @param _sender Celui qui est à l'origine de l'envoi de l'évènement
     * @param _functionName Fonction dans laquelle l'évènement a été envoyé
     * @param _cell Cellule qui a été modifié
     * @throws BadModelException 
     */
    public ChangeCaseEvent(AbstractDemineurModel _sender, String _functionName, DemineurCell _cell) throws BadModelException {
        super(_sender, _functionName);
        this.cell = _cell;
    }
    
    /**
     * 
     * @return Retourne la cellule contenu dans l'évènement (cellule modifiée)
     */
    public DemineurCell getCell(){
        return this.cell;
    }
}
