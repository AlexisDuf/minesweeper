/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.CustomeFrame;

import Application.Demineur;
import Mvc.Model.ArrayDemineurGrid;
import Mvc.Model.Level;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Delmaire & Dufour
 */
public class NewGameFrame extends JFrame  {
    
    private static NewGameFrame gameFrame = null;
    private static int xSize = 400;
    private static int ySize = 400;
    private boolean resizable = true;
    private boolean firstShow = true;
    private NewGamePanel newGamePanel;
    private CustomGamePanel customGamePanel;
    
    private NewGameFrame() {
        setTitle("Custom Game");
        getContentPane().setLayout(new BorderLayout());
        setSize(xSize,ySize); 
        setLocationRelativeTo(null); //we center the window
	setResizable(resizable);
        
        newGamePanel = new NewGamePanel();
        customGamePanel = new CustomGamePanel();
        
	getContentPane().add(newGamePanel, BorderLayout.NORTH); // Add on window
        getContentPane().add(customGamePanel,BorderLayout.CENTER); // Add on window
        getContentPane().add(new ValidPanel(new ActionListener(){

            /*
             * Action lorsque le boutton valider est cliqué
             */
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                switch(newGamePanel.currentState){
                    case Custom:
                        Level level = Level.Custom;
                        level.setNumberOfRows(customGamePanel.row.getCurrentNumber());
                        level.setNumberOfCols(customGamePanel.col.getCurrentNumber());
                        level.setNumberOfBombs(customGamePanel.mine.getCurrentNumber());

                        if(Demineur.getCurrentModel()==null){
                            Demineur.setCurrentModel(new ArrayDemineurGrid(true,level));
                        }else{
                            Demineur.getCurrentModel().startGame(true, level);  
                        }
                        break;
                    case Beginner:
                        if(Demineur.getCurrentModel()==null){
                            Demineur.setCurrentModel(new ArrayDemineurGrid(true,Level.Beginner));
                        }else{
                            Demineur.getCurrentModel().startGame(true, Level.Beginner);
                        }
                        break;
                    case Intermediate:
                        if(Demineur.getCurrentModel()==null){
                            Demineur.setCurrentModel(new ArrayDemineurGrid(true, Level.Intermediate));
                        }else{
                            Demineur.getCurrentModel().startGame(true, Level.Intermediate);
                        }
                        break;
                    case Expert: 
                        if(Demineur.getCurrentModel()==null){
                            Demineur.setCurrentModel(new ArrayDemineurGrid(true, Level.Expert));
                        }else{
                            Demineur.getCurrentModel().startGame(true, Level.Expert);
                        }
                        break;
                }
                
                
                setVisible(false);
            }
            
        },new ActionListener(){

            /*
             * Action lorsque le boutton annuler est cliqué
             */
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstShow){ // Si aucune partie n'a encore été lancée on ferme l'application
                    System.exit(0);
                }else{ // Sinon on ferme juste la fenêtre custom
                    setVisible(false); 
                }
            }
            
        }),BorderLayout.SOUTH);
        this.setVisible(true);
        firstShow=false;  
    }   
    
    /**
     * 
     * @return 
     */
    public static NewGameFrame getInstance(){
        if (gameFrame== null) {
            gameFrame = new NewGameFrame();
        }
        return gameFrame;
    }
    
}
