package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;
import static java.lang.Integer.min;
import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * this class calculates de calculatesDistance using Demerau Levenshtein algothim
 * 
 * @autor Daniel Villaça
 */
public class DemerauLevenshteinCalculator implements IDistanceCalculator {
    
    private final KeyboardLayout layout;
    
    public DemerauLevenshteinCalculator(KeyboardLayout layout) {
        this.layout = layout;
    }


    /**
     * 
     * @param word1 palavra original(?)
     * @param word2 palavra a ser comparada(?)
     * 
     * @return the Demerau Levenshtein calculatesDistance
     */
    @Immutable
    public double calculatesDistance(String word1, String word2) {
        
        int largestDistance = word1.length() + word2.length();

        // Create and initialize the character array indices
        Map<Character, Integer> position = new HashMap<Character, Integer>();
       
        
        for (int i = 0; i < word1.length(); i++) 
        {
            if (!position.containsKey(word1.charAt(i))) 
            {
                position.put(word1.charAt(i), 0);
            }
        }

        for (int i = 0; i < word2.length(); i++) 
        {
            if (!position.containsKey(word2.charAt(i))) 
            {
                position.put(word2.charAt(i), 0);
            }
        }

        // Create the calculatesDistance matrix H[0 .. s1.length+1][0 .. s2.length+1]
        double[][] matrixCalculateDemerau = new double[word1.length() + 2][word2.length() + 2];

        // initialize the left and top edges of H
        for (int i = 0; i <= word1.length(); i++) 
        {
            matrixCalculateDemerau[i + 1][0] = largestDistance;
            matrixCalculateDemerau[i + 1][1] = i;
        }

        for (int i = 0; i <= word2.length(); i++) 
        {
            matrixCalculateDemerau[0][i + 1] = largestDistance;
            matrixCalculateDemerau[1][i + 1] = i;

        }

        // fill in the calculatesDistance matrix H
        // look at each character in word1
        for (int i = 1; i <= word1.length(); i++) 
        {
            int db = 0;

            // look at each character in word2
            for (int j = 1; j <= word2.length(); j++) 
            {
                int i1 = position.get(word2.charAt(j - 1));
                int j1 = db;

//TODO: este demerau nao funciona bem com palavras de uma letra, precisa ajustar os indíces para poder usar a distância
                //double cost = this.layout.getRelativeDistance(word1.charAt(i),word2.charAt(j));
                double cost = 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) 
                {
                    cost = 0;
                    db = j;
                }

                matrixCalculateDemerau[i + 1][j + 1] = Math.min(matrixCalculateDemerau[i][j] + cost, // substitution
                                                       Math.min(matrixCalculateDemerau[i + 1][j] + this.layout.getInsertDeleteDistance(), // insertion
                                                       Math.min(matrixCalculateDemerau[i][j + 1] + this.layout.getInsertDeleteDistance(), // deletion
                                                       matrixCalculateDemerau[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1))));

//TODO: testar transposição com matrixCalculateDemerau[i1][j1] + this.layout.getNominalDistance(word1.charAt(i), word2.charAt(j1));
                
            }

            position.put(word1.charAt(i - 1), i);
        }

        return matrixCalculateDemerau[word1.length() + 1][word2.length() + 1];
    }
}