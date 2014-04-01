/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Exceptions.BadModelException;
import Mvc.Model.AbstractDemineurModel;
import Tools.Time;

/**
 *
 * @author @author Delmaire & Dufour
 */
public class ChangeTimerEvent extends Event{
    
    private int minute;
    private int seconde;

    /**
     * Constructors 
     */
    
    public ChangeTimerEvent() {
    }

    /**
     *
     * @param _sender Celui qui est à l'origine de l'envoi de l'évènement
     * @param _functionName Fonction dans laquelle l'évènement a été envoyé
     * @param time Temps qui a été modifié
     * @throws BadModelException
     */
    public ChangeTimerEvent(AbstractDemineurModel _sender, String _functionName, Time time) throws BadModelException {
        super(_sender, _functionName);
        minute =  time.getMinutes();
        seconde = time.getSecondes();
    }

    /**
     *
     * @return Retourne le nombre de minutes du temps qui a changé
     */
    public int getMinute() {
        return minute;
    }
    

    /**
     *
     * @return Retourne le nombre de secondes du temps qui a changé
     */
    public int getSeconde() {
        return seconde;
    }    
    
}
