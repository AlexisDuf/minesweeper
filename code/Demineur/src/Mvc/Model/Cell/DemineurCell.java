/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model.Cell;

import java.util.LinkedList;

/**
 *
 * @author Delmaire & Dufour
 */
public class DemineurCell{
    private LinkedList<DemineurCell> neighboors;
    private Integer numberOfBombsNeighboors;
    private boolean reveal;
    private int rowIndex;
    private int colIndex;
    private DemineurState state;
    private MarkDemineurState markState;
    
    /**
     * Constructeur
     * @param _rowIndex indice de la cellule
     * @param _colIndex indice de la cellule
     */
    public DemineurCell(int _rowIndex, int _colIndex){
        this(_rowIndex,_colIndex,false,0);
        
    }
    
    /**
     * Constructeur
     * @param _rowIndex indice de la cellule
     * @param _colIndex indice de la cellule
     * @param _reveal révéler ou pas révéler
     * @param _numberOfBombsNeighboors nombre de voisins ayant une bombe
     */
    public DemineurCell(int _rowIndex, int _colIndex, boolean _reveal,Integer _numberOfBombsNeighboors){
        super();
        this.reveal=_reveal;
        this.numberOfBombsNeighboors=_numberOfBombsNeighboors;
        this.colIndex = _colIndex;
        this.rowIndex = _rowIndex;
        this.neighboors = new LinkedList<>();
        this.state = DemineurState.WithoutBombNeighboorCell;
        this.markState = MarkDemineurState.AnyMarking;
    }
    
    /**
     * Permet de mettre un états sur la celulle.
     * @param _state BombCell, WithoutBombNeighboorCell ou BombNeighboorCell
     */
    public void setState(DemineurState _state){
        this.state = _state;
        if(_state==DemineurState.BombCell){
            updateNeighboors();
        }
    }
    
    /**
     * Mettre le nombre de voisin comportant une bombe
     * @param _neighboors 
     */
    public void setNeighboors(LinkedList<DemineurCell> _neighboors){
        this.neighboors = _neighboors;
    }
    
    /**
     * 
     * @return le nombre de voisin ayant une bombe.
     */
    public Integer getNumberOfBombsNeighboors(){
        return this.numberOfBombsNeighboors;
    }
    
    /**
     * 
     * @return 
     */
    public int getRowIndex(){
        return this.rowIndex;
    }
    
    /**
     * 
     * @return 
     */
    public int getColIndex(){
        return this.colIndex;
    }

    /**
     * 
     * @param _reveal 
     */
    public void setReveal(boolean _reveal) {
        this.reveal=_reveal;
    }

    /**
     * 
     * @return 
     */
    public boolean isReveal() {
        return this.reveal;
    }

    /**
     * Incrémente numberOfBombsNeighboors si il y a d'autre voisins possédant une bombe
     */
    public void incrementNumberOfBombsNeighboors() {
        this.numberOfBombsNeighboors++;
        System.out.println(this.getNumberOfBombsNeighboors());
    }
    
    /**
     * 
     */
    private void updateNeighboors(){
        for(DemineurCell currentNeighboor : this.neighboors){
            if(currentNeighboor.getState()==DemineurState.WithoutBombNeighboorCell){
                currentNeighboor.setState(DemineurState.BombNeighboorCell);
            }
            currentNeighboor.incrementNumberOfBombsNeighboors();
        }
    }
    
    /**
     * 
     * @return l'état de la cellule
     */
    public final DemineurState getState(){
        return this.state;
    }
    
    /**
     * 
     * @return 
     */
    public final MarkDemineurState getMarkState(){
        return this.markState;
    }
    
    /**
     * 
     * @return 
     */
    public LinkedList<DemineurCell> getNeighboors(){
        return this.neighboors;
    }
    
    /**
     * 
     * @param _markState 
     */
    public void setMarkState(MarkDemineurState _markState){
        this.markState = _markState;
    }
    
    
 
}
