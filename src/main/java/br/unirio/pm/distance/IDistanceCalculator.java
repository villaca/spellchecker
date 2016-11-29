package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;



/**
 * This class calculate the I distance
 * 
 * @author Daniel Villaça
 */
public interface IDistanceCalculator {
    
    double calculateDistance(String word1, String word2);
    
}
