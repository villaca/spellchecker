<<<<<<< HEAD
package br.unirio.pm.reader;

import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.tree.BurkhardKellerTree;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class DictionaryReader {
    public BurkhardKellerTree loadFromFile(String s, IDistanceCalculator calculator) {
       
        return null;
    }
    
    /* Nao sei se isso ta certo, achei muito simples, vi um site com um so que mais complicado:
    http://imasters.com.br/artigo/1319/java/descompactando-arquivos-zip-com-o-java?trace=1519021197&source=single
    */
    public void abrirArquivoDicionario(){
         try {
            //Le o arquivo zip
            FileInputStream fis = new FileInputStream("data/dictionary_pt-br.zip");
            //Converte para ZipInputStream
            ZipInputStream zipIn = new ZipInputStream( fis );
            
            ZipEntry entry = zipIn.getNextEntry();
            
            while (zipIn.available() > 0)
            {
                //aqui tecnicamente ja le o arquivo
                System.out.print( (char) zipIn.read() );
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
        }
    }
}
=======
package br.unirio.pm.reader;

import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.tree.BurkhardKellerTree;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Daniel Villaça on 18/11/2016.
 */
public class DictionaryReader {
    public BurkhardKellerTree loadFromFile(String s, IDistanceCalculator calculator) {
       
        return null;
    }
    
    /* Nao sei se isso ta certo, achei muito simples, vi um site com um so que mais complicado:
    http://imasters.com.br/artigo/1319/java/descompactando-arquivos-zip-com-o-java?trace=1519021197&source=single
    */
    private void abrirArquivoDicionario(){
         try {
            //Le o arquivo zip
            FileInputStream fis = new FileInputStream("data/dictionary_pt-br.zip");
            //Converte para ZipInputStream
            ZipInputStream zipIn = new ZipInputStream( fis );
            
            ZipEntry entry = zipIn.getNextEntry();
            
            while (zipIn.available() > 0)
            {
                //aqui tecnicamente ja le o arquivo
                System.out.print( (char) zipIn.read() );
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
        }
    }
}
>>>>>>> origin/master
