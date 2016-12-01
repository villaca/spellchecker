package br.unirio.pm.distance;

import br.unirio.pm.model.KeyboardLayout;


/**
 * this class calculates the distance using Levenshtein algothim
 * 
 * @author Daniel Villaça
 */
public class LevenshteinCalculator implements IDistanceCalculator {

    private final KeyboardLayout layout;
    
    /**
     * Defines the Keyboard Layout to be used
     * 
     * @param layout keyboard layout 
     */
    public LevenshteinCalculator(KeyboardLayout layout) {
        this.layout = layout;
    }

    
    /**
     * calculate the distance between 2 words using the Levenshtein theorema
     * 
     * @param word1 the first word to be compared (original)
     * @param word2 the others words to be compared with
     * 
     * @return the Levenshtein calculateDistance
     */
    @Override
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
        /*** BEGIN FIRST LETTER BUG - BRUTE FORCE FIX ***/

        //remove the first letter of the bigger word
        String word1WithoutFirstLetter = word1.substring(1);
        String word2WithoutFirstLetter = word2.substring(1);

        //if the "first letter"-less word is equal to the other just return the insertion/deletion cost
        if((word1.equals(word2WithoutFirstLetter)) || (word2.equals(word1WithoutFirstLetter))){
            return this.layout.getInsertDeleteDistance();
        }


        /*
         *  if the "first letter"-less word suffers other modifications beyond the first letter
         *  we add the insertion/deletion cost or the cost of trading the first letter
         *  to the cost of the other transformations
         */
        if((word1.length() == (word2.length()-1) && (word1.charAt(0) != word2.charAt(0))) ){
            if((word1.length() > 1) && (word2.length() > 1)){
                if(word1.charAt(1) == word2.charAt(1)){
                    return this.layout.getRelativeDistance(word1.charAt(0),word2.charAt(0))
                            + this.calculateDistance(word1WithoutFirstLetter, word2WithoutFirstLetter);
                }
            }
            return this.layout.getInsertDeleteDistance() + this.calculateDistance(word1, word2WithoutFirstLetter);
        }
        if((word2.length() == (word1.length()-1)) && (word1.charAt(0) != word2.charAt(0))){
            if((word1.length() > 1) && (word2.length() > 1)){
                if(word1.charAt(1) == word2.charAt(1)){
                    return this.layout.getRelativeDistance(word1.charAt(0),word2.charAt(0))
                            + this.calculateDistance(word1WithoutFirstLetter, word2WithoutFirstLetter);
                }
            }
            return this.layout.getInsertDeleteDistance() + this.calculateDistance(word1WithoutFirstLetter, word2);
        }
        /*** END FIRST LETTER BUG BRUTE FORCE FIX ***/

        double[][] levensteinMatrix = new double[word1.length() + 1][word2.length() + 1];

        // fills the first line and columm
        for (int i = 0; i <= word1.length(); i++)
            levensteinMatrix[i][0] = i;
 
        for (int i = 0; i <= word2.length(); i++)
            levensteinMatrix[0][i] = i;

        //minimal distance to a word turns in another
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++){
            	// if the letters of lines and columms are different we add 1 in cost
            	double cost;
            	
            	if(word1.charAt(i - 1) == word2.charAt(j - 1)){
            		cost = 0;
            	}
            	else{
            		cost = this.layout.getRelativeDistance(word1.charAt(i - 1),word2.charAt(j - 1));
            	}

            	// add to the matrix the lowest neightborhood value
                levensteinMatrix[i][j] =
                		Math.min((levensteinMatrix[i - 1][j] + this.layout.getInsertDeleteDistance()),
                                        (Math.min((levensteinMatrix[i][j - 1]
                                                        + this.layout.getInsertDeleteDistance()),
                                                    (levensteinMatrix[i - 1][j - 1] + cost))));
            }
        }
        
        //the last element is the minimal distance
        return levensteinMatrix[word1.length()][word2.length()];
    }
}
