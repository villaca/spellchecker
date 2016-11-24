package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;
import info.debatty.java.stringsimilarity.*;
import edu.gatech.gtri.bktree.Metric;

/**
 * this class calculates de distance using Demerau Levenshtein algothim
 * 
 * @autor Daniel Villaça
 */
public class DemerauLevenshteinCalculator implements IDistanceCalculator {
    public DemerauLevenshteinCalculator(KeyboardLayout layout) {
    }


    /**
     * 
     * @param o ?
     * @param e1 ?
     * @return the Demerau Levenshtein distance
     */
    @Override
    public int distance(Object o, Object e1) {
        Damerau d = new Damerau();
        return (int) d.distance(o.toString().toUpperCase(), e1.toString().toUpperCase());
    }
}
