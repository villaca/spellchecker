package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;



/**
 * This class calculate the I distance
 * 
 * @autor Daniel Villaça
 */
public interface IDistanceCalculator {
    
    public KeyboardLayout layout = new KeyboardLayout();

    public abstract double calculatesDistance(String word1, String word2);
    
}
