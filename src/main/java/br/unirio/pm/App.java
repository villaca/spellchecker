package br.unirio.pm;

import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutList;
import br.unirio.pm.model.KeyboardLine;
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
        KeyboardLayoutList layouts = new KeyboardLayoutReader().loadFromFile("data/KeyboardLayouts.xml");

        /*for(KeyboardLayout layout : layouts){
            System.out.println(layout.getName());
            for (KeyboardLine line : layout.getLines()){
                System.out.println("Offset: " + line.getOffset());
                line.printCharacters();
                System.out.println();
            }
        }*/

        KeyboardLayout layout = layouts.getLayoutByName("qwerty");
        /*System.out.println(layout.getName());
        for (KeyboardLine line : layout.getLines()){
            System.out.println("Offset: " + line.getOffset());
            line.printCharacters();
            System.out.println();
        }*/

        System.out.println(layout.getNominalDistance('w','a'));
        System.out.println("expected: " + Math.sqrt(0.5 * 0.5 + 1 * 1));

    }
}

