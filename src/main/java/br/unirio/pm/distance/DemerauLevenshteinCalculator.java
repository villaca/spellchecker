package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;
import static java.lang.Integer.min;
import java.util.HashMap;
import java.util.Map;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * this class calculates the distance using Demerau Levenshtein algothim
 * 
 * @autor Daniel Villaça
 */
public class DemerauLevenshteinCalculator implements IDistanceCalculator {
    
    private final KeyboardLayout layout;
    
    /**
     * Defines the Keyboard Layout to be used
     * 
     * @param layout keyboard layout 
     */
    public DemerauLevenshteinCalculator(KeyboardLayout layout) {
        this.layout = layout;
    }


    /**
     * calculate the distance between 2 words using the Demerau Levenshtein theorema
     * 
     * @param word1 the first word to be compared (original)
     * @param word2 the others words to be compared with
     * 
     * @return the Demerau Levenshtein calculateDistance
     */
    @Immutable
    public double calculateDistance(String word1, String word2) {

        if (word1.length() == 0 && word2.length() == 0){
            return -1;
        }

        if (word1.length() == 0)
            return word2.length() * this.layout.getInsertDeleteDistance();
        if (word2.length() == 0)
            return word1.length() * this.layout.getInsertDeleteDistance();


        //the following solution is terrible but we'll go with it for now
        //TODO: debug the code to understand the problem with first letter insertion/deletion
        //TODO: Find a better way to solve the problem than the following solution
        /*** BEGIN FIRST LETTER BUG BRUTE FORCE FIX ***/




        String word1WithoutFirstLetter ;
        String word2WithoutFirstLetter;
        if(word1.length() > 1){
            word1WithoutFirstLetter = word1.substring(1);
        }
        else {
            word1WithoutFirstLetter = word1;
        }
        if(word2.length() > 1){
            word2WithoutFirstLetter = word2.substring(1);
        }
        else {
            word2WithoutFirstLetter = word2;
        }


        if((word1.equals(word2WithoutFirstLetter)) || (word2.equals(word1WithoutFirstLetter))){
            return this.layout.getInsertDeleteDistance();
        }

        if((word1.length() == (word2.length()-1) && (word1.charAt(0) != word2.charAt(0))) ){
            if((word1.length() > 1) && (word2.length() > 1)){
                if(word1.charAt(1) == word2.charAt(1)){
                    if(word2.equals("aveia")){
                        System.out.println("cheguei onde não devia");
                    }
                    return this.layout.getRelativeDistance(word1.charAt(0),word2.charAt(0))
                            + this.calculateDistance(word1WithoutFirstLetter, word2WithoutFirstLetter);
                }
            }
            return this.layout.getInsertDeleteDistance() + this.calculateDistance(word1, word2WithoutFirstLetter);
        }
        //System.out.println("word1 : " + word1 + ", word2: " + word2);
        if((word2.length() == (word1.length()-1)) && (word1.charAt(0) != word2.charAt(0))){
            if((word1.length() > 1) && (word2.length() > 1)){
                if(word2.equals("aveia")){
                    System.out.println("cheguei onde não devia");
                }
                if(word1.charAt(1) == word2.charAt(1)){
                    return this.layout.getRelativeDistance(word1.charAt(0),word2.charAt(0))
                            + this.calculateDistance(word1WithoutFirstLetter, word2WithoutFirstLetter);
                }
            }
            return this.layout.getInsertDeleteDistance() + this.calculateDistance(word1WithoutFirstLetter, word2);
        }
        /*** END FIRST LETTER BUG BRUTE FORCE FIX ***/


        
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

        // Create the calculateDistance matrix H[0 .. s1.length+1][0 .. s2.length+1]
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

        // fill in the calculateDistance matrix H
        // look at each character in word1
        for (int i = 1; i <= word1.length(); i++) 
        {
            int db = 0;

            // look at each character in word2
            for (int j = 1; j <= word2.length(); j++) 
            {
                int i1 = position.get(word2.charAt(j - 1));
                int j1 = db;

                double cost = this.layout.getRelativeDistance(word1.charAt(i - 1),word2.charAt(j - 1));
                //double cost = 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) 
                {
                    cost = 0;
                    db = j;
                }

                matrixCalculateDemerau[i + 1][j + 1] = Math.min(matrixCalculateDemerau[i][j] + cost, // substitution
                                                       Math.min(matrixCalculateDemerau[i + 1][j] + this.layout.getInsertDeleteDistance(), // insertion
                                                       Math.min(matrixCalculateDemerau[i][j + 1] + this.layout.getInsertDeleteDistance(), // deletion
                                                       matrixCalculateDemerau[i1][j1]                  //transposition
                                                                        + ((i - i1 - 1) * this.layout.getInsertDeleteDistance())
                                                                        + ((j - j1 - 1) * this.layout.getInsertDeleteDistance())
                                                                        + this.layout.getRelativeDistance(word1.charAt(i-1), word2.charAt(j1))
                                                                )
                                                       ));
            }
            position.put(word1.charAt(i - 1), i);
        }



        return matrixCalculateDemerau[word1.length() + 1][word2.length() + 1];
    }
}