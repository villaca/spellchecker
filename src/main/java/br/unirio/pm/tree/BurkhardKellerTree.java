package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;
import edu.gatech.gtri.bktree.BkTreeSearcher;
import edu.gatech.gtri.bktree.MutableBkTree;

import java.util.Set;

/**
 * this class represents the BK tree
 * 
 * @autor Daniel Villaça
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

        BkTreeSearcher<String> searcher = new BkTreeSearcher<String>(this.bkTree);
        Set<BkTreeSearcher.Match<? extends String>> matches = searcher.search(word, maxDistanceAllowed);

        int nodesReturnedCount = 0;
        for (BkTreeSearcher.Match<? extends String> match : matches) {
            System.out.println("tô addando a palavra : " + match.getMatch());
            bkTree.addWord(match.getMatch());

            //NÃO REMOVER O TRECHO COMENTADO ABAIXO,
            //TODO: ARRUMAR UMA MANEIRA ADEQUADA DE FAZER ISSO, PROVAVELMENTE POVOANDO TUDO E RETIRANDO O Q EXCEDER
            /*nodesReturnedCount++;
            if (nodesReturnedCount >= maxNodesAllowed){
                break;
            }*/
        }
        return bkTree;
    }

    public void addWord(String word){
        this.bkTree.add(word);
    }
}
