/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Delmaire & Dufour
 */
public class BadRowArgumentException extends BadConcreteModelArgumentException {

    public BadRowArgumentException() {
    }

    /**
     * 
     * @param message Message d'erreur 
     * @param functionName  Fonction dans laquelle l'exception a été levée
     */
    public BadRowArgumentException(String message, String functionName) {
        super(message, functionName);
    }
    
}
