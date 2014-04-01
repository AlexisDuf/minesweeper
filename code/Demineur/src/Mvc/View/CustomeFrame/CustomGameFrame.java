/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.CustomeFrame;

import Mvc.View.GraphicalDemineurView;
import Mvc.View.Console.ConsoleView;
import Mvc.Controller.ConsoleController;
import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.ArrayDemineurGrid;
import Mvc.Model.Level;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Delmaire & Dufour
 */
public class CustomGameFrame extends JFrame {
    private static CustomGameFrame instance =null;
    private static int xSize = 1300;
    private static int ySize = 1000;
    private static String NameOfWindow = "MineSweeper";
    private MouseListener validNewGame = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            AbstractDemineurModel model = new ArrayDemineurGrid(true, Level.Beginner); // Boolean pour le mode debug
            model.getNotificator().addView(model,new ConsoleView());
            model.getNotificator().addView(model,new GraphicalDemineurView(NameOfWindow,xSize,ySize,true,model.getNumberOfRows(),model.getNumberOfCols(),model));
            ConsoleController controller = new ConsoleController(model);
        }

        /**
         * 
         */
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };
    private MouseListener cancelNewGame = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };

    /**
     * 
     * @throws HeadlessException 
     */
    private CustomGameFrame() throws HeadlessException {
        super();
        setTitle("Custom Game");
        getContentPane().setLayout(new GridLayout(4, 1, 5, 5));
        setSize(xSize, ySize);
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(true);

        this.getContentPane().add(new NewGamePanel()); // Panel avec les RadioButton
        this.getContentPane().add(new CustomGamePanel()); // Panel avec les sliders

        /*
         * Création des deux boutons 
         */

        JButton validButton = new JButton("Ok");
        validButton.addMouseListener(validNewGame);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addMouseListener(cancelNewGame);

        getContentPane().add(validButton);
        getContentPane().add(cancelButton);

        this.setVisible(true);


    }
    
    /**
     * 
     * @return 
     */
    public static CustomGameFrame getInstace(){
        if(instance==null){
            instance = new CustomGameFrame();
        }
        return instance;
    }
}