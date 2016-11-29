package br.unirio.pm;

import br.unirio.pm.distance.DemerauLevenshteinCalculator;
import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.distance.LevenshteinCalculator;
import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutList;
import br.unirio.pm.model.KeyboardLayoutNeutro;
import br.unirio.pm.reader.DictionaryReader;
import br.unirio.pm.reader.KeyboardLayoutReader;
import br.unirio.pm.tree.BurkhardKellerTree;
import br.unirio.pm.tree.BurkhardKellerTreeSearchResult;

import java.util.Scanner;

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

        Scanner input = new Scanner(System.in);
        String keyboardName = "";

        System.out.print("Please input keyboard used (neutral for neutral): ");
        while(input.hasNext()){
            keyboardName = input.next();
            input.nextLine();
            break;
        }
        KeyboardLayout layout;
        if(keyboardName.toLowerCase().equals("neutral")){
            layout = new KeyboardLayoutNeutro();
        }
        else{
            layout = layouts.getLayoutByName(keyboardName);
            layout.prepareDistances();
        }


        IDistanceCalculator calculator = new LevenshteinCalculator(layout);
        System.out.print("Please input calculation method (1 for Levenshtein, 2 for Demerau-Levenshtein): ");
        while(input.hasNextInt()){
            int aux = input.nextInt();
            if(aux == 1){
                calculator = new LevenshteinCalculator(layout);
            }
            else if(aux == 2){
                calculator = new DemerauLevenshteinCalculator(layout);
            }
            else{
                System.out.println("Invalid calculation method! Terminating execution ...");
                System.exit(0);
            }
            input.nextLine();
            break;
        }


        BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary_pt-br.zip", calculator);
        BurkhardKellerTreeSearchResult result;

        int tolerance = 1;
        System.out.print("Please input the tolerance degree for the search: ");
        while(input.hasNextInt()){
            tolerance = input.nextInt();
            input.nextLine();
            break;
        }

        int maxNodes = 10;
        System.out.print("Please input how many answer at maximum you desire: ");
        while(input.hasNextInt()){
            maxNodes = input.nextInt();
            input.nextLine();
            break;
        }

        System.out.print("Please input the word you want to search (input 0 to end): ");
        String word = "";

        while(input.hasNext()){
            word = input.next();
            input.nextLine();
            if(word.equals("0")){
                break;
            }

            result = tree.search(word, tolerance, maxNodes);
            System.out.println("Best matches: ");
            for(String match : result.getMatches()){
                System.out.println(match + ", far by " + calculator.calculateDistance(word.toUpperCase(), match));

            }

            System.out.println();
            System.out.print("Please input the word you want to search (input 0 to end): ");
        }

        return;

    }
}

