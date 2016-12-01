package br.unirio.pm.tree;

import br.unirio.pm.distance.DemerauLevenshteinCalculator;
import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.distance.LevenshteinCalculator;
import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutList;
import br.unirio.pm.model.KeyboardLayoutNeutro;
import br.unirio.pm.reader.DictionaryReader;
import br.unirio.pm.reader.KeyboardLayoutReader;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  @author Daniel Villaça
 */
public class TestBurkhardKellerTreeSearchResult {

    private static BurkhardKellerTreeSearchResult
                        result1LevenNeutral, result2LevenNeutral, result3LevenNeutral,
                        result1LevenQWERTY, result2LevenQWERTY, result3LevenQWERTY,
                        result1LevenDVORAK, result2LevenDVORAK,
                        result1DemerNeutral, result2DemerNeutral, result3DemerNeutral,
                        result1DemerQWERTY, result2DemerQWERTY, result3DemerQWERTY;

    @BeforeClass
    public static void setup(){

        KeyboardLayoutList layouts = new KeyboardLayoutReader().loadFromFile("data/keyboardlayouts.xml");

        for (KeyboardLayout layout : layouts)
            layout.prepareDistances();

        KeyboardLayout layoutNeutro = new KeyboardLayoutNeutro();
        KeyboardLayout layoutQWERTY = layouts.getLayoutByName("QWERTY");
        KeyboardLayout layoutDVORAK = layouts.getLayoutByName("DVORAK");

        IDistanceCalculator levenshteinCalculatorNeutral = new LevenshteinCalculator(layoutNeutro);
        IDistanceCalculator levenshteinCalculatorQWERTY = new LevenshteinCalculator(layoutQWERTY);
        IDistanceCalculator levenshteinCalculatorDVORAK = new LevenshteinCalculator(layoutDVORAK);
        IDistanceCalculator demerauCalculatorNeutral = new DemerauLevenshteinCalculator(layoutNeutro);
        IDistanceCalculator demerauCalculatorQWERTY = new DemerauLevenshteinCalculator(layoutQWERTY);

        BurkhardKellerTree bkTreeLevenNeutral = new DictionaryReader().loadFromFile("data/dictionary_pt-br.zip",levenshteinCalculatorNeutral);
        BurkhardKellerTree bkTreeLevenQWERTY = new DictionaryReader().loadFromFile("data/dictionary_pt-br.zip",levenshteinCalculatorQWERTY);
        BurkhardKellerTree bkTreeLevenDVORAK = new DictionaryReader().loadFromFile("data/dictionary_pt-br.zip",levenshteinCalculatorDVORAK);
        BurkhardKellerTree bkTreeDemerNeutral = new DictionaryReader().loadFromFile("data/dictionary_pt-br.zip",demerauCalculatorNeutral);
        BurkhardKellerTree bkTreeDemerQWERTY = new DictionaryReader().loadFromFile("data/dictionary_pt-br.zip",demerauCalculatorQWERTY);


        result1LevenNeutral = bkTreeLevenNeutral.search("casa",1, 10);
        result2LevenNeutral = bkTreeLevenNeutral.search("cervega",2, 10);
        result3LevenNeutral = bkTreeLevenNeutral.search("aviae",2, 10);

        result1LevenQWERTY = bkTreeLevenQWERTY.search("casa",1, 10);
        result2LevenQWERTY = bkTreeLevenQWERTY.search("cervega",2, 10);
        result3LevenQWERTY = bkTreeLevenQWERTY.search("aviae",2, 10);

        result1LevenDVORAK = bkTreeLevenDVORAK.search("casa",1, 10);
        result2LevenDVORAK = bkTreeLevenDVORAK.search("cervega",2, 10);

        result1DemerNeutral = bkTreeDemerNeutral.search("casa",1, 10);
        result2DemerNeutral = bkTreeDemerNeutral.search("cervega",2, 10);
        result3DemerNeutral = bkTreeDemerNeutral.search("aviae",2, 10);

        result1DemerQWERTY = bkTreeDemerQWERTY.search("casa",1, 10);
        result2DemerQWERTY = bkTreeDemerQWERTY.search("cervega",2, 10);
        result3DemerQWERTY = bkTreeDemerQWERTY.search("aviae",2, 10);

    }



    @Test
    public void testSortLevenNeutral(){
        for(int i = 0; i < result1LevenNeutral.getMatches().size() - 1;i++){
            assertTrue(result1LevenNeutral.getDistance(i) <= result1LevenNeutral.getDistance(i+1));
            if(result1LevenNeutral.getDistance(i) == result1LevenNeutral.getDistance(i + 1) ){
                int comparisonResult =
                        result1LevenNeutral.getWord(i).compareTo( result1LevenNeutral.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
        for(int i = 0; i < result2LevenNeutral.getMatches().size() - 1;i++){
            assertTrue(result2LevenNeutral.getDistance(i) <= result2LevenNeutral.getDistance(i+1));
            if(result2LevenNeutral.getDistance(i) == result2LevenNeutral.getDistance(i + 1) ){
                int comparisonResult =
                        result2LevenNeutral.getWord(i).compareTo( result2LevenNeutral.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }

        for(int i = 0; i < result3LevenNeutral.getMatches().size() - 1;i++){
            assertTrue(result3LevenNeutral.getDistance(i) <= result3LevenNeutral.getDistance(i+1));
            if(result3LevenNeutral.getDistance(i) == result3LevenNeutral.getDistance(i + 1) ){
                int comparisonResult =
                        result3LevenNeutral.getWord(i).compareTo( result3LevenNeutral.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }


    }
    @Test
    public void testSortLevenQWERTY(){
        for(int i = 0; i < result1LevenQWERTY.getMatches().size() - 1;i++){
            assertTrue(result1LevenQWERTY.getDistance(i) <= result1LevenQWERTY.getDistance(i+1));
            if(result1LevenQWERTY.getDistance(i) == result1LevenQWERTY.getDistance(i + 1) ){
                int comparisonResult =
                        result1LevenQWERTY.getWord(i).compareTo( result1LevenQWERTY.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
        for(int i = 0; i < result2LevenQWERTY.getMatches().size() - 1;i++){
            assertTrue(result2LevenQWERTY.getDistance(i) <= result2LevenQWERTY.getDistance(i+1));
            if(result2LevenQWERTY.getDistance(i) == result2LevenQWERTY.getDistance(i + 1) ){
                int comparisonResult =
                        result2LevenQWERTY.getWord(i).compareTo( result2LevenQWERTY.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }

        for(int i = 0; i < result3LevenQWERTY.getMatches().size() - 1;i++){
            assertTrue(result3LevenQWERTY.getDistance(i) <= result3LevenQWERTY.getDistance(i+1));
            if(result3LevenQWERTY.getDistance(i) == result3LevenQWERTY.getDistance(i + 1) ){
                int comparisonResult =
                        result3LevenQWERTY.getWord(i).compareTo( result3LevenQWERTY.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }

    }

   @Test
    public void testSortLevenDVORAK(){
        for(int i = 0; i < result1LevenDVORAK.getMatches().size() - 1;i++){
            assertTrue(result1LevenDVORAK.getDistance(i) <= result1LevenDVORAK.getDistance(i+1));
            if(result1LevenDVORAK.getDistance(i) == result1LevenDVORAK.getDistance(i + 1) ){
                int comparisonResult =
                        result1LevenDVORAK.getWord(i).compareTo( result1LevenDVORAK.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
        for(int i = 0; i < result2LevenDVORAK.getMatches().size() - 1;i++){
            assertTrue(result2LevenDVORAK.getDistance(i) <= result2LevenDVORAK.getDistance(i+1));
            if(result2LevenDVORAK.getDistance(i) == result2LevenDVORAK.getDistance(i + 1) ){
                int comparisonResult =
                        result2LevenDVORAK.getWord(i).compareTo( result2LevenDVORAK.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
    }

    @Test
    public void testSortDemerNeutral(){
        for(int i = 0; i < result1DemerNeutral.getMatches().size() - 1;i++){
            assertTrue(result1DemerNeutral.getDistance(i) <= result1DemerNeutral.getDistance(i+1));
            if(result1DemerNeutral.getDistance(i) == result1DemerNeutral.getDistance(i + 1) ){
                int comparisonResult =
                        result1DemerNeutral.getWord(i).compareTo( result1DemerNeutral.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
        for(int i = 0; i < result2DemerNeutral.getMatches().size() - 1;i++){
            assertTrue(result2DemerNeutral.getDistance(i) <= result2DemerNeutral.getDistance(i+1));
            if(result2DemerNeutral.getDistance(i) == result2DemerNeutral.getDistance(i + 1) ){
                int comparisonResult =
                        result2DemerNeutral.getWord(i).compareTo( result2DemerNeutral.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }

        for(int i = 0; i < result3DemerNeutral.getMatches().size() - 1;i++){
            assertTrue(result3DemerNeutral.getDistance(i) <= result3DemerNeutral.getDistance(i+1));
            if(result3DemerNeutral.getDistance(i) == result3DemerNeutral.getDistance(i + 1) ){
                int comparisonResult =
                        result3DemerNeutral.getWord(i).compareTo(result3DemerNeutral.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
    }
    @Test
    public void testSortDemerQWERTY(){
        for(int i = 0; i < result1DemerQWERTY.getMatches().size() - 1;i++){
            assertTrue(result1DemerQWERTY.getDistance(i) <= result1DemerQWERTY.getDistance(i+1));
            if(result1DemerQWERTY.getDistance(i) == result1DemerQWERTY.getDistance(i + 1) ){
                int comparisonResult =
                        result1DemerQWERTY.getWord(i).compareTo(result1DemerQWERTY.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
        for(int i = 0; i < result2DemerQWERTY.getMatches().size() - 1;i++){
            assertTrue(result2DemerQWERTY.getDistance(i) <= result2DemerQWERTY.getDistance(i+1));
            if(result2DemerQWERTY.getDistance(i) == result2DemerQWERTY.getDistance(i + 1) ){
                int comparisonResult =
                        result2DemerQWERTY.getWord(i).compareTo(result2DemerQWERTY.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }

        for(int i = 0; i < result3DemerQWERTY.getMatches().size() - 1;i++){
            assertTrue(result3DemerQWERTY.getDistance(i) <= result3DemerQWERTY.getDistance(i+1));
            if(result3DemerQWERTY.getDistance(i) == result3DemerQWERTY.getDistance(i + 1) ){
                int comparisonResult =
                        result3DemerQWERTY.getWord(i).compareTo(result3DemerQWERTY.getWord(i+1));
                assertTrue(comparisonResult < 0 );
            }
        }
    }



    @Test
    public void testLimitMatchesLevenNeutral() {
        assertTrue(result1LevenNeutral.getMatches().size() <= 10);
        assertTrue(result2LevenNeutral.getMatches().size() <= 10);
        assertTrue(result3LevenNeutral.getMatches().size() <= 10);
    }
    @Test
    public void testLimitMatchesLevenQWERTY() {
        assertTrue(result1LevenQWERTY.getMatches().size() <= 10);
        assertTrue(result2LevenQWERTY.getMatches().size() <= 10);
        assertTrue(result3LevenQWERTY.getMatches().size() <= 10);
    }
    @Test
    public void testLimitMatchesLevenDVORAK() {
        assertTrue(result1LevenDVORAK.getMatches().size() <= 10);
        assertTrue(result2LevenDVORAK.getMatches().size() <= 10);

    }
    @Test
    public void testLimitMatchesDemerNeutral() {
        assertTrue(result1DemerNeutral.getMatches().size() <= 10);
        assertTrue(result2DemerNeutral.getMatches().size() <= 10);
        assertTrue(result3DemerNeutral.getMatches().size() <= 10);
    }
    @Test
    public void testLimitMatchesDemerQWERTY() {
        assertTrue(result1DemerQWERTY.getMatches().size() <= 10);
        assertTrue(result2DemerQWERTY.getMatches().size() <= 10);
        assertTrue(result3DemerQWERTY.getMatches().size() <= 10);
    }

}