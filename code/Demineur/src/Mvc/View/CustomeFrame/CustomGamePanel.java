/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.CustomeFrame;


import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Delmaire & Dufour
 */
public class CustomGamePanel extends JPanel {
    private final static int beginCol = 19;
    private final static int beginRow = 9;
    private final static int beginMine = 76;
    private final static int minRow = 9;
    private final static int maxRow = 24;
    private final static int minCol = 9;
    private final static int maxCol = 30;
    private final static int minMine = 9;
    private final static int maxMine = new Double(0.85 * beginCol * beginRow).intValue();
    private final static int cfrow1 = 2;
    private final static int cfrow2 = 1;
    private final static int cfcol1 = 4;
    private final static int cfcol2 = 2;
    private final static int cfmine1 = 26;
    private final static int cfmine2 = 13;
    
    public CustomElementGamePanel row;
    public CustomElementGamePanel col;
    public CustomElementGamePanel mine;
    
    private int currentNumberOfRows;
    private int currentNumberOfCols;
    
    private class ChangeSlider implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            int valRow = row.getTypeSliderCustom().getValue();
            int valCol = col.getTypeSliderCustom().getValue();
            int max = new Double(0.85 * valCol * valRow).intValue();
            int min = new Double(0.10 * valCol * valRow).intValue();
            mine.modifySlider(max / 2, min, max);
        }
    };

    public CustomGamePanel() {
        this.setLayout(new GridLayout(3, 1, 5, 5));
        row = new CustomElementGamePanel("rows", beginRow, minRow, maxRow, cfrow1, cfrow2);
        col = new CustomElementGamePanel("Columns", beginCol, minCol, maxCol, cfcol1, cfcol2);
        mine = new CustomElementGamePanel("Mines", beginMine, minMine, maxMine, cfmine1, cfmine2);

        final JSlider rowSlider = row.getTypeSliderCustom();
        final JSlider colSlider = col.getTypeSliderCustom();

    /**
     * *****************************************************
     */
    /**
     * **Create ChangeListener on rowSlider and colSlider to modifiy the
     * mineSLider***
     */
    /**
     * *****************************************************
     */
        rowSlider.addChangeListener (new ChangeSlider());
        colSlider.addChangeListener(new ChangeSlider());

     /********************************************************/
        this.add(row);
        this.add(col);
        this.add(mine);
    }
    
    /**
     * 
     * @param _numberOfRows 
     */
    private void setNumberOfRows(int _numberOfRows){
        this.currentNumberOfRows = _numberOfRows;     
    }
    
    /**
     * 
     * @param _numberofCols 
     */
    private void setNumberOfCols(int _numberofCols){
        
    }
    
    /**
     * 
     */
    private void refreshElement(){
        final JSlider rowSlider = row.getTypeSliderCustom();
        final JSlider colSlider = col.getTypeSliderCustom();
        
    }


      
}
