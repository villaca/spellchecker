package br.unirio.pm.model;

/**
 * This class is used to calculate the distances without regarding the keyboard layout
 * 
 * @author Daniel Villaça
 */
public class KeyboardLayoutNeutro extends KeyboardLayout {

    @Override
    public double getNominalDistance(char key1, char key2){
        return 1;
    }

    @Override
    public double getMaximumDistance(){
        return 1;
    }

}
