/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model.Scores;

import Mvc.Model.Level;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author matt
 */
public abstract class AbstractBestScores implements Iterable<Score>, Serializable {

    public  static int numberOfScores=5; // Nombre maximal de scores que l'on enregistre par niveau
    protected Level level;
    protected LinkedList<Score> scores;
    
    public AbstractBestScores(){
        
    }

    public AbstractBestScores(Level level) {
        this.level = level;
        this.scores = new LinkedList<>();
    }

    public static int getNumberOfScores() {
        return numberOfScores;
    }

    public Level getLevel() {
        return level;
    }
    
    /**
     * 
     * @return la taille de scores pour voir si la taille est inferieur ou égal à 5
     */
    public int getNumberSaved(){
        return this.scores.size();
    }

    /**
     * Fonction permettant d'ajouter un score à la liste des meilleurs scores
     * @param newScore 
     */
    public boolean addScore(Score newScore) {
        boolean added = false;
        Iterator<Score> iterator = this.scores.descendingIterator();
        Score currentScore;
        int index = this.scores.size();


        if (this.scores.size() < numberOfScores) { // Si le nombre de score maximum n'est pas atteind pas besoin de parcourir la liste
            this.scores.add(newScore);
            Collections.sort(scores, new ScoreTimeComparator());
            return true;
        } else {
            if (iterator.hasNext()) {
                currentScore = iterator.next();
                index--;
                if (newScore.compareTo(currentScore) == 1) { // Si le temps est plus élevé que le dernier des meilleurs scores pas besoin d'aller plus loin
                    return false; // On n'ajoute pas le score
                } else { // Sinon on parcourt la liste pour ajouter le score au bon endroit
                    while (iterator.hasNext() && !added) {
                        currentScore = iterator.next();
                        if (newScore.compareTo(currentScore) == 1 || index==0) {
                            this.scores.add(index, newScore); // On ajoute le nouveau score
                            this.scores.removeLast(); // On supprime le dernier
                            added = true;
                        }
                        index--;
                    }
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 
     * @return un iterateur sur notre classe 
     */
    @Override
    public Iterator<Score> iterator() {
        return this.scores.iterator();
    }
    
    /**
     * 
     * @return un iterateur sur notre classe 
     */
    public Iterator<Score> descendingIterator(){
        return this.scores.descendingIterator();
    }

    @Override
    public String toString() {
        String result = this.level.getName()+"\n";
        for(Score currentScore : scores){
            result += currentScore.getName() +"      "+currentScore.getTime().getMinutes()+" minutes et "+currentScore.getTime().getSecondes()+" secondes \n";
        }
        return result;
    }
    
    

    public abstract void serialize() throws Exception;
    public abstract AbstractBestScores deserialize() throws Exception;
}
