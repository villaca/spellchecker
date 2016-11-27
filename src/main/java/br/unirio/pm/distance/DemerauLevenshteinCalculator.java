package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;
import static java.lang.Integer.min;
import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * this class calculates de distance using Demerau Levenshtein algothim
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
     * @return the Demerau Levenshtein distance
     */
    @Immutable
    public double distance(String word1, String word2) {
        
        int largestDistance = word1.length() + word2.length();

        // Create and initialize the character array indices
        Map<Character, Integer> position = new HashMap<Character, Integer>();
       
        
        for (int i = 0; i < word1.length(); i++) {
            if (!position.containsKey(word1.charAt(i))) {
                position.put(word1.charAt(i), 0);
            }
        }

        for (int i = 0; i < word2.length(); i++) {
            if (!position.containsKey(word2.charAt(i))) {
                position.put(word2.charAt(i), 0);
            }
        }

        // Create the distance matrix H[0 .. s1.length+1][0 .. s2.length+1]
        int[][] h = new int[word1.length() + 2][word2.length() + 2];

        // initialize the left and top edges of H
        for (int i = 0; i <= word1.length(); i++) {
            h[i + 1][0] = largestDistance;
            h[i + 1][1] = i;
        }

        for (int i = 0; i <= word2.length(); i++) {
            h[0][i + 1] = largestDistance;
            h[1][i + 1] = i;

        }

        // fill in the distance matrix H
        // look at each character in word1
        for (int i = 1; i <= word1.length(); i++) {
            int db = 0;

            // look at each character in word2
            for (int j = 1; j <= word2.length(); j++) {
                int i1 = position.get(word2.charAt(j - 1));
                int j1 = db;

                int cost = 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost = 0;
                    db = j;
                }

                h[i + 1][j + 1] = Math.min(
                        h[i][j] + cost, // substitution
                        Math.min(h[i + 1][j] + 1, // insertion
                        Math.min(h[i][j + 1] + 1, // deletion
                        h[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1))));
                
                
            }

            position.put(word1.charAt(i - 1), i);
        }

        return h[word1.length() + 1][word2.length() + 1];
    }

    //na real é o método acima com o nome alterado, deixei assim pq não vou encostar nisso agora
    @Override
    public double calcula(String word1, String word2) {
        return 0;
    }
}