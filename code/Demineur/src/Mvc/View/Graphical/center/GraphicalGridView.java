/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Graphical.center;

import Events.ChangeCaseEvent;
import Events.Event;
import Mvc.Controller.CellClicDemineurListenner;
import Mvc.Model.Cell.DemineurCell;
import Mvc.Model.Cell.DemineurState;
import static Mvc.Model.Cell.DemineurState.BombCell;
import static Mvc.Model.Cell.DemineurState.BombNeighboorCell;
import static Mvc.Model.Cell.DemineurState.WithoutBombNeighboorCell;
import Mvc.Model.Cell.MarkDemineurState;
import static Mvc.Model.Cell.MarkDemineurState.AnyMarking;
import static Mvc.Model.Cell.MarkDemineurState.BombMark;
import static Mvc.Model.Cell.MarkDemineurState.UndecidedMark;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Delmaire & Dufour
 */

public class GraphicalGridView extends JPanel{

    public static final String markingBombCase = "!";
    public static final String undecidedCase = "?";
    public static final String bombCharacter = "x";
    public static final String bombCharacterDebug = "b";
    private GraphicalCellView[][] cells;
    private int numberRows;
    private int numberCols;
    
    /**
     * 
     * @param ev 
     */

    public GraphicalGridView(Event ev) {
        createView(ev);
    }

    /**
     * 
     * @param ev
     * @return 
     */
    public final boolean createView(Event ev) {
        this.setLayout(new GridLayout(ev.getSender().getNumberOfRows(), ev.getSender().getNumberOfCols(), 5, 5));
        this.setBackground(Color.WHITE);
        this.cells = new GraphicalCellView[ev.getSender().getNumberOfRows()][ev.getSender().getNumberOfCols()];
        for (int i = 0; i < ev.getSender().getNumberOfRows(); i++) {
            for (int j = 0; j < ev.getSender().getNumberOfCols(); j++) {
                this.cells[i][j] = new GraphicalCellView(""); // Création des boutons
                this.cells[i][j].addMouseListener(new CellClicDemineurListenner(ev.getSender(), i, j)); // On ajoute l'écouteur sur le bouton
                this.add(this.cells[i][j]); // On les ajoute à l'interface graphique
            }
        }
        this.numberRows = ev.getSender().getNumberOfRows();
        this.numberCols = ev.getSender().getNumberOfCols();
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    public boolean removeView(Event ev) {
        return true;
    }
    
    /**
     * 
     * @param cce
     * @return 
     */
    public boolean refreshCase(ChangeCaseEvent cce) {
        if (cce.getCell().isReveal()) {
            switch (cce.getCell().getState()) {
                case BombCell: // Une bombe a été révélé
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setBackground(Color.red);
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setText(""); // On enlève le texte qui pouvait être présent
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setIcon(new ImageIcon("./img/bomb.png"));
                    break;
                case WithoutBombNeighboorCell:
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setIcon(null);
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setText(" ");
                    break;
                case BombNeighboorCell:
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setIcon(null);
                    /*Color cellColor = getColorOfCellWithBombNeighboors(cce.getCell());
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setForeground(cellColor);*/
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setText(cce.getCell().getNumberOfBombsNeighboors().toString());
                    
                    break;
            }
            this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setEnabled(false);
            return true;
        } else {
            switch (cce.getCell().getMarkState()) {
                case AnyMarking: 
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setIcon(null);
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setText(" ");
                    break;
                case BombMark:
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setText("");
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setIcon(new ImageIcon("./img/!.png"));
                    break;
                case UndecidedMark:
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setText("");
                    this.cells[cce.getCell().getRowIndex()][cce.getCell().getColIndex()].setIcon(new ImageIcon("./img/?.png"));
                    break;
            }
            return true;
        }
    }
    
    /**
     * 
     * @param dc
     * @return 
     */
    private Color getColorOfCellWithBombNeighboors(DemineurCell dc){
        int numberOfNeighboors = dc.getNumberOfBombsNeighboors();
        if(numberOfNeighboors==1){
            return Color.BLUE;
        }
        else if (numberOfNeighboors == 2){
            return Color.GREEN;
        }
        else if (numberOfNeighboors == 3){
            return Color.RED;
        }
        else if (numberOfNeighboors == 4){
            return Color.BLACK;
        }
        else if (numberOfNeighboors == 5){
            return Color.PINK;
        }
        else if (numberOfNeighboors == 6){
            return Color.MAGENTA;
        }
        else if (numberOfNeighboors == 7){
            return Color.yellow;
        }
        else if (numberOfNeighboors == 8){
            return Color.DARK_GRAY;
        }
        else{
            return null; // Changer en une exception
        }
    }
    
    public boolean refreshBomb(Event ev){
        for(int i = 0; i < this.numberRows; i++){
            for (int j = 0; j < this.numberCols; j++) {
                if(ev.getSender().getCell(i, j).getState() == DemineurState.BombNeighboorCell){
                    this.cells[i][j].setUI(new GraphicalCellUI(getColorOfCellWithBombNeighboors(ev.getSender().getCell(i, j))));
                }
            }
        }
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshWinGame(Event ev) {
        for (int i = 0; i < this.numberRows; i++) {
            for (int j = 0; j < this.numberCols; j++) {
                if (ev.getSender().getCell(i, j).getState()==DemineurState.BombCell) {
                    this.cells[i][j].setBackground(Color.green);
                    this.cells[i][j].setText("");
                    this.cells[i][j].setIcon(new ImageIcon("./img/bomb.png"));
                    this.cells[i][j].setEnabled(false);
                }
            }
        }
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshLooseGame(Event ev) {
        for (int i = 0; i < this.numberRows; i++) {
            for (int j = 0; j < this.numberCols; j++) {
                if (ev.getSender().getCell(i, j).getState()==DemineurState.BombCell) {
                    if(ev.getSender().getCell(i, j).getMarkState() == MarkDemineurState.BombMark ){
                        this.cells[i][j].setBackground(Color.green);
                    }else{
                        this.cells[i][j].setBackground(Color.red);
                    }
                    this.cells[i][j].setText("");
                    this.cells[i][j].setIcon(new ImageIcon("./img/bomb.png"));
                    this.cells[i][j].setEnabled(false);
                }else if (ev.getSender().getCell(i, j).getState()==DemineurState.BombNeighboorCell){
                    this.cells[i][j].setIcon(null);
                    this.cells[i][j].setText(ev.getSender().getCell(i,j).getNumberOfBombsNeighboors().toString());
                }else if(ev.getSender().getCell(i,j).getState()==DemineurState.WithoutBombNeighboorCell){
                    this.cells[i][j].setIcon(null);
                }
                this.cells[i][j].setEnabled(false);
            }
        }
        return true;
    }

    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshGrid(Event ev) {
        this.removeAll();
        this.setLayout(new GridLayout(ev.getSender().getNumberOfRows(), ev.getSender().getNumberOfCols(), 5, 5));
        this.createView(ev);
        return true;
    }
    
    /**
     * 
     * @param ev
     * @return 
     */
    public boolean refreshRemainingBomb(Event ev) {
        return true;
    }
    
    /**
     * 
     * @param rowIndex
     * @param colIndex
     * @return 
     */
    public GraphicalCellView getCellView(int rowIndex, int colIndex){
        return this.cells[rowIndex][colIndex];
    }
}