/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Exceptions.BadArgumentException;
import java.io.Serializable;

/**
 *
 * @author matt
 */
public class Time implements Serializable {
    
    private int secondes;
    private int minutes;
    
    public Time(){
        this.secondes=0;
        this.minutes=0;
    }

    public Time(int _time) throws BadArgumentException {
        if(_time <0){
            throw new BadArgumentException("L'argument _time doit être supérieur à 0", "public Time(int _time)");
        }else{
            this.secondes = _time%60;
            this.minutes = (int)_time/60;
        }
    }

    public int getSecondes() {
        return secondes;
    }

    public int getMinutes() {
        return minutes;
    }
    
    public void setSecondes(int _secondes){
        this.secondes = _secondes;
    }
    
    public void setMinutes(int _minutes){
        this.minutes = _minutes;
    }
    
    public void increment(){
        this.secondes++;
        if(this.secondes == 60){
            this.secondes=0;
            this.minutes++;
        }
    }
}
