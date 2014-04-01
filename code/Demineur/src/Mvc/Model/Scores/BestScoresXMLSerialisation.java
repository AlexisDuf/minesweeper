/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model.Scores;

import Mvc.Model.Level;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author matt
 */
public class BestScoresXMLSerialisation extends AbstractBestScores {
    
    public BestScoresXMLSerialisation(){}

    public BestScoresXMLSerialisation(Level level) {
        super(level);
    }

    @Override
    public void serialize() throws FileNotFoundException, IOException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(this.level.getScoreFilePath()));
        try {
            encoder.writeObject(this);
            encoder.flush();
        } finally {
            encoder.close();
        }
    }

    @Override
    public AbstractBestScores deserialize() throws FileNotFoundException, IOException{
        Object object = null;
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(this.level.getScoreFilePath()));
        try {
            object = decoder.readObject();
        } finally {
            decoder.close();
        }
        return (AbstractBestScores)object;
    }
}
