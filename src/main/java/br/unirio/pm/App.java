package br.unirio.pm;

import br.unirio.pm.distance.DemerauLevenshteinCalculator;
import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.distance.LevenshteinCalculator;
import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutList;
import br.unirio.pm.model.KeyboardLayoutNeutro;
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
        layout.prepareDistances();
        KeyboardLayout layoutNeutro = new KeyboardLayoutNeutro();
        /*System.out.println(layout.getName());
        for (KeyboardLine line : layout.getLines()){
            System.out.println("Offset: " + line.getOffset());
            line.printCharacters();
            System.out.println();
        }*/



        IDistanceCalculator measurerL = new LevenshteinCalculator(layout);
        IDistanceCalculator measurerLNeutro = new LevenshteinCalculator(layoutNeutro);
        IDistanceCalculator measurerD = new DemerauLevenshteinCalculator(layout);
        IDistanceCalculator measurerDNeutro = new DemerauLevenshteinCalculator(layoutNeutro);

        /*
        System.out.println(measurerL.calculateDistance("teste", "xablau"));
        System.out.println(measurerLNeutro.calculateDistance("teste", "xablau"));
        System.out.println(measurerD.calculateDistance("teste", "xablau"));
        System.out.println(measurerDNeutro.calculateDistance("teste", "xablau"));
        */

        double distance = measurerL.calculateDistance("casa", "asa");
        System.out.println(distance);
        System.out.println( Math.ceil(distance) );

    }
}

