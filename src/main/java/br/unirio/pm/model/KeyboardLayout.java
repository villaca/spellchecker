package br.unirio.pm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Classe que representa o tipo do teclado
 * 
 * @author Daniel Villaça
 */
public class KeyboardLayout {

    private final static int ALPHABET_SIZE = 26;
    private String name;
    private ArrayList <KeyboardLine> lines;
    //private double[][] distanceMatrix;
    private HashMap<Character,HashMap<Character,Double>> distanceMatrix;

    public KeyboardLayout() {
        this.lines = new ArrayList<KeyboardLine>();
        this.distanceMatrix = new HashMap<Character,HashMap<Character,Double>>();
    }

    public void prepareDistances() {
        for(KeyboardLine line : this.lines){
            for(int i = 0; i < line.getLineLength(); i++){
                HashMap<Character,Double> distancesFromChar = new HashMap<Character,Double>();

                for(KeyboardLine lineCompared : this.lines) {
                    for (int j = 0; j < lineCompared.getLineLength(); j++) {
                        distancesFromChar.put(lineCompared.getChar(j),
                                this.calculateDistance(line.getChar(i), lineCompared.getChar(j)));
                    }
                }
                this.distanceMatrix.put(line.getChar(i), distancesFromChar);
            }
        }
    }

    /**
     * @param q1  ? acho uma boa mudar o nome dessas variaveis
     * @param q
     */
    private double calculateDistance(char q, char q1) {

        int height1 = 0;
        float offset1 = 0;
        int position1 = 0;
        for (KeyboardLine line : this.lines){
            height1++;
            offset1 += line.getOffset();
            if(line.hasChar(q)){
                position1 = line.charPosition(q);
                break;
            }
        }

        int height2 = 0;
        float offset2 = 0;
        int position2 = 0;
        for (KeyboardLine line : this.lines){
            height2++;
            offset2 += line.getOffset();
            if(line.hasChar(q1)){
                position2 = line.charPosition(q1);
                break;
            }
        }

        if(height1 == height2){
            return Math.abs(position1 - position2);
        }
        else {
            double width = (position1 + offset1) - (position2 + offset2);
            double height = height1 - height2;
            return Math.sqrt(width * width + height * height);
        }
    }

    public double getNominalDistance(char key1, char key2){
        return this.distanceMatrix.get(Character.toUpperCase(key1)).get(Character.toUpperCase(key2));
    }
    
    /**
     * @param name  nome do model do teclado
     */
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    /**
     * @param line  linha do teclado
     */
    public void AddKeyboardLine(KeyboardLine line){
        this.lines.add(line);
    }

    public ArrayList<KeyboardLine> getLines() {
        return lines;
    }

    public double getInsertDeleteDistance() {
        return 0.25;
    }

    public double getMaximumDistance(){
        double maximumDistance = 0;
        double distanceFromOrigin = 0;
        double offsetTemp = this.lines.get(0).getOffset();
        char firstKey = this.lines.get(0).getChar(0);

        for(KeyboardLine line : this.lines){
            if(line.getOffset() < offsetTemp){
                firstKey = line.getChar(0);
            }
        }

        for(KeyboardLine line : this.lines){
            distanceFromOrigin = getNominalDistance(firstKey, line.getChar(line.getLineLength() - 1));
            if(distanceFromOrigin > maximumDistance){
                maximumDistance = distanceFromOrigin;
            }
        }

        return maximumDistance;
    }
}

