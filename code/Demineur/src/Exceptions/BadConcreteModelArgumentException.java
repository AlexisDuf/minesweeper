/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Delmaire & Dufour
 */
public class BadConcreteModelArgumentException extends BadArgumentException {

    public BadConcreteModelArgumentException() {
    }

    /**
     * 
     * @param message Message d'erreur 
     * @param functionName Fonction dans laquelle l'exception a été levée
     */
    public BadConcreteModelArgumentException(String message, String functionName) {
        super(message, functionName);
    }
    
}
