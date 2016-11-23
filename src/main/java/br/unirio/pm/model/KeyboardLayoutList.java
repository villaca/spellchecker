package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class KeyboardLayoutList extends ArrayList <KeyboardLayout>  {

    public KeyboardLayoutList() {
        
    }
    
    public KeyboardLayout getLayoutByName(String keyboardType) {
        for(KeyboardLayout layout : this){
            if (layout.getName().equals(keyboardType.toUpperCase())){
                return layout;
            }
        }
        return null;
    }

}

