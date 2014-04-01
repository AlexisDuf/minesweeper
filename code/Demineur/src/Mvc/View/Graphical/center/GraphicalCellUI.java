/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.Graphical.center;

import java.awt.Color;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 *
 * @author matt
 */
public class GraphicalCellUI extends MetalButtonUI {
    private Color color;

    public GraphicalCellUI(Color color) {
        this.color = color;
    }

    @Override
    protected Color getDisabledTextColor() {
        return this.color;
    }
  
}
