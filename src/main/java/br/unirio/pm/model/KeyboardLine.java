/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unirio.pm.model;

import java.util.ArrayList;

/**
 *
 * @author Gabriel Teixeira
 */
public class KeyboardLine {
    float offset;
    ArrayList <Character> line;
    
    public KeyboardLine(String lineValue){
        for(int i=0; i<lineValue.length();i++){
            line.add(lineValue.charAt(i));
        }
        
    }
    
}
