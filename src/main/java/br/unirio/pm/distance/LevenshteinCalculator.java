package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;

/**
 * this class calculates de distance using Levenshtein algothim
 * 
 * @autor Daniel Villaça
 */
public class LevenshteinCalculator implements IDistanceCalculator {
    public LevenshteinCalculator(KeyboardLayout layout) {
    }

    
    /**
     * 
     * @param word1 ?
     * @param word2 ?
     * @return the Levenshtein distance
     */
    @Override
    public double calcula(String word1, String word2) {
        return 0;
    }
}
