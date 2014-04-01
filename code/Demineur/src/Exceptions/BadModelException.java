/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Delmaire & Dufour
 */
public class BadModelException extends BadArgumentException {

    public BadModelException() {
    }

    /**
     * 
     * @param message Message d'erreur
     * @param functionName Fonction dans laquelle l'exception a été levée
     */
    public BadModelException(String message, String functionName) {
        super(message, functionName);
    }
    
}
