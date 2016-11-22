package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class KeyboardLayoutList {
    ArrayList <KeyboardLayout> layoutList;

    public KeyboardLayoutList() {
        
    }
    
    public KeyboardLayout getLayoutByName(String keyboardType) {
        for(KeyboardLayout layout : layoutList){
            if (layout.getName().equals(keyboardType)){
                return layout;
            }
        }
        
        return null;
    }

    public void add(KeyboardLayout layout) {
        layoutList.add(layout);
    }
}

