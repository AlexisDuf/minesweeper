/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author alexisdufour
 */
public class Chronometer {

    private Timer chrono;

    /**
     * Constructeur avec un delay de 1000ms et un controleur
     * @param al Controleur sur le timer
     */
    public Chronometer(ActionListener al){
        this.chrono = new Timer(1000,al);
        startChrono();
    }

    /**
     * lance le compteur de temps 
     */
    public final void startChrono() {
        this.chrono.start();
    }

    /**
     * stop le compteur de temps 
     */
    public void stopChrono() {
        chrono.stop();
    }
    
    /**
     * permet de connaitre l'etat d'activite du timer (lance ou non)
     * @return true ou false 
     */
    public boolean isRunning() {
        return chrono.isRunning();
    }

    /**
     * Retourn le chrono
     * @return 
     */
    public Timer getChrono() {
        return chrono;
    }
}
