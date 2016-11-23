package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * Classe que armazena os diferentes tipos de teclado
 * 
 * @autor Daniel Villaça 
 * @version 18/11/2016.
 */
public class KeyboardLayoutList extends ArrayList <KeyboardLayout>  {

    public KeyboardLayoutList() {
        
    }
    
    /**
     * @param keyboardType  é o model do teclado usado
     */
    public KeyboardLayout getLayoutByName(String keyboardType) {
        for(KeyboardLayout layout : this){
            if (layout.getName().equals(keyboardType.toUpperCase())){
                return layout;
            }
        }
        return null;
    }

}

