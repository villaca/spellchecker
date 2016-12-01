package br.unirio.pm.reader;

import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.tree.BurkhardKellerTree;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * this class defines the dictionary
 * 
 * @author Daniel Villaça
 */
public class DictionaryReader {

    // we will use this char to define the words which contains "-"
    public static final char HYFEN = '-';

    /**
     * open the dictionary file and put in a BK tree
     * 
     * @param sourceFile  archive path
     * @param calculator the value calculated to the distance between 2 keyboard keys
     * 
     * @return the BK tree with the words required
     */
    
    public BurkhardKellerTree loadFromFile(String sourceFile, IDistanceCalculator calculator) {

        BurkhardKellerTree bkTree = new BurkhardKellerTree(calculator);
        try {
            //read the zip archive
            FileInputStream fis = new FileInputStream(sourceFile);
            //Convert to ZipInputStream
            ZipInputStream zipIn = new ZipInputStream( fis );

            ZipEntry entry = zipIn.getNextEntry();

            StringBuilder word = new StringBuilder();
            char letterRead;

            //while exists entry in the archive
            while (zipIn.available() > 0)
            {
                letterRead = (char) zipIn.read();

                
                //if its a word, add to bktree
                if(!(Character.isLetter(letterRead)) && (Character.valueOf(letterRead) != Character.valueOf(HYFEN)))
                {
                    bkTree.add(word.toString());
                    word.setLength(0);
                }
                else 
                {
                    word.append(Character.toUpperCase(letterRead));
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

        return bkTree;
    }
}
