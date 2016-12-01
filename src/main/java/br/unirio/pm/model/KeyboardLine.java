package br.unirio.pm.model;

import java.util.ArrayList;

/**
 * this class represents the keyboard lines of a specific keyboard
 * 
 * @author Gabriel Teixeira
 */
public class KeyboardLine {
    private float offset;
    private ArrayList <Character> line;
    

    /**
     * 
     * @param lineValue 
     */
    public KeyboardLine(String lineValue){
        this.line = new ArrayList<Character>();
        for(int i = 0; i < lineValue.length(); i++)
            line.add(lineValue.charAt(i));
        
        this.offset = 0;
    }

    /**
     * add the characters in a line with its offset
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

    /**
     * print the characters 
     */
    public void printCharacters(){
        for(Character c : line)
        {
            System.out.print(c);
        }
    }

    /**
     * 
     * @return the offset of the line 
     */
    public float getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset the offset of the line 
     */
    public void setOffset(float offset) {
        this.offset = offset;
    }

    /**
     * indentify the line which contains the character key
     * @param characterKey  the character of the key
     */
    public boolean hasChar(char characterKey){
        return line.contains(Character.toUpperCase(characterKey));
    }

    /**
     * indentify the index of line which contains the character key
     * @param characterKey  the character of the key
     * 
     * @return the index of the character
     */
    public int charPosition(char characterKey){
        return line.indexOf(Character.toUpperCase(characterKey));
    }

    /**
     * 
     * @param position position of the character wanted
     * 
     * @return  the character in the given position
     */
    public char getChar(int position){
        return this.line.get(position);
    }

    /**
     * 
     * @return the length of the line 
     */
    public int getLineLength(){
        return this.line.size();
    }

    
}
