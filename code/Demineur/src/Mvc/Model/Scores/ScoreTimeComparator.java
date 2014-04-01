/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model.Scores;

import java.util.Comparator;

/**
 *
 * @author matt
 */
public class ScoreTimeComparator implements Comparator<Score> {

    @Override
    public int compare(Score o1, Score o2) {
        if (o1.getTime().getMinutes() < o2.getTime().getMinutes()) {
            return -1;
        } else if (o1.getTime().getMinutes() == o2.getTime().getMinutes()) {
            if (o1.getTime().getSecondes() < o2.getTime().getSecondes()) {
                return -1;
            } else if (o1.getTime().getSecondes() == o2.getTime().getSecondes()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
