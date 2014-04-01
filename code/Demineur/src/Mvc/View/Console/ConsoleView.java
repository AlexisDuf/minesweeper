/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Console;

import Events.ChangeCaseEvent;
import Events.ChangeTimerEvent;
import Events.Event;
import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.Cell.DemineurState;
import Mvc.View.AbstractView;

/**
 *
 * @author Delmaire & Dufour
 */
public class ConsoleView implements AbstractView {

    public static final String anyMarkingCase = "#";
    public static final String markingBombCase = "!";
    public static final String undecidedCase = "?";
    public static final String revealedCaseEmpty = ".";
    public static final String bombCharacter = "x";
    public static final String bombCharacterDebug = "b";

    public ConsoleView() {
    }

    /**
     * 
     * @param ev
     * @return 
     */
    @Override
    public boolean createView(Event ev) {
        String cellContent = "";
        String sOut = "";
        AbstractDemineurModel sender = ev.getSender();
        sOut += " ";
        for (int k = 0; k < sender.getNumberOfCols(); k++) {
            if (sender.getNumberOfCols() > 10) {
                sOut += "  " + k;
            }else{
                sOut += " " + k;
            }
            
        }
        sOut += System.lineSeparator();


        for (int i = 0; i < sender.getNumberOfRows(); i++) {
            sOut += i ; 
            if (i < 10) {
                sOut += " ";
            }           

            for (int j = 0; j < sender.getNumberOfCols(); j++) {
                if (ev.getSender().getCell(i, j).isReveal()) {
                    switch (ev.getSender().getCell(i, j).getState()) {
                        case BombCell:
                            cellContent=bombCharacter;
                            break;
                        case BombNeighboorCell:
                            cellContent = ev.getSender().getCell(i, j).getNumberOfBombsNeighboors().toString();
                            break;
                        case WithoutBombNeighboorCell:
                            cellContent = revealedCaseEmpty;
                            break;
                    }
                } else {
                    switch (ev.getSender().getCell(i, j).getMarkState()) {
                        case UndecidedMark:
                            cellContent= undecidedCase;
                            break;
                        case BombMark:
                            cellContent = markingBombCase;
                            break;
                        case AnyMarking:
                            if(ev.getSender().getDebugMode() && ev.getSender().getCell(i, j).getState() == DemineurState.BombCell){
                                cellContent = bombCharacterDebug;
                            }else{
                                cellContent = anyMarkingCase;
                            }
                            break;
                    }
                }
                if (sender.getNumberOfCols() > 10) {
                    sOut += " ";
                }
                if( j> 10){
                    sOut += " ";
                }
                sOut += cellContent + " ";
                
            }
            sOut += System.lineSeparator();
        }
        System.out.println(sOut);
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    @Override
    public boolean removeView(Event ev) {
        return true;
    }

    /**
     * 
     * @param cce
     * @return 
     */
    @Override
    public boolean refreshCase(ChangeCaseEvent cce) {
        createView(cce);
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    @Override
    public boolean refreshWinGame(Event ev) {
        System.out.println("Félicitations vous avez remporté la partie");
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    @Override
    public boolean refreshLooseGame(Event ev) {
        System.out.println("Vous avez sélectionné une bombe, la partie est perdue");
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    @Override
    public boolean refreshGrid(Event ev) {
        return createView(ev);
    }

    /**
     * 
     * @param ev
     * @return 
     */
    @Override
    public boolean refreshRemainingBomb(Event ev) {
        return true;
    }

    @Override
    public boolean refreshTimer(ChangeTimerEvent ev) {
        return true;
    }

    @Override
    public boolean refreshBomb(Event ev) {
        return true;
    }
}
