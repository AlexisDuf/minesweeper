/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Mvc.Model.Scores.AbstractBestScores;
import Mvc.Model.Scores.Score;
import java.io.File;
import java.util.Iterator;

/**
 *
 * @author alexisdufour
 */
public class Tools {

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean fileExists(String filePath) {
        File f = new File(filePath);
        return f.exists();
    }
    
    public static boolean isNewBestScore(AbstractBestScores abs, Time time){
        Iterator<Score> it = abs.descendingIterator();
        if(abs.getNumberSaved() < abs.numberOfScores){
            return true;
        }else{
            Score lastScore = it.next();
            if(lastScore.compareTo(new Score(time))==-1){
                return false;
            }else{
                return true;
            }
        }
    }
}
