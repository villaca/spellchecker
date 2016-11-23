package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;
import edu.gatech.gtri.bktree.MutableBkTree;

/**
 * Classe da BK Tree
 * 
 * @autor Daniel Villaça 
 * @version 18/11/2016.
 */
public class BurkhardKellerTree {

    MutableBkTree<String> bkTree;
    IDistanceCalculator calculator;

    public BurkhardKellerTree(IDistanceCalculator calculator) {
        this.bkTree = new MutableBkTree<String>(calculator);
        this.calculator = calculator;
    }



    /**
     * @param word the word to be searched in the bktree
     * @param maxDistanceAllowed the maximum distance allowed for the root node in relation to the word searched for
     * @param maxNodesAllowed the maximum number of nodes contained in the bktree returned by the search
     *
     */
    public BurkhardKellerTreeSearchResult search(String word, int maxDistanceAllowed, int maxNodesAllowed) {

        BurkhardKellerTreeSearchResult bkTree = new BurkhardKellerTreeSearchResult(this.calculator);

        //TODO: implementar isso aqui efetivamente


        return null;
    }

    public void addWord(String word){
        this.bkTree.add(word);
    }
}
