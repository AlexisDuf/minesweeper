/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model;

import Events.ChangeCaseEvent;
import Events.ChangeTimerEvent;
import Events.Event;
import Exceptions.BadArgumentException;
import Mvc.Model.Cell.DemineurCell;
import Mvc.Model.Scores.AbstractBestScores;
import Mvc.Model.Scores.AllBestScores;
import Tools.Time;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 *
 * @author Delmaire & Dufour
 */
public abstract class AbstractDemineurModel {

    protected LinkedListModelNotificator notificator;
    protected int numberOfBombMarks = 0;
    protected int numberOfBomb = 0;
    protected int numberOfRows;
    protected int numberOfCols;
    protected int numberOfRevealCell = 0;
    protected int nextGameNumberOfRows;
    protected int nextGameNumberOfCols;
    protected boolean debugMode;
    protected AllBestScores allBestScores;
    protected Level currentLevel;
    
    
    protected Chronometer gameTimer;
    Time time;

    /*
     * Constructors
     */
    public AbstractDemineurModel() {
        this(false);
        this.time = new Time();
    }

    /**
     *
     * @param _debugMode Activation du mode debug
     */
    public AbstractDemineurModel(boolean _debugMode) {
        this.time = new Time();
        this.debugMode = _debugMode;
        this.notificator = new LinkedListModelNotificator();
        this.allBestScores = AllBestScores.getInstance();
        InitTimer();
    }

    /*
     * Getteurs
     */
    public Time getTimer() {
        return this.time;
    }
    
   
    /**
     * 
     * @param _level correspond à "beginner", "intermediate", "Expert"
     * @return les meilleurs scores
     * @throws BadArgumentException 
     */
    public AbstractBestScores getBestScores(Level _level) throws BadArgumentException{
        if(_level == Level.Custom){
            throw new BadArgumentException("Les scores ne sont pas gérés pour le mode custom du jeu", "public AbstractBestScores getBestScores(Level _level)");
        }else{
            boolean found = false;
            Iterator<AbstractBestScores> it = this.allBestScores.iterator();
            AbstractBestScores currentBestScore=null;
            while(it.hasNext() && !found){
                currentBestScore = it.next();
                if(currentBestScore.getLevel()==_level){
                    found = true;
                }
            }
            if(found){
                return currentBestScore;
            }else{
                return null;
            }
        }
    }
    
    
    /**
     * 
     * @return le level de la partie
     */
    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    /**
     *
     * @return 
     */
    public int getNextGameNumberOfRows() {
        return this.nextGameNumberOfRows;
    }

    /**
     *
     * @return
     */
    public int getNextGameNumberOfCols() {
        return this.nextGameNumberOfCols;
    }

    /**
     *
     * @return un notificateur
     */
    public LinkedListModelNotificator getNotificator() {
        return this.notificator;
    }

    /**
     *
     * @return le nombres de mine restants à placer 
     */
    public int getRemainingMines() {
        return this.numberOfBomb - this.numberOfBombMarks;
    }

    /*
     * Setteurs
     */
    /**
     *
     * @param _nextGameNumberOfRows
     */
    public void setNextGameNumberOfRows(int _nextGameNumberOfRows) {
        this.nextGameNumberOfRows = _nextGameNumberOfRows;
    }

    /**
     *
     * @param _nextGameNumberOfCols
     */
    public void setNextGameNumberOfCols(int _nextGameNumberOfCols) {
        this.nextGameNumberOfCols = _nextGameNumberOfCols;
    }


    /**
     * incrémente le nombre de bombe marqué
     */
    public void incBombMarks() {
        this.numberOfBombMarks++;
        this.notificator.fireChangeRemainingBomb(new Event(this, "AbstractDemineurModel::incBombMarks"));
    }

    /**
     *
     */
    public void decBombMarks() {
        this.numberOfBombMarks--;
        this.notificator.fireChangeRemainingBomb(new Event(this, "AbstractDemineurModel::incBombMarks"));
    }

    /**
     *
     * @return
     */
    public boolean getDebugMode() {
        return this.debugMode;
    }

    /**
     *
     */
    public void closeGame() {
        notificator.removeViews();
    }

    /**
     *
     */
    public void looseGame() {
        this.gameTimer.stopChrono();
        this.notificator.fireLooseGameEvent(new Event(this, "AbstractDemineurModel::looseGame()"));
    }

    /**
     *
     */
    public void winGame() {
        this.gameTimer.stopChrono();
        this.notificator.fireWinGameEvent(new Event(this, "AbstractDemineurModel::winGame()"));
    }

    /*
     * Fonctions propres à l'application
     */
    /**
     * 
     * @param _debugMode si on est en mode debug
     * @param _level level définis lors du lancement du jeu 
     * @return 
     */
    public boolean startGame(boolean _debugMode, Level _level) {
        this.numberOfBombMarks = 0;
        this.numberOfRevealCell = 0;
        this.gameTimer.getChrono().restart();
        this.time = new Time();
        this.gameTimer.startChrono();
        this.notificator.fireNewGameEvent(new Event(this, "AbstractDemineurModel::startGame()"));
        return true;
    }

    /**
     * initialisation du timer quand la partie se lance
     */
    public final void InitTimer() {
        final AbstractDemineurModel newThis = this;
        this.gameTimer = new Chronometer(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                time.increment();
                notificator.fireChangeTimerCount(new ChangeTimerEvent(newThis, "AbstractDemineurMode::InitTimer()", newThis.time));
            }
            
        });
        this.notificator.fireChangeTimerCount(new ChangeTimerEvent(this, "AbstractDemineurMode::InitTimer()", newThis.time));
    }

    /**
     *
     * @param _cell cellule qui a été choisi par l'utilisateur
     * @return
     */
    public boolean markCaseWithBomb(DemineurCell _cell) {
        this.notificator.firechangeCaseEvent(new ChangeCaseEvent(this, "AbstractDemineurMode::markCaseWithBomb(int rowIndex, int colIndex)", _cell));
        return true;
    }

    /**
     *
     * @param _cell cellule qui a été choisi par l'utilisateur
     * @return
     */
    public boolean markUndecidedCase(DemineurCell _cell) {
        this.notificator.firechangeCaseEvent(new ChangeCaseEvent(this, "AbstractDemineurMode::markUndecidedCase(int rowIndex, int colIndex)", _cell));
        return true;
    }

    /**
     *
     * @param _cell cellule qui a été choisi par l'utilisateur
     * @return
     */
    public boolean deleteMarking(DemineurCell _cell) {
        this.notificator.firechangeCaseEvent(new ChangeCaseEvent(this, "AbstractDemineurMode::deleteMarking(int rowIndex, int colIndex)", _cell));
        return true;
    }

    /**
     *
     * @param _cell cellule qui a été choisi par l'utilisateur
     * @return
     */
    public boolean revealCase(DemineurCell _cell) {
        this.notificator.firechangeCaseEvent(new ChangeCaseEvent(this, "AbstractDemineurMode::revealCase(int rowIndex, int colIndex)", _cell));
        return true;
    }

    /*
     * Getteurs application
     */
    /**
     *
     * @return
     */
    public abstract int getNumberOfBomb();

    /**
     *
     * @return
     */
    public abstract int getNumberOfCols();

    /**
     *
     * @return
     */
    public abstract int getNumberOfRows();
    
    
    /**
     * 
     * @param firstRevealRowIndex indice où l'on a révélé la 1er celulle
     * @param firstRevealColIndex indice où l'on a révélé la 1er celulle
     */
    public void initializeBomb(int firstRevealRowIndex, int firstRevealColIndex){
        this.notificator.fireInitializeBombEvent(new Event(this,"AbstractDemineurModel::initializeBomb"));
    }

    /**
     *
     * @param _rowIndex
     * @param _colIndex
     * @return
     */
    public abstract DemineurCell getCell(int _rowIndex, int _colIndex);
}
