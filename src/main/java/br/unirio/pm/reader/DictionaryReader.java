package br.unirio.pm.reader;

import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.tree.BurkhardKellerTree;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import edu.gatech.gtri.bktree.*;
import edu.gatech.gtri.bktree.BkTreeSearcher.Match;
import java.util.Set;


/**
 * this class defines the dictionary
 * 
 * @autor Daniel Villaça
 */
public class DictionaryReader {

    public static final char HYFEN = '-';

    /**
     * @param sourceFile  archive path
     * @param calculator the value calculated to the distance between 2 keyboard keys
     */
    
    public BurkhardKellerTree loadFromFile(String sourceFile, IDistanceCalculator calculator) {
        try {
            //read the zip archive
            FileInputStream fis = new FileInputStream(sourceFile);
            //Convert to ZipInputStream
            ZipInputStream zipIn = new ZipInputStream( fis );

            ZipEntry entry = zipIn.getNextEntry();

            StringBuilder word = new StringBuilder();
            char letterRead;

            
            while (zipIn.available() > 0)
            {
                BurkhardKellerTree bkTree = new BurkhardKellerTree(calculator);

                letterRead = (char) zipIn.read();

                if(!(Character.isLetter(letterRead)) && (Character.valueOf(letterRead) != Character.valueOf(HYFEN))){
                    bkTree.addWord(word.toString());
                    word.setLength(0);
                }
                else {
                    word.append(Character.toUpperCase(letterRead));
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();

        }


        return null;
    }
}
