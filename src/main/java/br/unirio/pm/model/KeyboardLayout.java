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
            double width = 0;
            /*if( (offset1 > offset2) && (position1 > position2) ){
                width = Math.abs(position1 - position2) - Math.abs(offset1 - offset2);
            }
            else if((offset1 > offset2) && (position1 < position2)){
                width = Math.abs(position1 - position2) + Math.abs(offset1 - offset2);
            }
            else if( (offset1 < offset2) && (position1 > position2) ){
                width = Math.abs(position1 - position2) - Math.abs(offset1 - offset2);
            }
            else if((offset1 < offset2) && (position1 < position2)){
                width = Math.abs(position1 - position2) + Math.abs(offset1 - offset2);
            }
            else {
                width = Math.abs(position1 - position2) + Math.abs(offset1 - offset2);
            }*/

            width = Math.abs((position1 + offset1) - (position2 + offset2));
            //double width = Math.abs(position1 - position2) - Math.abs(offset1 - offset2);
            double height = Math.abs(height1 - height2);
            return Math.sqrt(width * width + height * height);
        }
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

