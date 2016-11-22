package br.unirio.pm.model;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class KeyboardLayout {
    
    private String name;
    
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
}
