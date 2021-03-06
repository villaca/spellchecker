package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * this class create a ArrayList of the keyboard layout
 * 
 * @author Daniel Villaça
 */
public class KeyboardLayoutList extends ArrayList <KeyboardLayout>  {

    public KeyboardLayoutList() {
        
    }
    
    /**
     * @param keyboardType  model of the used keyboard
     */
    public KeyboardLayout getLayoutByName(String keyboardType) {
        for(KeyboardLayout layout : this)
        {
            if (layout.getModel().equals(keyboardType.toUpperCase()))
            {
                return layout;
            }
                
        }
        return null;
    }

}

