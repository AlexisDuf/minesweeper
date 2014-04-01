/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Model.Scores;

import Tools.Time;
import java.io.Serializable;

/**
 *
 * @author matt
 */
public class Score implements Serializable, Comparable<Score>{
    private String name;
    private Time time; 
    
    /*
     * Constructors
     */
    
    public Score(){}

    public Score(String _name, Time _time) {
        this.name = _name;
        this.time = _time;
    }
    
    public Score(Time _time){
        this("",_time);
    }
    
    /*
     * Fin constructors
     */

   
    /*
     * Getteurs
     */
    
    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }
    
    /*
     * Fin Getteurs
     */
      
    
    /*
     * Setteurs
     */
    
    public void setName(String _name){
        this.name = _name;
    }
    
    public void setTime(Time _time){
        this.time = _time;
    }
    
    /*
     * Fin Setteurs
     */
    
    

    @Override
    public int compareTo(Score other) {
        if(this.time.getMinutes() == other.time.getMinutes() && this.time.getSecondes() == other.time.getSecondes()){
            return 0;
        }
        if(this.time.getMinutes() < other.time.getMinutes() || (this.time.getMinutes() == other.time.getMinutes() && this.time.getSecondes() < other.time.getSecondes()) ){
            return -1;
        }else{
            return 1;
        }
    }
    
}
