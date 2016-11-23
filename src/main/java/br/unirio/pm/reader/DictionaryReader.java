package br.unirio.pm.reader;

import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.tree.BurkhardKellerTree;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Classe utilizada para ler e definir o arquivo dicionario
 * 
 * @autor Daniel Villaça 
 * @version 18/11/2016.
 */
public class DictionaryReader {
    
    /**
     * @param sourceFile  caminho do arquivo
     * @param calculator ?
     */
    public BurkhardKellerTree loadFromFile(String sourceFile, IDistanceCalculator calculator) {
       
        OpenDictionaryZIP(sourceFile);
        return null;
    }
    
    /**
     * @param sourceFile caminho do arquivo
     */
    public void OpenDictionaryZIP(String sourceFile){
         try {
            //Le o arquivo zip
            FileInputStream fis = new FileInputStream(sourceFile);
            //Converte para ZipInputStream
            ZipInputStream zipIn = new ZipInputStream( fis );
            
            ZipEntry entry = zipIn.getNextEntry();
            
            //Nessa parte ja se le o arquivor, temos que armazenalo em algum lugar certo?
            while (zipIn.available() > 0)
            {
                System.out.print( (char) zipIn.read() );
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
        }
    }
}
