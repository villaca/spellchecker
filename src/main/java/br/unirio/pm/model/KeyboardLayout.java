package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class KeyboardLayout {
    
    private String name;
    private ArrayList <KeyboardLine> lines;

    public KeyboardLayout() {
        lines = new ArrayList<KeyboardLine>();
    }

    public void prepareDistances() {
    }

    public double getNominalDistance(char q, char q1) {
        return 0;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void addLine(KeyboardLine line){
        this.lines.add(line);
    }

    public ArrayList<KeyboardLine> getLines() {
        return lines;
    }

    public double getInsertDeleteDistance() {

        return 0;
    }

    public double getMaximumDistance() {
        return 0;
    }
}

