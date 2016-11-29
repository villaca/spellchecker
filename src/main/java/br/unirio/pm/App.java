package br.unirio.pm;

import br.unirio.pm.distance.DemerauLevenshteinCalculator;
import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.distance.LevenshteinCalculator;
import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutList;
import br.unirio.pm.model.KeyboardLine;
import br.unirio.pm.reader.DictionaryReader;
import br.unirio.pm.reader.KeyboardLayoutReader;

/**
 * Main class
 * 
 * @autor Daniel Villaça
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



        IDistanceCalculator measurerL = new LevenshteinCalculator(layout);

        //IDistanceCalculator measurerD = new DemerauLevenshteinCalculator(layout);

        System.out.println(measurerL.calcula("teste", "xablau"));
        //System.out.println(measurerD.distance("teste", "xablau"));

    }
}

