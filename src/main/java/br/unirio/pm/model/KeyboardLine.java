/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unirio.pm.model;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

/**
 *
 * @author Gabriel Teixeira
 */
public class KeyboardLine {
    float offset;
    ArrayList <Character> line;
    
    public KeyboardLine(String lineValue){
        this.line = new ArrayList<Character>();
        for(int i = 0; i < lineValue.length(); i++){
            line.add(lineValue.charAt(i));
        }
        this.offset = 0;
    }

    public KeyboardLine(String lineValue, float offset){
        this.line = new ArrayList<Character>();
        for(int i=0; i<lineValue.length();i++){
            line.add(lineValue.charAt(i));
        }
        this.offset = offset;
    }

    public void printCharacters(){
        for(Character c : line)
            System.out.print(c);
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    
}
