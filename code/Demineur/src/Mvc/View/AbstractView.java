/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View;
import Events.Event;
import Events.ChangeCaseEvent;
import Events.ChangeTimerEvent;
import java.util.Collection;

/**
 *
 * @author Delmaire & Dufour
 */
public interface AbstractView{
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean createView(Event ev);
    
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean removeView(Event ev);
    
    /**
     * 
     * @param cce
     * @return 
     */
    public boolean refreshCase(ChangeCaseEvent cce);
    
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshWinGame(Event ev);
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshLooseGame(Event ev);
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshGrid(Event ev);
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshRemainingBomb(Event ev);
    
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshTimer(ChangeTimerEvent ev);
    
    public boolean refreshBomb(Event ev);
}
