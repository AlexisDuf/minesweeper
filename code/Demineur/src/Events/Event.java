/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;
import Mvc.Model.AbstractDemineurModel;
import Exceptions.BadModelException;

/**
 *
 * @author Delmaire & Dufour
 */
public class Event {
    private AbstractDemineurModel sender;
    private String functionName;
    
    /**
     * 
     * @throws BadModelException 
     */
    public Event() throws BadModelException{
        this(null,"Unknown");
    }
    
    /**
     * 
     * @param _sender Celui qui est à l'origine de l'envoi de l'évènement
     * @param _functionName Fonction dans laquelle l'évènement a été envoyé
     * @throws BadModelException 
     */
    public Event(AbstractDemineurModel _sender,String _functionName) throws BadModelException{
        if(_sender == null){
            throw new BadModelException("Aucun sender renseigné","Event()");
        }else{
            this.sender=_sender;
            this.functionName=_functionName;
        }
    }
    
    /**
     *
     * @return Retourne l'instance qui est à l'origine de l'envoi de l'évènement
     */
    public final AbstractDemineurModel getSender(){
        return this.sender;
    }
    
    
    /**
     *
     * @return Retourne le nom de la fonction d'où l'évènement a été envoyé
     */
    public final String getFunctionName(){
        return this.functionName;
    }
    
    
    
    
}
