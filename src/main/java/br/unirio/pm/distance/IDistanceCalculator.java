package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;



/**
 * this class is a interface of Levenshtein and Demerau Levenshtein calculator
 * 
 * @author Daniel Villaça
 */
public interface IDistanceCalculator {
    
    double calculateDistance(String word1, String word2);
    
}
