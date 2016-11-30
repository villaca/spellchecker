package br.unirio.pm.tree;

import br.unirio.pm.distance.DemerauLevenshteinCalculator;
import br.unirio.pm.distance.IDistanceCalculator;
import br.unirio.pm.distance.LevenshteinCalculator;
import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutNeutro;
import br.unirio.pm.reader.KeyboardLayoutReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Daniel Villaça
 */
public class TestBurkhardKellerTree {

    private static String[] words = { "book", "books", "cake", "boo", "cape", "cart", "boon", "cook"};;
    private static BurkhardKellerTree bkTreeLeven, bkTreeDemer;

    /**
     *  example tree taken from:
     *  https://nullwords.wordpress.com/2013/03/13/the-bk-tree-a-data-structure-for-spell-checking/
     *
     *                                book
     *                         (1) /        \ (4)
     *                      books            cake
     *                  (2) /           (1) /   \ (2)
     *                  boo             cape    cart
     *             (1)/    \(2)
     *             boon    cook
     *
     */


    @BeforeClass
    public static void setup(){

        KeyboardLayout layout = new KeyboardLayoutNeutro();
        IDistanceCalculator levenshteinCalculator = new LevenshteinCalculator(layout);
        IDistanceCalculator demerauCalculator = new DemerauLevenshteinCalculator(layout);

        bkTreeLeven = new BurkhardKellerTree(levenshteinCalculator);
        bkTreeDemer = new BurkhardKellerTree(demerauCalculator);

        for(String word : words){
            bkTreeLeven.add(word);
            bkTreeDemer.add(word);
        }
    }


    @Test
    public void testSearch(){

        //Choice for ArrayList<String> justified by the "contains" method
        ArrayList<String> target = new ArrayList<String>();
        target.add("CAKE");
        target.add("CAPE");

        BurkhardKellerTreeSearchResult searchResultLeven =
                bkTreeLeven.search("CAQE",1,10);
        BurkhardKellerTreeSearchResult searchResultDemer =
                bkTreeDemer.search("CAQE",1,10);


        for(String match : searchResultLeven.getMatches()){
            assertTrue(target.contains(match));
        }
        for(String match : searchResultDemer.getMatches()){
            assertTrue(target.contains(match));
        }
    }

    /**
     * the add method is beind used on setup
     * tests if created tree is what was expected
     */
    @Test
    public void testAdd() {
        String targetTree = "{ BOOK { BOOKS { BOO { BOON }{ COOK }}}{ CAKE { CAPE }{ CART }}}";

        assertTrue(targetTree.equals(bkTreeLeven.toString()));
        assertTrue(targetTree.equals(bkTreeDemer.toString()));
    }

    /**
     * tests if, desconsidering keyboard distances, the floating point distance of the words
     *  is beind approximated to its correct integer value
     */
    @Test
    public void testTruncateDistance(){

        assertEquals(1, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOOK", "BOOKS")
                ));
        assertEquals(4, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOOK", "CAKE")
                ));
        assertEquals(2, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOOKS", "BOO")
                ));
        assertEquals(1, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("CAKE", "CAPE")
                ));
        assertEquals(2, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("CAKE", "CART")
                ));
        assertEquals(1, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOO", "BOON")
                ));
        assertEquals(2, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOO", "COOK")
                ));



        assertEquals(1, bkTreeDemer.truncateDistance(
                bkTreeDemer.getCalculator().calculateDistance("BOOK", "BOOKS")
        ));
        assertEquals(4, bkTreeDemer.truncateDistance(
                bkTreeDemer.getCalculator().calculateDistance("BOOK", "CAKE")
        ));
        assertEquals(2, bkTreeDemer.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOOKS", "BOO")
        ));
        assertEquals(1, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("CAKE", "CAPE")
        ));
        assertEquals(2, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("CAKE", "CART")
        ));
        assertEquals(1, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOO", "BOON")
        ));
        assertEquals(2, bkTreeLeven.truncateDistance(
                bkTreeLeven.getCalculator().calculateDistance("BOO", "COOK")
        ));
    }

    /**
     *  tests if the children of a node are always the expected nodes
     */
    @Test
    public void testGetChildren() {

        String targetFirstChild = "{ BOOKS { BOO { BOON }{ COOK }}}";
        String targetSecondChild = "{ CAKE { CAPE }{ CART }}";
        String targetFirstGrandchild = "{ BOO { BOON }{ COOK }}";
        String targetSecondGrandchild = "{ CAPE }";
        String targetThirdGrandchild = "{ CART }";
        String targetFirstGreatGrandchild = "{ BOON }";
        String targetSecondGreatGrandchild = "{ COOK }";


        assertTrue(targetFirstChild.equals(bkTreeLeven.getChildren().get(1).toString()));
        assertTrue(targetSecondChild.equals(bkTreeLeven.getChildren().get(4).toString()));
        assertTrue(targetFirstGrandchild.equals(
                bkTreeLeven.getChildren().get(1).getChildren().get(2).toString()
        ));
        assertTrue(targetSecondGrandchild.equals(
                bkTreeLeven.getChildren().get(4).getChildren().get(1).toString()
        ));
        assertTrue(targetThirdGrandchild.equals(
                bkTreeLeven.getChildren().get(4).getChildren().get(2).toString()
        ));
        assertTrue(targetFirstGreatGrandchild.equals(
                bkTreeLeven.getChildren().get(1).getChildren().get(2).getChildren().get(1).toString()
        ));
        assertTrue(targetSecondGreatGrandchild.equals(
                bkTreeLeven.getChildren().get(1).getChildren().get(2).getChildren().get(2).toString()
        ));


        assertTrue(targetFirstChild.equals(bkTreeDemer.getChildren().get(1).toString()));
        assertTrue(targetSecondChild.equals(bkTreeDemer.getChildren().get(4).toString()));
        assertTrue(targetFirstGrandchild.equals(
                bkTreeDemer.getChildren().get(1).getChildren().get(2).toString()
        ));
        assertTrue(targetSecondGrandchild.equals(
                bkTreeDemer.getChildren().get(4).getChildren().get(1).toString()
        ));
        assertTrue(targetThirdGrandchild.equals(
                bkTreeDemer.getChildren().get(4).getChildren().get(2).toString()
        ));
        assertTrue(targetFirstGreatGrandchild.equals(
                bkTreeDemer.getChildren().get(1).getChildren().get(2).getChildren().get(1).toString()
        ));
        assertTrue(targetSecondGreatGrandchild.equals(
                bkTreeDemer.getChildren().get(1).getChildren().get(2).getChildren().get(2).toString()
        ));


    }


}