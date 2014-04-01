/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Exceptions.BadModelException;
import Mvc.Model.AbstractDemineurModel;

/*
 * Evenement lancé lorsque la vue a été supprimé du modèle
 */

/**
 *
 * @author Delmaire & Dufour
 */
public class RemoveEvent extends Event {

    /**
     * 
     * @throws BadModelException 
     */
    public RemoveEvent() throws BadModelException {
    }

    /**
     * 
     * @param _sender Celui qui est à l'origine de l'envoi de l'évènement
     * @param _functionName Fonction dans laquelle l'évènement a été envoyé
     * @throws BadModelException 
     */
    public RemoveEvent(AbstractDemineurModel _sender, String _functionName) throws BadModelException {
        super(_sender, _functionName);
    }
    
}
