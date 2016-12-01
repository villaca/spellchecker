package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * this class is used to do the search in the BK Tree
 * 
 * @author Daniel Villaça
 */
public class BurkhardKellerTreeSearchResult extends BurkhardKellerTree {

    private String target;
    private ArrayList<String> matches;


    public BurkhardKellerTreeSearchResult(IDistanceCalculator calculator, String target) {
        super(calculator);
        matches = new ArrayList<String>();
        this.target = target;
    }



    public ArrayList<String> getMatches() {
        return this.matches;
    }

    /**
     *
     * @param position the known position of a match among the matches of the previous search over the bk tree
     * @return the word occupying the parameter position
     */
    public String getWord(int position) {
        return this.getMatches().get(position).toLowerCase();
    }

    /**
     *
     * @param distance the distance that many words are removed from the target
     * @param quantity the number of words far by the same distance
     * @return a list of words sharing the same distance from the target word of the search
     */
    private ArrayList<String> getEquidistantWords(double distance, int quantity) {

        ArrayList<String> words = new ArrayList<String>();
        int i = 0;
        for(String word : this.getMatches()){
            if(this.getCalculator().calculateDistance(this.wordDefault(this.target), word) == distance){
                words.add(word);
                i++;
            }
            if(i >= quantity){
                break;
            }
        }
        return words;
    }

    /**
     *
     * @param position the known position of a match among the matches of the previous search over the bk tree
     * @return the distance of the word found in that position of the matches of the previous search over the bk tree in relation to searched word
     */
    public double getDistance(int position) {
        return this.getCalculator().calculateDistance(this.wordDefault(this.target),
                                                        this.wordDefault(this.getWord(position)));
    }

    /**
     *
     * @param word word to be searched for inside the matches of the previous search over the bk tree
     * @return the position of the found word among the matches of the previous search over the bk tree
     * @throws Exception when the word isn't among the matches of the previous search over the bk tree
     */
    public int getPosition(String word) throws Exception {
        if(!(this.getMatches().contains(this.wordDefault(word)))){
            throw new Exception("Word not found!");
        }
        else{
            return this.getMatches().indexOf(this.wordDefault(word));
        }
    }

    public void add(String word){
        this.getMatches().add(word);
    }

    /**
     *  sort the stored matches from the search
     */
    public void sort(){

        ArrayList<String> orderedMatches = new ArrayList<String>();
        Iterator<String> it = this.getMatches().iterator();

        double minimalDistanceFound;
        int numberOfEquidistantWords;
        while (it.hasNext()){
            /*  finds the closest word to the target, add it to the ordered list and remove from the original list
                if there are more than 1 node far by the same distance, increase the number for later
                retrieval of these words
            */
            minimalDistanceFound = Double.MAX_VALUE;
            numberOfEquidistantWords = 1;
            for(String word : this.getMatches()){
                if(this.getCalculator().calculateDistance(this.wordDefault(this.target), word)
                        < minimalDistanceFound){

                    minimalDistanceFound = this.getCalculator().calculateDistance(
                                                                this.wordDefault(this.target), word);
                }
                else if(this.getCalculator().calculateDistance(this.wordDefault(this.target), word)
                            == minimalDistanceFound){

                    numberOfEquidistantWords++;
                }
            }
            ArrayList<String> closestElements = new ArrayList<String>();

            // gets equidistant words and sort them alphabetically
            if(numberOfEquidistantWords > 1){
                for(String word : this.getMatches()){
                    if(this.getCalculator().calculateDistance(this.wordDefault(this.target), word)
                                == minimalDistanceFound){

                        closestElements.addAll(
                                this.getEquidistantWords(minimalDistanceFound, numberOfEquidistantWords));
                        break;
                    }
                }

                Collections.sort(closestElements);
                orderedMatches.addAll(closestElements);
                this.matches.removeAll(closestElements);
            }
            else{
                String closestElement = this.getEquidistantWords(minimalDistanceFound, 1).get(0);
                orderedMatches.add(closestElement);
                this.matches.remove(closestElement);
            }
        }
        this.matches = orderedMatches;
    }


    /**
     *  utilitary method to limit the number of nodes returned by the search
     *
     * @param maxNodesAllowed number of itens to be returned by the search
     */
    public void limitMatches(int maxNodesAllowed){
        ArrayList<String> limitedMatches = new ArrayList<String>();

        for(int i = 0; (i < maxNodesAllowed) && (i < this.getMatches().size()); i++){
            limitedMatches.add(this.getMatches().get(i));
        }
        this.matches = limitedMatches;
    }


}
