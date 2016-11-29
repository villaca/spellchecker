package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * this class represents the keyboard lines of a specific keyboard
 * 
 * @author Gabriel Teixeira
 */
public class KeyboardLine {
    float offset;
    private ArrayList <Character> line;
    

    public KeyboardLine(String lineValue){
        this.line = new ArrayList<Character>();
        for(int i = 0; i < lineValue.length(); i++)
            line.add(lineValue.charAt(i));
        
        this.offset = 0;
    }

    /**
     * NAO SEI COMO EXPLICAR ESSE METODO
     * 
     * @param lineValue  keyboard line
     * @param offset offset keyboard line
     */
    public KeyboardLine(String lineValue, float offset){
        this.line = new ArrayList<Character>();
        for(int i=0; i<lineValue.length();i++)
        {
            line.add(lineValue.charAt(i));
        }
        this.offset = offset;
    }

    public void printCharacters(){
        for(Character c : line)
        {
            System.out.print(c);
        }
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    /**
     * @param c  ? acho uma boa trocar o nome dessa variavel
     */
    public boolean hasChar(char c){
        return line.contains(Character.toUpperCase(c));
    }

    /**
     * @param c  ? acho uma boa trocar o nome dessa variavel
     */
    public int charPosition(char c){
        return line.indexOf(Character.toUpperCase(c));
    }

    public char getChar(int position){
        return this.line.get(position);
    }

    public int getLineLength(){
        return this.line.size();
    }

    
}
