/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Graphical.header;

import Events.Event;
import Mvc.Controller.BestScoreController;
import Mvc.Controller.ButtonCloseController;
import Mvc.Controller.ButtonCustomController;
import Mvc.Controller.NewGameMenuController;
import Mvc.Model.Level;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author matt
 */
public class GraphicalDemineurMenu extends JMenuBar{

    public GraphicalDemineurMenu(Event ev) {
        super();
   
        JMenu menu1 = new JMenu("Game");
        menu1.setMnemonic(KeyEvent.VK_G);
        this.add(menu1);
        
        JMenu score = new JMenu("Score");
        score.setMnemonic(KeyEvent.VK_S);
        this.add(score);

        JMenu newMenu = new JMenu("New");
        menu1.add(newMenu);
        
        
        /*
         * Sous menu de Game 
         */
        
        JMenuItem newMenuItem1 = new JMenuItem("Beginner");
        newMenuItem1.addActionListener(new NewGameMenuController(ev.getSender(),Level.Beginner));        
        newMenuItem1.setMnemonic(KeyEvent.VK_B);
        newMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        newMenu.add(newMenuItem1);
        
        JMenuItem newMenuItem2 = new JMenuItem("Intermediate");
        newMenuItem2.addActionListener(new NewGameMenuController(ev.getSender(),Level.Intermediate));
        newMenuItem2.setMnemonic(KeyEvent.VK_I);
        newMenuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        newMenu.add(newMenuItem2);
        
        JMenuItem newMenuItem3 = new JMenuItem("Expert");
        newMenuItem3.addActionListener(new NewGameMenuController(ev.getSender(),Level.Expert));
        newMenuItem3.setMnemonic(KeyEvent.VK_E);
        newMenuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        newMenu.add(newMenuItem3);
        
        JMenuItem newMenuItem4 = new JMenuItem("Custom");
        newMenuItem4.addActionListener(new ButtonCustomController());
        newMenuItem1.setMnemonic(KeyEvent.VK_C);
        newMenuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        newMenu.add(newMenuItem4);
        
        JMenuItem menu2 = new JMenuItem("Quit");
        menu2.setMnemonic(KeyEvent.VK_Q);
        menu2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menu2.addActionListener(new ButtonCloseController());    
        menu1.add(menu2);
        
        
        /*
         * Sous menu de Score
         */
        
        JMenuItem scoreMenuItem1 = new JMenuItem("Beginner");
        scoreMenuItem1.addActionListener(new BestScoreController(ev.getSender(), Level.Beginner));
        scoreMenuItem1.setMnemonic(KeyEvent.VK_B);
        score.add(scoreMenuItem1);
        
        JMenuItem scoreMenuItem2 = new JMenuItem("Intermediate");
        scoreMenuItem2.addActionListener(new BestScoreController(ev.getSender(), Level.Intermediate));
        scoreMenuItem2.setMnemonic(KeyEvent.VK_I);
        score.add(scoreMenuItem2);
        
        JMenuItem scoreMenuItem3 = new JMenuItem("Expert");
        scoreMenuItem3.addActionListener(new BestScoreController(ev.getSender(), Level.Expert));
        scoreMenuItem3.setMnemonic(KeyEvent.VK_E);
        score.add(scoreMenuItem3);
        
    }
    
}
