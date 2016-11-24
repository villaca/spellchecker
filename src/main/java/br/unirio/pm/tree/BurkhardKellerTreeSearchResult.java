package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;

/**
 * this class is used to do the search in the BK Tree
 * 
 * @autor Daniel Villaça
 */
public class BurkhardKellerTreeSearchResult extends BurkhardKellerTree {

    public BurkhardKellerTreeSearchResult(IDistanceCalculator calculator) {
        super(calculator);
    }

    //TODO: implementar geral
    
    

    public String getWord(int position) {

        return "";
    }

    public double getDistance(int position) {
        return 0;
    }

    public int getPosition(String word){
        return 0;
    }



}
