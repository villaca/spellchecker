package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class is used to do the search in the BK Tree
 * 
 * @autor Daniel Villaça
 */
public class BurkhardKellerTreeSearchResult extends BurkhardKellerTree {

    private ArrayList<String> matches;


    public BurkhardKellerTreeSearchResult(IDistanceCalculator calculator) {
        super(calculator);
        matches = new ArrayList<String>();
    }

    public BurkhardKellerTreeSearchResult(IDistanceCalculator calculator, String root) {
        super(calculator, root);
    }


    public ArrayList<String> getMatches() {
        return this.matches;
    }

    public String getWord(int position) {
        return this.getMatches().get(position).toLowerCase();
    }

    public double getDistance(int position) {
        return this.getCalculator().calcula(this.getWord(0),this.getWord(position));
    }

    public int getPosition(String word) throws Exception {
        if(!(this.getMatches().contains(this.wordDefault(word)))){
            throw new Exception("Word not found!");
        }
        else{
            return this.getMatches().indexOf(word);
        }
    }

    public void add(String word){
        this.getMatches().add(word);
    }


}
