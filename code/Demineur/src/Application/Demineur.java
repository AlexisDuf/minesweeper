/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Mvc.Controller.AbstractController;
import Mvc.Controller.ConsoleController;
import Mvc.View.Console.ConsoleView;
import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.ArrayDemineurGrid;
import Mvc.Model.Level;
import Mvc.View.GraphicalDemineurView;
import Mvc.View.CustomeFrame.NewGameFrame;

/**
 *
 * @author @author Delmaire & Dufour
 */
public class Demineur {

    private static int xSize = 1300;
    private static int ySize = 1000;
    private static String NameOfWindow = "MineSweeper";
    private static AbstractDemineurModel currentModel;
    
    private class MyThread implements Runnable{
        private AbstractController controller;

        public MyThread(AbstractController controller) {
            this.controller = controller;
        }
         
        @Override
        public void run() {
            this.controller.initializeListenners();
        }
        
    };

    public static void main(String[] args) {
        if(args.length==0){
            NewGameFrame newGameFrame = NewGameFrame.getInstance();
        }else{
            Level level = Level.Custom;
            level.setNumberOfRows(Integer.valueOf(args[0]));
            level.setNumberOfCols(Integer.valueOf(args[0]));
            level.setNumberOfBombs(Integer.valueOf(args[0]));
            Demineur.setCurrentModel(new ArrayDemineurGrid(Boolean.valueOf(args[3]),level));
            
        }
        
    }

    /**
     *
     * @return Retourne le modèle couramment utilisé
     */
    public static AbstractDemineurModel getCurrentModel() {
        return currentModel;
    }
    
    

    /**
     *
     * @param _currentModel Le modèle couramment utilisé
     */
    public static void setCurrentModel(AbstractDemineurModel _currentModel) {
       
        currentModel = _currentModel;
        currentModel.getNotificator().addView(currentModel, new ConsoleView());
        currentModel.getNotificator().addView(currentModel, new GraphicalDemineurView(NameOfWindow, xSize, ySize, true, currentModel.getNumberOfRows(), currentModel.getNumberOfCols(), currentModel));
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("test");
                 ConsoleController console = new ConsoleController(currentModel);
            }
        }).start();
    }
    
    
}
