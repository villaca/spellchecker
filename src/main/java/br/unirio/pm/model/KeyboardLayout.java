package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * Classe que representa o tipo do teclado
 * 
 * @autor Daniel Villaça 
 * @version 18/11/2016.
 */
public class KeyboardLayout {
    
    private String name;
    private ArrayList <KeyboardLine> lines;

    public KeyboardLayout() {
        lines = new ArrayList<KeyboardLine>();
    }

    public void PrepareDistances() {
    }

    /**
     * @param q1  ? acho uma boa mudar o nome dessas variaveis
     * @param q
     */
    public double getNominalDistance(char q, char q1) {

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

        return 0;
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

