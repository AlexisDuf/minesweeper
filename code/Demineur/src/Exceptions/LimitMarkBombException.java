/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Delmaire & Dufour
 */

/*
 * Exception levée lorsque le nombre de bombes indiqué pour commencer une partie est supérieur à 85% du nombre de celules
 */
public class LimitMarkBombException extends RuntimeException{
    int numberOfBomb;
    
    
    /**
     * 
     * @param numberOfBomb Nombre de bombe passé en argument de la construction du jeu
     * @param message Message d'erreur
     */
    public LimitMarkBombException(int numberOfBomb, String message) {
        super(message);
        this.numberOfBomb = numberOfBomb;
    } 
    
    
    /**
     * 
     * @param numberOfBomb Nombre de bombe passé en argument de la construction du jeu
     */
    public LimitMarkBombException(int numberOfBomb) {
        this(numberOfBomb, "Undefined");
    }
    
}
