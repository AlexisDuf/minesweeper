/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model.Scores;

import Mvc.Model.Level;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author matt
 */
public class AllBestScores implements Iterable<AbstractBestScores> {

    private static AllBestScores instance = null;
    private AbstractBestScores[] bestScores;

    private class AllBestScoresIterator implements Iterator<AbstractBestScores> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < bestScores.length;
        }

        @Override
        public AbstractBestScores next() {
            AbstractBestScores toReturn = bestScores[index];
            this.index++;
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private AllBestScores() {
        this.bestScores = new BestScoresStandardSerialisation[Level.values().length-1]; // Impose que le custom soit en dernier
        for (int i = 0; i < this.bestScores.length; i++) {
            this.bestScores[i] = new BestScoresStandardSerialisation(Level.values()[i]);
        }
        updateScores();
        
    }

    public static AllBestScores getInstance() {
        if (instance == null) {
            instance = new AllBestScores();
        } 
        
        return instance;
    }

    @Override
    public Iterator<AbstractBestScores> iterator() {
        return new AllBestScoresIterator();
    }

    private void updateScores() {
        for (int i = 0; i < this.bestScores.length; i++) {
            if(Tools.Tools.fileExists(this.bestScores[i].level.getScoreFilePath())){
                try {
                    this.bestScores[i] = this.bestScores[i].deserialize();
                } catch (Exception ex) {
                    Logger.getLogger(AllBestScores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void saveScores() {
        for (int i = 0; i < this.bestScores.length; i++) {
            try {
                this.bestScores[i].serialize();
            } catch (Exception ex) {
                Logger.getLogger(AllBestScores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.bestScores.length; i++) {
            result += this.bestScores[i];
        }
        return result;
    }
    
    
}
