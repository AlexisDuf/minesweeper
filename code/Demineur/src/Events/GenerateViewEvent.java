/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Exceptions.BadModelException;
import Mvc.Model.AbstractDemineurModel;


/*
 * Evenement lancé lorsqu'une vue est ajouté au model et doit se générer en fonction de ce dernier
 */

/**
 *
 * @author Delmaire & Dufour
 */
public class GenerateViewEvent extends Event {

    /**
     * 
     * @throws BadModelException 
     */
    public GenerateViewEvent() throws BadModelException {
    }

    /**
     * 
     * @param _sender Celui qui est à l'origine de l'envoi de l'évènement
     * @param _functionName Fonction dans laquelle l'évènement a été envoyé
     * @throws BadModelException 
     */
    public GenerateViewEvent(AbstractDemineurModel _sender, String _functionName) throws BadModelException {
        super(_sender, _functionName);
    }
    
}
