/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Graphical.footer;

import Events.Event;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Delmaire & Dufour
 */
public class BombStatutBarView extends JPanel {
    JLabel label;
    ImageIcon icon;

    /**
     * 
     * @param ev 
     */
    public BombStatutBarView(Event ev) {
        super();
        this.label=new JLabel("Remaining mines : "+ ev.getSender().getRemainingMines());
        this.icon = new ImageIcon("./img/bomb.png");
        this.label.setIcon(icon);
        this.add(this.label);
    }

    /**
     * 
     * @param ev
     * @return 
     */
    public boolean createView(Event ev) {
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("test"));
        return true;
    }
    
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshLooseGame(Event ev) {
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshRemainingBomb(Event ev) {
       this.label.setText("Remaining mines : "+ev.getSender().getRemainingMines());
       return true;
    }
    
}