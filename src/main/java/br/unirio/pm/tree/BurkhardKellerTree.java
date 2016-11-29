package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;

import java.util.*;


/**
 * this class represents the BK tree, which is used to implements spellchecker
 * 
 * @author Daniel Villaça
 */
public class BurkhardKellerTree {

    String root;
    HashMap<Integer,BurkhardKellerTree> children;
    IDistanceCalculator calculator;

    public BurkhardKellerTree(IDistanceCalculator calculator) {
        this.children = new HashMap<Integer, BurkhardKellerTree>();
        this.calculator = calculator;
    }

    public BurkhardKellerTree(IDistanceCalculator calculator, String root) {
        this.children = new HashMap<Integer, BurkhardKellerTree>();
        this.calculator = calculator;
        this.root = root;
    }



    /**
     * NAO SEI COMO EXPLICAR ESSE METODO, INDICO POR COMENTARIOS DENTRO DO METODO
     * 
     * @param word the word to be searched in the bktree
     * @param maxDistanceAllowed the maximum distance allowed for the root node in relation to the word searched for
     * @param maxNodesAllowed the maximum number of nodes contained in the bktree returned by the search
     *
     * @return the final bktree
     */
    public BurkhardKellerTreeSearchResult search(String word, int maxDistanceAllowed, int maxNodesAllowed) {

        BurkhardKellerTreeSearchResult bkTree = new BurkhardKellerTreeSearchResult(this.calculator);

        ArrayList<String> result = new ArrayList<String>();
        this.wordMatcher(this.wordDefault(word), maxDistanceAllowed, result);

        Iterator<String> it = result.iterator();


        int distanceFromRoot = 0;
        boolean distanceMatched;
        
        while(distanceFromRoot <= maxDistanceAllowed)
        {
            distanceMatched = false;
            
            while (it.hasNext()) 
            {
                String match = it.next();
                int matchedDistance = this.truncateDistance(this.getCalculator()
                                                .calculatesDistance(match,this.wordDefault(word)));
                
                if(matchedDistance == distanceFromRoot )
                {
                    bkTree.add(match);
                    distanceMatched = true;
                    it.remove();
                }
            }
            
            if(!distanceMatched)
            {
                distanceFromRoot++;
            }
            
            it = result.iterator();
        }

        return bkTree;
    }

    /**
     * NAO SEI EXPLICAR ESSE METODO ACONSELHO POR COMENTARIO DENTRO
     * 
     * @param word the imput word
     * @param maxDistanceAllowed maximun distance allowed to change the word
     * @param result NAO SEI
     */
    private void wordMatcher(String word, int maxDistanceAllowed, ArrayList<String> result) {

        Set<Integer> edges = this.getChildren().keySet();
        int distanceFromRoot = (int) (this.getCalculator().calculatesDistance(this.getRoot(), word));

        if(distanceFromRoot <= maxDistanceAllowed)
        {
            result.add(this.getRoot());
        }

        for(int i = (distanceFromRoot - maxDistanceAllowed);
            i <= (distanceFromRoot + maxDistanceAllowed)  ; i++)
        {
            if(edges.contains(i))
            {
                this.getChildren().get(i).wordMatcher(word, maxDistanceAllowed, result);
            }
        }

    }

    /**
     * add a word into the bktree
     * 
     * @param word word to be added in the bktree
     */
    public void add(String word){
        String newWord = this.wordDefault(word);
        
        if(this.getRoot() == null)
        {
            this.root = newWord;
        }
        else
        {
            int distance = this.truncateDistance(this.getCalculator().calculatesDistance(this.getRoot(), newWord));

            if(this.getChildren().containsKey(distance))
            {
                this.getChildren().get(distance).add(newWord);
            }
            else 
            {
                this.getChildren().put(distance, new BurkhardKellerTree(this.getCalculator(), newWord));
            }
        }
        
    }

    protected int truncateDistance(double distance){
        return (int) Math.round(distance * 100)/100;
    }

    public String getRoot() {
        return this.root;
    }

    public IDistanceCalculator getCalculator() {
        return calculator;
    }

    public HashMap<Integer, BurkhardKellerTree> getChildren() {
        return children;
    }

    /**
     * this method is used to padronizate the words
     * 
     * @param word to be padronizated
     * 
     * @return padronizated word
     */
    protected String wordDefault(String word){
        String newWord = word.toUpperCase();
        newWord = newWord.trim();
        newWord = newWord.replace(".", "");
        newWord = newWord.replace(",", "");
        
        return newWord;

    }

}
