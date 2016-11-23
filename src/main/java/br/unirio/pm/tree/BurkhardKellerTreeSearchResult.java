package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;

/**
 * Classe que representa a busca na BK Tree
 * 
 * @autor Daniel Villaça 
 * @version 18/11/2016.
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
