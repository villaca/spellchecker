package br.unirio.pm.tree;

import br.unirio.pm.distance.IDistanceCalculator;

import java.util.HashMap;

/**
 * this class is used to do the search in the BK Tree
 * 
 * @autor Daniel Villaça
 */
public class BurkhardKellerTreeSearchResult extends BurkhardKellerTree {

    public BurkhardKellerTreeSearchResult(IDistanceCalculator calculator) {
        super(calculator);
    }

    public BurkhardKellerTreeSearchResult(IDistanceCalculator calculator, String root) {
        super(calculator, root);
    }

    //TODO: implementar geral
    
    

    public String getWord(int position) {

        return "";
    }

    public double getDistance(int position) {
        return 0;
    }

    public int getPosition(String word){

        int position = 0;

        if(!this.root.equals(word)){
            return position;
        }
        else{
            int distance = this.truncateDistance(this.calculator.calcula(this.root, word));
            int depth = 1;
            BurkhardKellerTree target = this.children.get(distance);

            while(!(target.getRoot().equals(word))){
                depth++;
                target = target.children.get(distance);
            }
// TODO: JÁ TENHO A PROFUNDIDADE DO NÓ, FALTA PERCORRER CADA NÍVEL DA ÁRVORE ARMAZENDO A QUANTIDADE DE NÓS PRESENTES EM CADA UM

        }

        return position;
    }



}
