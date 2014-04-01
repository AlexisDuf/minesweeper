/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View.CustomeFrame;

import Tools.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Delmaire & Dufour
 */
public class CustomElementGamePanel extends JPanel{
   
    private JLabel typeLabelCustom;
    private JSlider typeSliderCustom;
    private JTextField typeFieldCustom;
    private int currentNumber;

    /**
     * 
     * @param s1
     * @param defaultVal
     * @param minVal
     * @param maxVal
     * @param config1
     * @param config2 
     */
    public CustomElementGamePanel(String s1, int defaultVal, int minVal, int maxVal, int config1, int config2) {
        this.setLayout(new GridBagLayout());

        typeLabelCustom = new JLabel(s1);
        typeLabelCustom.setPreferredSize(new Dimension(10, 10));
        typeFieldCustom = new JTextField("" + defaultVal);
        typeFieldCustom.setPreferredSize(new Dimension(10, 20));
        typeSliderCustom = new JSlider(JSlider.HORIZONTAL);
        typeSliderCustom.setMinimumSize(new Dimension(300, 50));
        typeSliderCustom.setMinimum(minVal);
        typeSliderCustom.setMaximum(maxVal);
        typeSliderCustom.setValue(defaultVal);
        typeSliderCustom.setMajorTickSpacing(config1);
        typeSliderCustom.setMinorTickSpacing(config2);
        typeSliderCustom.setPaintLabels(true);
        typeSliderCustom.setPaintTicks(true);
        currentNumber = defaultVal;

        /**
         * *****************************************************
         */
        /**
         * **Create ChangeListener on Slider to modify the TextField when the
         * slider's value change***
         */
        /**
         * *****************************************************
         */
        typeSliderCustom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                typeFieldCustom.setForeground(Color.black);
                setCurrentNumber(typeSliderCustom.getValue());
            }
        });

        /**
         * *****************************************************
         */
        /**
         * **Create a KeyListener on TextField, When the textfield's value
         * change, the slider's value change***
         */
        /**
         * *****************************************************
         */
        typeFieldCustom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String FieldValue = typeFieldCustom.getText();
                boolean isInt = Tools.isInteger(FieldValue);

                if (isInt == true) {
                    int value = Integer.valueOf(FieldValue);
                    if (value < typeSliderCustom.getMinimum() || value > typeSliderCustom.getMaximum()) {
                        typeFieldCustom.setForeground(Color.red);
                        return;
                    } else {
                        typeFieldCustom.setForeground(Color.black);
                        setCurrentNumber(value);
                    }

                }
            }
        });



        GridBagConstraints cLabel = new GridBagConstraints();
        GridBagConstraints cSlider = new GridBagConstraints();
        GridBagConstraints cField = new GridBagConstraints();

        cLabel.fill = GridBagConstraints.HORIZONTAL;
        cLabel.weightx = 0.5;
        cLabel.gridx = 0;
        cLabel.gridy = 0;
        cLabel.ipadx = 10;


        cSlider.fill = GridBagConstraints.HORIZONTAL;
        cSlider.weightx = 0.5;
        cSlider.gridx = 1;
        cSlider.gridy = 0;
        cSlider.ipadx = 50;


        cField.fill = GridBagConstraints.HORIZONTAL;
        cField.weightx = 0.5;
        cField.gridx = 2;
        cField.gridy = 0;
        cField.ipadx = 10;


        this.add(typeLabelCustom, cLabel);
        this.add(typeSliderCustom, cSlider);
        this.add(typeFieldCustom, cField);

    }

    /**
     * 
     * @return 
     */
    public JLabel getTypeLabelCustom() {
        return typeLabelCustom;
    }

    /**
     * 
     * @return 
     */
    public JSlider getTypeSliderCustom() {
        return typeSliderCustom;
    }

    /**
     * 
     * @return 
     */
    public JTextField getTypeFieldCustom() {
        return typeFieldCustom;
    }
    
    /**
     * 
     * @return 
     */
    public int getCurrentNumber(){
        return this.currentNumber;
    }

    /**
     * 
     * @param defVal
     * @param min
     * @param max 
     */
    public void modifySlider(int defVal, int min, int max) {
        typeSliderCustom.setMinimum(min);
        typeSliderCustom.setMaximum(max);
        typeSliderCustom.setValue(defVal);
    }

    /**
     * 
     */
    private void refreshElements() {
        typeSliderCustom.setValue(currentNumber);
        typeFieldCustom.setText(String.valueOf(this.currentNumber));
    }

    /**
     * 
     * @param _currentNumber 
     */
    private void setCurrentNumber(int _currentNumber) {
        this.currentNumber = _currentNumber;
        refreshElements();
    }
    

}
