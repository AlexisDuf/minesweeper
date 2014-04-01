/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/*
 * Exception lancé lorsqu'un argument d'une méthode n'est pas approprié
 */

/**
 *
 * @author Delmaire & Dufour
 */
public class BadArgumentException extends RuntimeException{
    
    private String functionName;

    /**
     * Constructors
     */
    public BadArgumentException() {
        super();
        this.functionName="No function specify";
    }

    /**
     * 
     * @param message Message d'erreur
     * @param functionName Fonction dans laquelle l'exception a été levée
     */
    public BadArgumentException(String message, String functionName) {
        super(message);
        this.functionName=functionName;
    }
    
    /**
     * 
     * @return Le nom de la fonction dans laquelle l'exception a été levée
     */
    public String getFunctionName(){
        return this.functionName;
    }
    
    
}
