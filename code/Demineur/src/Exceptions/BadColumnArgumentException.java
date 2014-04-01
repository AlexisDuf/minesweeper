/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Delmaire & Dufour
 */
public class BadColumnArgumentException extends BadConcreteModelArgumentException {

    public BadColumnArgumentException() {
    }

    /**
     * 
     * @param message Message d'erreur
     * @param functionName Fonction dans laquelle, l'exceptiona été levée
     */
    public BadColumnArgumentException(String message, String functionName) {
        super(message, functionName);
    }
    
}
