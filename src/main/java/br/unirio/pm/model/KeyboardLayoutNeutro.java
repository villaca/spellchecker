package br.unirio.pm.model;

/**
 * This class is used to calculate the distances without regarding the keyboard layout
 * 
 * @author Daniel Villaça
 */
public class KeyboardLayoutNeutro extends KeyboardLayout {

    /**
     * 
     * @param key1 key of the keyboard
     * @param key2 key of the keyboard
     * @return the cost to change the characters, it will always be 1 beacause don't have a keyboard layout here
     */
    @Override
    public double getNominalDistance(char key1, char key2){
        return 1;
    }

    /**
     * 
     * @return value 1, is the maximun distance, beacause don't have a keyboard layout here
     */
    @Override
    public double getMaximumDistance(){
        return 1;
    }

    /**
     * 
     * @return the insert and delete distance will always be 1 as well 
     */
    @Override
    public double getInsertDeleteDistance() {
        return 1;
    }

}
