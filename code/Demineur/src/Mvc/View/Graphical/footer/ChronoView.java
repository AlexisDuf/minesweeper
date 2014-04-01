/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Graphical.footer;

import Events.ChangeTimerEvent;
import Events.Event;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alexisdufour
 */
public class ChronoView extends JPanel {

    protected JLabel viewTime;

    public ChronoView(Event ev) {
        int minutes = ev.getSender().getTimer().getMinutes();
        int secondes = ev.getSender().getTimer().getSecondes();
        String content=" ";
        if(minutes<10){
            content += "0"+minutes;
        }
        content+=":";
        if(secondes < 10){
            content += "0"+secondes;
        }
        
        viewTime = new JLabel(content);
        viewTime.setIcon(new ImageIcon("./img/time.png"));
        this.add(viewTime);
    }

    public boolean refreshTimer(ChangeTimerEvent ev) {
        String minute;
        String seconde;
        if (ev.getMinute() < 10) {
            minute = " 0" + ev.getMinute();
        } else {
            minute = " " + ev.getMinute();
        }

        if (ev.getSeconde() < 10) {
            seconde = "0" + ev.getSeconde();
        } else {
            seconde = "" + ev.getSeconde();
        }
        viewTime.setText(minute + ":" + seconde);
        return true;
    }
}
