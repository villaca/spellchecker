package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;

/**
 * this class calculates de distance using Levenshtein algothim
 * 
 * @autor Daniel Villaça
 */
public class LevenshteinCalculator implements IDistanceCalculator {

    private final KeyboardLayout layout;
    
    public LevenshteinCalculator(KeyboardLayout layout) {
        this.layout = layout;
    }

    
    /**
     * 
     * @param word1 ?
     * @param word2 ?
     * @return the Levenshtein distance
     */
    @Override
    public double calcula(String word1, String word2) {
        if (word1.length() == 0 && word2.length() == 0) {
			return -1;
		}
        if (word1.length() == 0) 
        	return word2.length();
        if (word2.length() == 0) 
        	return word1.length();
 
        int[][] levensteinMatrix = new int[word1.length() + 1][word2.length() + 1];
        
        // fills the first line and columm
        for (int i = 0; i <= word1.length(); i++)
            levensteinMatrix[i][0] = i;
 
        for (int i = 0; i <= word2.length(); i++)
            levensteinMatrix[0][i] = i;
 
        
        //minimal distance to a word turns in another 
        for (int i = 1; i <= word1.length(); i++)
        {
            for (int j = 1; j <= word2.length(); j++)
            {
            	// if the letters of lines and columms are different we add 1 in cost
            	int cost;
            	
            	if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            		cost = 0;
            	}
            	else {
            		cost = 1;
            	}

            	// add to the matriz the lowest neightborhood value
                levensteinMatrix[i][j] = 
                		Math.min(levensteinMatrix[i - 1][j] + 1,
                				Math.min(levensteinMatrix[i][j - 1] + 1,
                				levensteinMatrix[i - 1][j - 1]
						)) + cost;
            }
        }
        
        //the last element is the minimal distance
        return levensteinMatrix[word1.length()][word1.length()];
    }
}
