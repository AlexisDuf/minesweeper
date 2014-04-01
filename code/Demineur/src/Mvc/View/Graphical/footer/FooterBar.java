/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Graphical.footer;

import Events.ChangeTimerEvent;
import Events.Event;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author alexisdufour
 */
public class FooterBar extends JPanel {
    public BombStatutBarView bsbv;
    public ChronoView cv;

    public FooterBar(Event ev) {
        this.setLayout(new GridLayout(1, 2));
        this.bsbv = new BombStatutBarView(ev);
        this.cv = new ChronoView(ev);
        this.add(this.bsbv);
        this.add(this.cv);
    }
    
    public void refreshTimer(ChangeTimerEvent ev){
        this.cv.refreshTimer(ev);
    }
}
