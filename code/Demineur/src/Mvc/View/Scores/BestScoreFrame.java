/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Scores;

import Mvc.Model.Scores.AbstractBestScores;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class BestScoreFrame extends JFrame {
    public static int xSize = 500;
    public static int ySize = 500;
    public static boolean resizable = true;

    public BestScoreFrame(AbstractBestScores ab) throws HeadlessException {
        JPanel panel = new BestScoreFramePanel(ab);
        setTitle("Scores");
        setLocationRelativeTo(null);
        setResizable(resizable);
        this.setSize(xSize, ySize);
        this.getContentPane().add(panel);
        this.setVisible(true);
    }
    
}
