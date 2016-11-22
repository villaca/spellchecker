package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class KeyboardLayoutList extends ArrayList <KeyboardLayout>  {
    //ArrayList <KeyboardLayout> layoutList;

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

    /*public void add(KeyboardLayout layout) {
        this.add(layout);
    }*/
}

