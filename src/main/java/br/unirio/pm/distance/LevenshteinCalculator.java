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
     * @param o ?
     * @param e1 ?
     * @return the Levenshtein distance
     */
    @Override
    public int distance(Object o, Object e1) {
        //TODO: implementar Levenshtein
        return 0;
    }
}
