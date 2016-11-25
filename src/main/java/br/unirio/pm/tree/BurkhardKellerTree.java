package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;
import java.util.HashMap;
import java.util.Set;

/**
 * this class represents the BK tree
 * 
 * @autor Daniel Villaça
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
     * @param word the word to be searched in the bktree
     * @param maxDistanceAllowed the maximum distance allowed for the root node in relation to the word searched for
     * @param maxNodesAllowed the maximum number of nodes contained in the bktree returned by the search
     *
     */
    public BurkhardKellerTreeSearchResult search(String word, int maxDistanceAllowed, int maxNodesAllowed) {

        BurkhardKellerTreeSearchResult bkTree = new BurkhardKellerTreeSearchResult(this.calculator);

        int nodesReturnedCount = 0;
        if(root.equals(word)){

            bkTree.copy(this);
            bkTree.children.keySet() ;


            bkTree.add(word);
            nodesReturnedCount++;

            for(int i = 0; i < maxDistanceAllowed; i++){
                bkTree.add(this.children.get(i).getRoot());
                nodesReturnedCount++;
                if (nodesReturnedCount >= maxNodesAllowed){
                    break;
                }

                Set<Integer> chaves = bkTree.getChildren().keySet();
                for(int chave : chaves){
                    int depth = 0;
                    BurkhardKellerTree childTree = this.children.get(chave);


                        bkTree.add(children.get(i).getRoot());
                    }

                    bkTree.add(children.get(i).getRoot());
                    nodesReturnedCount++;
                    if (nodesReturnedCount >= maxNodesAllowed){
                        break;
                    }
                }

            }
        }

        for (BkTreeSearcher.Match<? extends String> match : matches) {
            bkTree.add(match.getMatch());

            //NÃO REMOVER O TRECHO COMENTADO ABAIXO,
            //TODO: ARRUMAR UMA MANEIRA ADEQUADA DE FAZER ISSO, PROVAVELMENTE POVOANDO TUDO E RETIRANDO O Q EXCEDER
            /*nodesReturnedCount++;
            if (nodesReturnedCount >= maxNodesAllowed){
                break;
            }*/
        }
        return bkTree;
    }

    public void add(String word){
        if(root == null){
            root = word;
        }
        else{
            int distance = this.truncateDistance(this.calculator.calcula(this.root, word));

            if(children.containsKey(distance)){
                children.get(distance).add(word);
            }
            else {
                children.put(distance, new BurkhardKellerTree(this.calculator, word));
            }
        }
    }

    protected int truncateDistance(double distance){
        return (int) Math.round(distance * 100);
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

    public void copy(BurkhardKellerTree bkTree){
        this.root = bkTree.getRoot();
        this.children = (HashMap<Integer, BurkhardKellerTree>) bkTree.getChildren().clone();
        this.calculator = bkTree.getCalculator();
    }

    public void removeFurtherThan(int maximumDistance){

        Set<Integer> keys = this.children.keySet();

        for(int i = 0; i < keys.size(); i++){
            this.children.get(i).removeFurtherThan(maximumDistance);
        }



    }

}
