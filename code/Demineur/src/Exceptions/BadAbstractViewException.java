/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author matt
 */
public class BadAbstractViewException extends BadArgumentException{

    public BadAbstractViewException() {
        
    }
         
    /**
     * 
     * @param message Message d'erreur
     * @param functionName Fonction dans laquelle l'exception a été levé
     */
    public BadAbstractViewException(String message,String functionName) {
        super(message,functionName);
    }
    
}
