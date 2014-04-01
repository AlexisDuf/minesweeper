/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controller;

import Exceptions.BadModelException;
import Mvc.Model.AbstractDemineurModel;
import Tools.Tools;
import java.util.Scanner;

/**
 *
 * @author Delmaire & Dufour
 */
public class ConsoleController implements AbstractController {

    protected AbstractDemineurModel model;
    private final static String revealCaseCommand = "d";
    private final static String markingCaseCommand = "m";
    private final static String exitGameCommand = "q";
    private final static String markinCaseBombCommand = "x";
    private final static String markingCaseUndecidedCommand = "?";
    private final static String deleteMarkingCase = "#";

    /**
     *
     * @param _model Modèle que le controleur controle
     */
    public ConsoleController(AbstractDemineurModel _model) {
        if (_model == null) {
            throw new BadModelException("null object", "AbstractController(AbstractModel _model)");
        } else {
            this.model = _model;
        }
        initializeListenners();
    }

    /**
     *
     * @return Renvoie le model qui est controlé
     */
    @Override
    public AbstractDemineurModel getModel() {
        return this.model;
    }

    /**
     * Initialisation des écouteurs sur les entrées clavier
     */
    @Override
    public final void initializeListenners() {
        Scanner s = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu Demineur");
        String line = s.nextLine();
        while (!(line.equals("q"))) {

            String cmd = String.valueOf(line.substring(0, 1));
            switch (cmd) {
                case "m":
                    if (line.length() == 7) {
                        String symbol = line.substring(6, 7);
                        if (Tools.isInteger(line.substring(2, 3)) && Tools.isInteger(line.substring(4, 5))) {
                            int i = Integer.parseInt(line.substring(2, 3));
                            int j = Integer.parseInt(line.substring(4, 5));
                            if (symbol.equals("?")) {
                                model.markUndecidedCase(model.getCell(i, j));
                            } else if (symbol.equals("!")) {
                                model.markCaseWithBomb(model.getCell(i, j));
                            } else if (symbol.equals("#")) {
                                model.deleteMarking(model.getCell(i, j));
                            }

                        } else {
                            System.out.println("Les coordonnées rentrées ne sont pas correctes, le format pour marquer une case est m int int String ");
                        }
                    } else {
                        System.out.println("Mauvais format de marquage, Le format pour marquer une case est m int int String ");
                    }

                    break;
                case "d":
                    if (line.length() == 5) {
                        if (Tools.isInteger(line.substring(2, 3)) && Tools.isInteger(line.substring(4, 5))) {
                            int i = Integer.parseInt(line.substring(2, 3));
                            int j = Integer.parseInt(line.substring(4, 5));
                            model.revealCase(model.getCell(i, j));

                        } else {
                            System.out.println("Les coordonnées rentrées ne sont pas correctes, le format pour dévoiler une case est d int int ");
                        }
                    } else {
                        System.out.println("Mauvais format pour dévoiler une case, le format pour dévoiler une case est d int int ");
                    }

                    break;
                default:
                    System.out.println("Vous n'avez pas rentré une commande correct");


            }
            line = s.nextLine();
        }
        s.close();
    }
    
    
    
}
