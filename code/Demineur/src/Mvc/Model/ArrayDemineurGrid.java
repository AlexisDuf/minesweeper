/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model;

import Mvc.Model.Cell.DemineurCell;
import Exceptions.BadColumnArgumentException;
import Exceptions.BadPercentageArgumentException;
import Exceptions.BadRowArgumentException;
import Exceptions.LimitMarkBombException;
import Mvc.Model.Cell.DemineurState;
import Mvc.Model.Cell.MarkDemineurState;
import java.util.LinkedList;

/**
 *
 * @author Delmaire & Dufour
 */
public class ArrayDemineurGrid extends AbstractDemineurModel {

    protected DemineurCell grid[][];
    private boolean firstReveal = true;


    /**
     * Constructor
     * @param _debugMode
     * @param _level  Level definit par l'utilisateur
     */
    public ArrayDemineurGrid(boolean _debugMode, Level _level) {
        super(_debugMode);
        if (_level.getNumberOfRows() <= 0) {
            throw new BadRowArgumentException("Le nombre de lignes doit être supérieur à 0", "ConcreteModel::ConcreteModel(boolean _debugMode, Level _level)");
        }
        if (_level.getNumberOfCols() <= 0) {
            throw new BadColumnArgumentException("Le nombre de colonnes doit être supérieur à 0", "ConcreteModel::ConcreteModel(boolean _debugMode, Level _level)");
        }
        if (_level.getNumberOfBombs() <= 0 || _level.getNumberOfBombs() >= new Double(0.85 * _level.getNumberOfRows() * _level.getNumberOfCols()).intValue()) {
            throw new BadPercentageArgumentException("Le pourcentage de bombe doit être supérieur à 0 et inférieur à 85", "ConcreteModel::ConcreteModel(boolean _debugMode, Level _level)");
        }
        startGame(_debugMode,_level);
    }

    /*
     * Getteurs
     */
    
    /**
     * 
     * @return  le nombre de lignes
     */
    @Override
    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    /**
     * 
     * 
     * @return le nombre de colonnes
     */
    @Override
    public int getNumberOfCols() {
        return this.numberOfCols;
    }

    /**
     * 
     * @return le nombre de bombe
     */
    @Override
    public int getNumberOfBomb() {
        return this.numberOfBomb;
    }

    /**
     * 
     * @return le nombre de bombes marqué
     */
    public int getNumberOfBombMarks() {
        return this.numberOfBombMarks;
    }

    /**
     * Retourne une cellule
     * @param _rowIndex 
     * @param _colIndex
     * @return 
     */
    @Override
    public DemineurCell getCell(int _rowIndex, int _colIndex) {
        if(checkValidityOfIndex(_rowIndex, _colIndex)){
            return this.grid[_rowIndex][_colIndex];
        }else{
            return null;
        }
    }
    
    

    /*
     * Fonctions relatives à l'initialisation des bombes
     */
    
    /**
     * Initialisation des bombes en prenant compte de la 1er case révélée.
     * @param firstRevealRowIndex indice où l'on a révélé la 1er celulle.
     * @param firstRevealColIndex indice où l'on a révélé la 1er celulle.
     */
    @Override
    public void initializeBomb(int firstRevealRowIndex, int firstRevealColIndex) {
        int numberOfBombs = this.numberOfBomb;
        int randomRowIndex, randomColIndex;
        while (numberOfBombs > 0) {

            randomRowIndex = (int) (Math.random() * (this.getNumberOfRows()));
            randomColIndex = (int) (Math.random() * (this.getNumberOfCols()));

            if (this.grid[randomRowIndex][randomColIndex].getState() != DemineurState.BombCell && ( randomRowIndex != firstRevealRowIndex || randomColIndex != firstRevealColIndex ) ){
                this.grid[randomRowIndex][randomColIndex].setState(DemineurState.BombCell);
                numberOfBombs--;                
            }
        }
        super.initializeBomb(firstRevealRowIndex, firstRevealColIndex);
    }

    /**
     * Initialisation des cellules voisines.
     */
    private void initCellNeighboors() {
        LinkedList<DemineurCell> neighboors;
        for (int rowIndex = 0; rowIndex < this.numberOfRows; rowIndex++) {
            for (int colIndex = 0; colIndex < this.numberOfCols; colIndex++) {
                neighboors = new LinkedList<>();
                for (int i = getMinBoundRow(rowIndex); i <= getMaxBoundRow(rowIndex); i++) {
                    for (int j = getMinBoundCol(colIndex); j <= getMaxBoundCol(colIndex); j++) {
                        if(!(rowIndex==i && j==colIndex)){ // Si ce n'est pas la case en question
                            neighboors.add(this.grid[i][j]);
                        }
                    }
                }
                this.grid[rowIndex][colIndex].setNeighboors(neighboors);
            }
        }
    }

    
    /**
     * Lancement du jeu.
     * @param _debugMode
     * @param _level
     * @return 
     */
    @Override
    public final boolean startGame(boolean _debugMode, Level _level) {
        /*
         * Initialisation de la grille (création des instances de cellules)
         */
        this.currentLevel = _level;
        this.numberOfRows = _level.getNumberOfRows();
        this.numberOfCols = _level.getNumberOfCols();
        this.numberOfBomb = _level.getNumberOfBombs();
        this.debugMode = _debugMode;
        this.firstReveal = true;

        this.grid = new DemineurCell[getNumberOfRows()][getNumberOfCols()];
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            for (int colIndex = 0; colIndex < getNumberOfCols(); colIndex++) {
                this.grid[rowIndex][colIndex] = new DemineurCell(rowIndex, colIndex);
            }
        }
        initCellNeighboors();
        return super.startGame(_debugMode, _level);
    }

    /*
     * Méthodes surchargées 
     */
    
    /**
     * Marquage d'une case comme étant une bombe
     * @param _cell
     * @return
     * @throws LimitMarkBombException 
     */
    @Override
    public boolean markCaseWithBomb(DemineurCell _cell) throws LimitMarkBombException {
        int rowIndex = _cell.getRowIndex();
        int colIndex = _cell.getColIndex();
        if (this.numberOfBombMarks >= this.numberOfBomb) {
            throw new LimitMarkBombException(numberOfBomb, "Vous avez utilisé tout vos marquages de bombes disponibles");
        } else {
            if (checkValidityOfIndex(rowIndex, colIndex)) {
                if (this.grid[rowIndex][colIndex].isReveal()) {
                    System.out.println("Impossible de marquer cette case car elle a été révélé");
                    return false;
                } else if (this.grid[rowIndex][colIndex].getMarkState() == MarkDemineurState.BombMark) {
                    System.out.println("Cette case est déjà marquée comme une bombe");
                    return false;
                } else {
                    this.grid[rowIndex][colIndex].setMarkState(MarkDemineurState.BombMark);
                    incBombMarks();
                    if ((this.numberOfRevealCell == (this.numberOfRows * this.numberOfCols) - this.numberOfBomb) && this.numberOfBombMarks == this.numberOfBomb) {
                        winGame();
                    }
                    return super.markCaseWithBomb(_cell);
                }
            } else {
                return false;
            }
        }
    }

    /**
     * Marquage d'une case où l'on ne sait pas le contenu.
     * @param _cell
     * @return 
     */
    @Override
    public boolean markUndecidedCase(DemineurCell _cell) {
        int rowIndex = _cell.getRowIndex();
        int colIndex = _cell.getColIndex();
        if (checkValidityOfIndex(rowIndex, colIndex)) {
            if (this.grid[rowIndex][colIndex].isReveal()) {
                System.out.println("Impossible de marquer cette case car elle a été révélé");
                return false;
            } else if (this.grid[rowIndex][colIndex].getMarkState() == MarkDemineurState.UndecidedMark) {
                System.out.println("Cette case est déjà marquée comme indéfinie");
                return false;
            } else {
                if (this.grid[rowIndex][colIndex].getMarkState() == MarkDemineurState.BombMark) {
                    this.decBombMarks(); // Un marquage de bombe en moins
                }
                this.grid[rowIndex][colIndex].setMarkState(MarkDemineurState.UndecidedMark);
                return super.markUndecidedCase(_cell);

            }

        } else {
            return false;
        }
    }

    /**
     * Marquage supprimer de la case choisit
     * @param _cell
     * @return 
     */
    @Override
    public boolean deleteMarking(DemineurCell _cell) {
        int rowIndex = _cell.getRowIndex();
        int colIndex = _cell.getColIndex();
        if (checkValidityOfIndex(rowIndex, colIndex)) {
            if (!(this.grid[rowIndex][colIndex].isReveal())) {
                if (this.grid[rowIndex][colIndex].getMarkState() == MarkDemineurState.BombMark) {
                    this.decBombMarks();
                }
                this.grid[rowIndex][colIndex].setMarkState(MarkDemineurState.AnyMarking);
                return super.deleteMarking(_cell);
            } else {
                System.out.println("Impossible d'enlever le marquage cette case car elle a été révélé");
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Révélation de la case choisit
     * @param _cell cellule choisit
     * @return 
     */
    @Override
    public boolean revealCase(DemineurCell _cell) {
        int rowIndex = _cell.getRowIndex();
        int colIndex = _cell.getColIndex();
        if (_cell.isReveal()) {
            return false; // Condition d'arrêt de la récursion
        }
        if (checkValidityOfIndex(rowIndex, colIndex)) {

            if (this.firstReveal) {    // Premier coup toujours gagnant
                initializeBomb(rowIndex, colIndex);
                this.firstReveal = false;
            }

            if (!(_cell.isReveal())) { // On vérifie que la case n'a pas encore été révélé

                if (_cell.getState() == DemineurState.BombCell) { // Cas où la case révélée est une bombe
                    revealBombCell(_cell);
                } else {
                    if (_cell.getMarkState() == MarkDemineurState.BombMark) { // On vérifie si la case révélée possédait un marquage de bombe
                        this.decBombMarks();
                    }
                    if (_cell.getState() == DemineurState.BombNeighboorCell) { // Cas où la case révélée possède des voisins bombes
                        revealNeighborBombCell(_cell);
                    } else {
                        revealCellWithoutBombsNeighbors(_cell);
                    }
                }
                super.revealCase(_cell);
                checkWinGame();
                return true;


            } else { // La case est déjà révélée 

                System.out.println("La cellule spécifiée a déjà été révélée");
                return false;
            }

        } else { // Les indexs sont invalides
            return false;
        }
    }

    /*
     * Fonctions privées 
     */
    /**
     * Verification si la partie est gagnée
     */
    private void checkWinGame() {
        if ((this.numberOfRevealCell == (this.numberOfRows * this.numberOfCols) - this.numberOfBomb) && this.numberOfBombMarks == this.numberOfBomb) {
            winGame();
        }
    }

    /**
     * Revalation d'une celulle
     * @param _cell 
     */
    private void revealBombCell(DemineurCell _cell) {
        _cell.setReveal(true);
        looseGame();
    }

    /**
     * 
     * @param _cell 
     */
    private void revealNeighborBombCell(DemineurCell _cell) {
        _cell.setReveal(true);
        this.numberOfRevealCell++;
    }

    /**
     * Revelation des cellules n'ayant aucunes bombes autour d'elle 
     * @param _cell 
     */
    private void revealCellWithoutBombsNeighbors(DemineurCell _cell) {

        _cell.setReveal(true);
        this.numberOfRevealCell++;
        LinkedList<DemineurCell> neighboors = _cell.getNeighboors();
        
        for(DemineurCell currentNeighboor : neighboors){
            if(currentNeighboor.getState()==DemineurState.BombNeighboorCell || currentNeighboor.getState()==DemineurState.WithoutBombNeighboorCell){
                revealCase(currentNeighboor);
            }
            
        }


    }

    /**
     * Verification des coordonnées si elles sont correctes
     * @param rowIndex
     * @param colIndex
     * @return 
     */
    private boolean checkValidityOfIndex(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= this.getNumberOfRows()) {
            throw new BadRowArgumentException("L'index de la ligne doit être supérieur à 0 et inférieur au nombre de lignes", "ArrayDemineurGrid::checkValidityOfIndex(int rowIndex, int colIndex)");
        }
        if (colIndex < 0 || colIndex >= this.getNumberOfCols()) {
            throw new BadColumnArgumentException("L'index de la colonne doit être supérieur à 0 et inférieur au nombre de colonnes", "ArrayDemineurGrid::checkValidityOfIndex(int rowIndex, int colIndex");
        }
        return true;
    }

    /*
     * Fonctions pour le parcours des voisins
     */
    /**
     * 
     * @param rowIndex
     * @return 
     */
    private int getMinBoundRow(int rowIndex) {
        if (rowIndex - 1 < 0) {
            return 0;
        } else {
            return rowIndex - 1;
        }
    }

    /**
     * 
     * @param rowIndex
     * @return 
     */
    private int getMaxBoundRow(int rowIndex) {
        if (rowIndex + 1 >= getNumberOfRows()) {
            return getNumberOfRows() - 1;
        } else {
            return rowIndex + 1;
        }
    }

    /**
     * 
     * @param colIndex
     * @return 
     */
    private int getMinBoundCol(int colIndex) {
        if (colIndex - 1 < 0) {
            return 0;
        } else {
            return colIndex - 1;
        }
    }

    /**
     * 
     * @param colIndex
     * @return 
     */
    private int getMaxBoundCol(int colIndex) {
        if (colIndex + 1 >= getNumberOfCols()) {
            return getNumberOfCols() - 1;
        } else {
            return colIndex + 1;
        }
    }
    
    
}
