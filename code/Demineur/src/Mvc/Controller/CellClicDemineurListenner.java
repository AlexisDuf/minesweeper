/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controller;

import Exceptions.LimitMarkBombException;
import Mvc.Model.AbstractDemineurModel;
import Mvc.Model.Cell.MarkDemineurState;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Delmaire & Dufour
 */
public class CellClicDemineurListenner implements AbstractController, MouseListener {

    private int rowIndex;
    private int colIndex;
    private AbstractDemineurModel model;

    /**
     * 
     * @param _model
     * @param _rowIndex
     * @param _colIndex 
     */
    public CellClicDemineurListenner(AbstractDemineurModel _model, int _rowIndex, int _colIndex) {
        this.model = _model;
        this.rowIndex = _rowIndex;
        this.colIndex = _colIndex;
    }

    /**
     * 
     * @return 
     */
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * 
     * @return 
     */
    public int getColIndex() {
        return this.colIndex;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.model.revealCase(this.model.getCell(rowIndex, colIndex));
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if(this.model.getCell(rowIndex, colIndex).getMarkState()== MarkDemineurState.AnyMarking){
                try{
                    this.model.markCaseWithBomb(this.model.getCell(rowIndex, colIndex));
                }
                catch(LimitMarkBombException lmbe){
                   JOptionPane.showMessageDialog(null, lmbe.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE); 
                }
            }else if (this.model.getCell(rowIndex, colIndex).getMarkState() == MarkDemineurState.BombMark){
                this.model.markUndecidedCase(this.model.getCell(rowIndex, colIndex));
            }else if (this.model.getCell(rowIndex, colIndex).getMarkState() == MarkDemineurState.UndecidedMark){
                this.model.deleteMarking(this.model.getCell(rowIndex, colIndex));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void initializeListenners() {} // Les écouteurs ont déjà été initialisé à la création de la vue

    /**
     * 
     * @return Le modèle que le controleur contrôle
     */
    @Override
    public AbstractDemineurModel getModel() {
        return this.model;
    }
}
