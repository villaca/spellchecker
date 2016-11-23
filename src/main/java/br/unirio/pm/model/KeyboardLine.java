/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unirio.pm.model;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

/**
 * Classe que representas as linhas do teclado
 * 
 * @author Gabriel Teixeira 
 * @version 22/11/2016
 */
public class KeyboardLine {
    float offset;
    private ArrayList <Character> line;
    
    /**
     * @param lineValue  linha do teclado
     */
    public KeyboardLine(String lineValue){
        this.line = new ArrayList<Character>();
        for(int i = 0; i < lineValue.length(); i++){
            line.add(lineValue.charAt(i));
        }
        this.offset = 0;
    }

    /**
     * @param lineValue  linha do teclado
     * @param offset offset da linha do teclado definida
     */
    public KeyboardLine(String lineValue, float offset){
        this.line = new ArrayList<Character>();
        for(int i=0; i<lineValue.length();i++){
            line.add(lineValue.charAt(i));
        }
        this.offset = offset;
    }

    public void PrintCharacters(){
        for(Character c : line)
            System.out.print(c);
    }

    public float getOffset() {
        return offset;
    }

    /**
     * @param offset offset da  linha do teclado
     */
    public void setOffset(float offset) {
        this.offset = offset;
    }

    /**
     * @param c  ? acho uma boa trocar o nome dessa variavel
     */
    public boolean HasChar(char c){
        return line.contains(Character.toUpperCase(c));
    }

    /**
     * @param c  ? acho uma boa trocar o nome dessa variavel
     */
    public int CharPosition(char c){
        return line.indexOf(Character.toUpperCase(c));
    }

    
}
