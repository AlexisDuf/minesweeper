/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model;

import Events.ChangeCaseEvent;
import Events.ChangeTimerEvent;
import Events.Event;
import java.util.LinkedList;
import Exceptions.BadAbstractViewException;
import Mvc.View.AbstractView;

/**
 *
 * @author Delmaire & Dufour
 */
public class LinkedListModelNotificator{
    
    private LinkedList<AbstractView> views;
    private boolean viewsConnect=true;
    /**
     *
     */
    public LinkedListModelNotificator() {
        this.views = new LinkedList<>();
    }
    
    /**
     * 
     * @return true ou false si les vues sont connectées
     */
    public boolean viewsAreConnect(){
        return this.viewsConnect;
    }
    
    /**
     * 
     */
    public void connectViews(){
        this.viewsConnect=true;
    }
    
    public void disconnectViews(){
        this.viewsConnect=false;
    }

    /**
     * Ajoute une vue 
     * @param sender représente le model
     * @param av la vue à ajouter
     */    
    public void addView(AbstractDemineurModel sender, AbstractView av) {
        this.views.add(av);
        av.createView(new Event(sender,"ModelNotificator::addView(AbstractModel sender, AbstractView av)"));
    }

    /**
     * Supprime la vue choisis
     * @param sender
     * @param av 
     */
    public void removeView(AbstractDemineurModel sender,AbstractView av) {
       if(!this.views.remove(av)){
           throw new BadAbstractViewException();
       }else{
           av.removeView(new Event(sender,"ModelNotificator::removeView(AbstractModel sender, AbstractView av)"));
       }
    }

    /**
     * Supprime toutes les vues
     */
    public void removeViews() {
        for (int i = 0; i < views.size(); i++) {
            views.remove();
        }
    }
    
    /**
     * Rafraichissement dans le cas d'une changement d'état d'une cellule
     * @param cce 
     */
    public void firechangeCaseEvent(ChangeCaseEvent cce) {
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.refreshCase(cce);
            }
        }
    }


    /**
     * Rafraichissement dans le cas d'une fin de partie
     * @param ev 
     */
    public void fireCloseGameEvent(Event ev) {
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.removeView(ev);
            }
        }
    } 


    /**
     * Rafraichissement dans le cas d'une partie gagné
     * @param ev 
     */
    protected void fireWinGameEvent(Event ev) {
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.refreshWinGame(ev);
            }
        }
    }


    /**
     * Rafraichissement dans le cas d'une partie perdu
     * @param ev 
     */
    protected void fireLooseGameEvent(Event ev) {
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.refreshLooseGame(ev);
            }
        }
    }


    /**
     * Rafraichissement dans le cas où une nouvelle partie est lancée
     * @param ev 
     */
    protected void fireNewGameEvent(Event ev) {
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.refreshGrid(ev);
            }
        }
    }
    
    /**
     * Rafraichissement dans le cas où on a marqué une case comme étant une bombe
     * @param ev 
     */
    protected void fireChangeRemainingBomb(Event ev) {
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.refreshRemainingBomb(ev);
            }
        }
    }
    
    /**
     * Rafraichissement du chrono dès qu'il y a 1 seconde.
     * @param ev 
     */
    protected void fireChangeTimerCount(ChangeTimerEvent ev){        
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.refreshTimer(ev);
            }
        }
    }
    
    /**
     * Rafraichissement dans le cas de l'initialisation des bombes
     * @param ev 
     */
    protected void fireInitializeBombEvent(Event ev){
        if(this.viewsAreConnect()){
            for(AbstractView view : views){
                view.refreshBomb(ev);
            }
        }
    }
    
    
    
 
    
}
