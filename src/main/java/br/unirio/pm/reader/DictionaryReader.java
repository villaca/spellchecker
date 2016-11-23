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
 * Classe utilizada para ler e definir o arquivo dicionario
 * 
 * @autor Daniel Villaça 
 * @version 18/11/2016.
 */
public class DictionaryReader {

    public static final char HYFEN = '-';

    /**
     * @param sourceFile  caminho do arquivo
     * @param calculator ?
     */
    
    public BurkhardKellerTree loadFromFile(String sourceFile, IDistanceCalculator calculator) {
        try {
            //Le o arquivo zip
            FileInputStream fis = new FileInputStream(sourceFile);
            //Converte para ZipInputStream
            ZipInputStream zipIn = new ZipInputStream( fis );

            ZipEntry entry = zipIn.getNextEntry();

            StringBuilder word = new StringBuilder();
            char letterRead;

            //Nessa parte ja se le o arquivo, temos que armazenalo em algum lugar certo?
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
    
    /**
     * @param sourceFile caminho do arquivo
     */
    public void OpenDictionaryZIP(String sourceFile){

    }
}
