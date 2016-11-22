package br.unirio.pm;

import br.unirio.pm.reader.DictionaryReader;
import br.unirio.pm.reader.KeyboardLayoutReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        KeyboardLayoutReader lay = new KeyboardLayoutReader();
        lay.lerXML("s");
                
        System.out.println( "Hello World!" );
    }
}
