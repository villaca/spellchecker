package br.unirio.pm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * this class represents to us the keyboard layout
 * 
 * @author Daniel Villaça
 */
public class KeyboardLayout {

    private final static int ALPHABET_SIZE = 26;
    private String model;
    private ArrayList <KeyboardLine> lines;
    //private double[][] distanceMatrix;
    private HashMap<Character,HashMap<Character,Double>> distanceMatrix;

    public KeyboardLayout() {
        this.lines = new ArrayList<KeyboardLine>();
        this.distanceMatrix = new HashMap<Character,HashMap<Character,Double>>();
    }

    /**
     * nao entendi esse
     */
    public void prepareDistances() {
        for(KeyboardLine line : this.lines)
        {
            for(int i = 0; i < line.getLineLength(); i++)
            {
                HashMap<Character,Double> distancesFromChar = new HashMap<Character,Double>();

                for(KeyboardLine lineCompared : this.lines) 
                {
                    for (int j = 0; j < lineCompared.getLineLength(); j++) 
                    {
                        distancesFromChar.put(lineCompared.getChar(j),
                                this.calculateDistance(line.getChar(i), lineCompared.getChar(j)));
                    }
                }
                this.distanceMatrix.put(line.getChar(i), distancesFromChar);
            }
        }
    }

    /**
     * calculates te distance between 2 keys
     * 
     * @param key2  key to be compared
     * @param key1 key to be compared and get the distance between
     * 
     * @return the distance between 2 keys
     */
    private double calculateDistance(char key1, char key2) {

        int height1 = 0;
        float offset1 = 0;
        int position1 = 0;
        for (KeyboardLine line : this.lines){
            height1++;
            offset1 += line.getOffset();
            if(line.hasChar(key1))
            {
                position1 = line.charPosition(key1);
                break;
            }
        }

        int height2 = 0;
        float offset2 = 0;
        int position2 = 0;
        for (KeyboardLine line : this.lines){
            height2++;
            offset2 += line.getOffset();
            if(line.hasChar(key2))
            {
                position2 = line.charPosition(key2);
                break;
            }
        }

        if(height1 == height2)
        {
            return Math.abs(position1 - position2);
        }
        else 
        {
            double width = (position1 + offset1) - (position2 + offset2);
            double height = height1 - height2;
            return Math.sqrt(width * width + height * height);
        }
    }

    /**
     * 
     * @param key2  key to be compared
     * @param key1 key to be compared and get the distance between
     * 
     * @return the nominal distance
     */
    public double getNominalDistance(char key1, char key2){
        return this.distanceMatrix.get(Character.toUpperCase(key1)).get(Character.toUpperCase(key2));
    }
    
    /**
     * @param model  Keyboard Layout model
     */
    public void setModel(String model){
        this.model = model;
    }
    
    public String getModel(){
        return model;
    }
    
    /**
     * defines the keyboard lines
     * 
     * @param line  Keyboard line
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

    /**
     * 
     * @return the maximun distance of one key to another
     */
    public double getMaximumDistance(){
        double maximumDistance = 0;
        double distanceFromOrigin = 0;
        double offsetTemp = this.lines.get(0).getOffset();
        char firstKey = this.lines.get(0).getChar(0);

        for(KeyboardLine line : this.lines)
        {
            if(line.getOffset() < offsetTemp)
            {
                firstKey = line.getChar(0);
            }
        }

        for(KeyboardLine line : this.lines){
            distanceFromOrigin = getNominalDistance(firstKey, line.getChar(line.getLineLength() - 1));
            if(distanceFromOrigin > maximumDistance)
            {
                maximumDistance = distanceFromOrigin;
            }
        }

        return maximumDistance;
    }

    /**
     * calculates the relative distance between 2 keys using (nominal distance / maximun distance) 
     * 
     * @param key1 key of the keyboard
     * @param key2 key of the keyboard
     * @return the relative distance between 2 keys
     */
    public double getRelativeDistance(char key1, char key2){
        if((key1 == '-') || (key2 == '-')){
            return 1;
        }
        return this.getNominalDistance(key1, key2) / this.getMaximumDistance();
    }
}

