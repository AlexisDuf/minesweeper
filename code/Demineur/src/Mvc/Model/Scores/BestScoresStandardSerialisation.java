/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model.Scores;

import Mvc.Model.Level;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

/**
 *
 * @author matt
 */
public class BestScoresStandardSerialisation extends AbstractBestScores {
    
    public BestScoresStandardSerialisation(){}

    public BestScoresStandardSerialisation(Level _level) {
        super(_level);
    }

    @Override
    public void serialize() {
        ObjectOutputStream objectOutPutStream = null;
        try {
            System.out.println(this.level.getScoreFilePath());
            objectOutPutStream = new ObjectOutputStream(new FileOutputStream(this.level.getScoreFilePath()));
            objectOutPutStream.writeObject(this);
        } catch (IOException ex) {
            Logger.getLogger(AbstractBestScores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            try {
                objectOutPutStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractBestScores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean addScore(Score newScore) {
        super.addScore(newScore); //To change body of generated methods, choose Tools | Templates.
        serialize();
        return true;
    }
    
    
    
    @Override
    public AbstractBestScores deserialize() {
        ObjectInputStream objectInputStream = null;
        AbstractBestScores element = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(this.level.getScoreFilePath()));
            element = (AbstractBestScores) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AbstractBestScores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        finally {
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractBestScores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        return element;
    }    
    
}
