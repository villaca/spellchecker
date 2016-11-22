package br.unirio.pm.reader;

import br.unirio.pm.model.KeyboardLayoutList;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class KeyboardLayoutReader {
    public KeyboardLayoutList loadFromFile(String s) {
        //aqui temos que descobrir a entrada do teclado e retornar so ela
        KeyboardLayoutList teclado = new KeyboardLayoutReader().loadFromFile("data/KeyboardLayouts.xml");
        return teclado;
    }
}
