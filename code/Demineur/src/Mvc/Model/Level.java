/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model;

/**
 *
 * @author matt
 */
public enum Level {
    Beginner(9,9,10,"./scores/beginner.ser","Beginner"), Intermediate(16,16,40,"./scores/intermediate.ser","Intermediate"), Expert(16,30,99,"./scores/expert.ser","Expert"), Custom("Custom");
    
    private int numberOfRows;
    private int numberOfCols;
    private int numberOfBombs;
    private String scoreFilePath;
    private String name;
    
    private Level(){
        /*
         * Constructor pour le custom on ne sait pas à l'avance quels vont être les paramètres
         */
    }
    
    /**
     * 
     * @param _name 
     */
    private Level(String _name){
        this.name = _name;
    }
    
    
    /**
     * 
     * @param _numberOfRows
     * @param _numberOfCols
     * @param _numberOfBombs
     * @param _scoreFilePath
     * @param _name 
     */
    private Level(int _numberOfRows, int _numberOfCols, int _numberOfBombs, String _scoreFilePath, String _name){
        this.numberOfRows = _numberOfRows;
        this.numberOfCols = _numberOfCols;
        this.numberOfBombs = _numberOfBombs;
        this.scoreFilePath = _scoreFilePath;
        this.name = _name;
    }

    /**
     * 
     * @return le nombre de lignes
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * 
     * @return le nombe de colonnes 
     */
    public int getNumberOfCols() {
        return numberOfCols;
    }

    /**
     * 
     * @return le nombre de bombes
     */
    public int getNumberOfBombs() {
        return numberOfBombs;
    }
    
    /**
     * 
     * @return le nom du fichier
     */
    public String getScoreFilePath(){
        return this.scoreFilePath;
    }
    
    /**
     * 
     * @return le nom de la personne jouant
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * 
     * @param _numberOfRows 
     */
    public void setNumberOfRows(int _numberOfRows){
        this.numberOfRows = _numberOfRows;
    }
    
    /**
     * 
     * @param _numberOfCols 
     */
    public void setNumberOfCols(int _numberOfCols){
        this.numberOfCols = _numberOfCols;
    }
    
    /**
     * 
     * @param _numberOfBombs 
     */
    public void setNumberOfBombs(int _numberOfBombs){
        this.numberOfBombs = _numberOfBombs;
    }
    
    /**
     * 
     * @param _scoreFilePath 
     */
    public void setScoreFilePath(String _scoreFilePath){
        this.scoreFilePath = _scoreFilePath;
    }
    
    /**
     * 
     * @param _name 
     */
    public void setName(String _name){
        this.name = _name;
    }
    
}
