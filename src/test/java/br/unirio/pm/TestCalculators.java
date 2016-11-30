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
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Daniel Villaça adapted from marciobarros
 */

public class TestCalculators {

    private static KeyboardLayoutList layouts;

    @BeforeClass
    public static void setup()
    {
        layouts = new KeyboardLayoutReader().loadFromFile("data/keyboardlayouts.xml");

        for (KeyboardLayout layout : layouts)
            layout.prepareDistances();
    }

    @Test
    public void testLevenshteintTecladoNeutro()
    {
        KeyboardLayout layout = new KeyboardLayoutNeutro();
        IDistanceCalculator calculator = new LevenshteinCalculator(layout);

        check(calculator, "casa", "casa", 0.0);
        check(calculator, "casa", "asa", 1.0);
        check(calculator, "casa", "cas", 1.0);
        check(calculator, "casa", "casar", 1.0);
        check(calculator, "casa", "casal", 1.0);
        check(calculator, "casa", "causa", 1.0);
        check(calculator, "casa", "casba", 1.0);
        check(calculator, "casa", "casca", 1.0);
        check(calculator, "casa", "casta", 1.0);
        check(calculator, "casa", "caca", 1.0);

        check(calculator, "cervega", "cerveja", 1.0);
        check(calculator, "cervega", "cereja", 2.0);
        check(calculator, "cervega", "corveta", 2.0);
        check(calculator, "cervega", "corveia", 2.0);
        check(calculator, "cervega", "certeza", 2.0);

        check(calculator, "aviea", "aia", 2.0);
        check(calculator, "aviea", "ave", 2.0);
        check(calculator, "aviea", "via", 2.0);
        check(calculator, "aviea", "avioes", 2.0);
        check(calculator, "aviea", "avisar", 2.0);
        check(calculator, "aviea", "avivar", 2.0);
        check(calculator, "aviea", "avidez", 2.0);
        check(calculator, "aviea", "alinea", 2.0);
        check(calculator, "aviea", "ravina", 2.0);
        check(calculator, "aviea", "vies", 2.0);
    }

    @Test
    public void testDemerauTecladoNeutro()
    {
        KeyboardLayout layout = new KeyboardLayoutNeutro();
        IDistanceCalculator calculator = new DemerauLevenshteinCalculator(layout);

        check(calculator, "casa", "casa", 0.0);
        check(calculator, "casa", "asa", 1.0);
        check(calculator, "casa", "cas", 1.0);
        check(calculator, "casa", "casar", 1.0);
        check(calculator, "casa", "casal", 1.0);
        check(calculator, "casa", "causa", 1.0);
        check(calculator, "casa", "casba", 1.0);
        check(calculator, "casa", "casca", 1.0);
        check(calculator, "casa", "casta", 1.0);
        check(calculator, "casa", "caca", 1.0);

        check(calculator, "cervega", "cerveja", 1.0);
        check(calculator, "cervega", "cereja", 2.0);
        check(calculator, "cervega", "corveta", 2.0);
        check(calculator, "cervega", "corveia", 2.0);
        check(calculator, "cervega", "certeza", 2.0);

        check(calculator, "aviea", "aveia", 1.0);
        check(calculator, "aviea", "aia", 2.0);
        check(calculator, "aviea", "ave", 2.0);
        check(calculator, "aviea", "via", 2.0);
        check(calculator, "aviea", "avioes", 2.0);
        check(calculator, "aviea", "avisar", 2.0);
        check(calculator, "aviea", "avivar", 2.0);
        check(calculator, "aviea", "alinea", 2.0);
        check(calculator, "aviea", "avidez", 2.0);
        check(calculator, "aviea", "ravina", 2.0);
    }

    @Test
    public void testLevenshteinTecladoQwerty()
    {
        KeyboardLayout layout = layouts.getLayoutByName("QWERTY");
        IDistanceCalculator calculator = new LevenshteinCalculator(layout);

        check(calculator, "casa", "casa", 0.0);
        check(calculator, "casa", "cada", 0.11);
        check(calculator, "casa", "caca", 0.20);
        check(calculator, "casa", "cara", 0.20);
        check(calculator, "casa", "fada", 0.23);
        check(calculator, "casa", "vaza", 0.23);
        //check(calculator, "casa", "asa", 0.25);  //TODO: RESOLVER MUDANÇA DE PRIMEIRA LETRA
        check(calculator, "casa", "cas", 0.25);
        check(calculator, "casa", "casal", 0.25);
        check(calculator, "casa", "casar", 0.25);

        check(calculator, "cervega", "cerveja", 0.22);
        check(calculator, "cervega", "cereja", 0.47);
        check(calculator, "cervega", "xereta", 0.48);
        check(calculator, "cervega", "verbete", 0.54);
        check(calculator, "cervega", "careta", 0.57);
        check(calculator, "cervega", "ferver", 0.57);
        check(calculator, "cervega", "carreta", 0.57);
        check(calculator, "cervega", "vereda", 0.58);
        check(calculator, "cervega", "refrega", 0.59);
        check(calculator, "cervega", "cerca", 0.61);

        check(calculator, "aviea", "acida", 0.23);
        check(calculator, "aviea", "afora", 0.34);
        check(calculator, "aviea", "agora", 0.34);
        check(calculator, "aviea", "aries", 0.35);
        check(calculator, "aviea", "aves", 0.36);
        //check(calculator, "aviea", "vira", 0.36); //TODO: RESOLVER MUDANÇA DE PRIMEIRA LETRA
        //check(calculator, "aviea", "vies", 0.36); //TODO: RESOLVER MUDANÇA DE PRIMEIRA LETRA
        check(calculator, "aviea", "avioes", 0.36);
        //check(calculator, "aviea", "vida", 0.37); //TODO: RESOLVER MUDANÇA DE PRIMEIRA LETRA
        check(calculator, "aviea", "avisar", 0.37);
    }

    @Test
    public void testDemerauTecladoQwerty()
    {
        KeyboardLayout layout = layouts.getLayoutByName("QWERTY");
        IDistanceCalculator calculator = new DemerauLevenshteinCalculator(layout);

        check(calculator, "casa", "casa", 0.0);
        check(calculator, "casa", "cada", 0.11);
        check(calculator, "casa", "caca", 0.20);
        check(calculator, "casa", "cara", 0.20);
        check(calculator, "casa", "fada", 0.23);
        check(calculator, "casa", "vaza", 0.23);
        //check(calculator, "casa", "asa", 0.25); //TODO: RESOLVER MUDANÇA DE PRIMEIRA LETRA
        check(calculator, "casa", "cas", 0.25);
        check(calculator, "casa", "casal", 0.25);
        check(calculator, "casa", "casar", 0.25);

        check(calculator, "cervega", "cerveja", 0.22);
        check(calculator, "cervega", "cereja", 0.47);
        check(calculator, "cervega", "xereta", 0.48);
        check(calculator, "cervega", "verbete", 0.54);
        check(calculator, "cervega", "careta", 0.57);
        check(calculator, "cervega", "ferver", 0.57);
        check(calculator, "cervega", "carreta", 0.57);
        check(calculator, "cervega", "vereda", 0.58);
        check(calculator, "cervega", "refrega", 0.59);
        check(calculator, "cervega", "cerca", 0.61);

        check(calculator, "aviea", "acida", 0.23);
        //check(calculator, "aviea", "aveia", 0.25); //TODO: ENTENDER PQ BUGOU AQUI
        check(calculator, "aviea", "afora", 0.34);
        check(calculator, "aviea", "agora", 0.34);
        check(calculator, "aviea", "aries", 0.35);
        check(calculator, "aviea", "aves", 0.36);
        check(calculator, "aviea", "vira", 0.36);
        check(calculator, "aviea", "vies", 0.36);
        check(calculator, "aviea", "avioes", 0.36);
        check(calculator, "aviea", "vida", 0.37);
    }

    @Test
    public void testLevenshteinTecladoDvorak()
    {
        KeyboardLayout layout = layouts.getLayoutByName("DVORAK");
        IDistanceCalculator calculator = new LevenshteinCalculator(layout);

        check(calculator, "casa", "casa", 0.0);
        check(calculator, "casa", "cana", 0.11);
        check(calculator, "casa", "caso", 0.11);
        check(calculator, "casa", "cara", 0.15);
        check(calculator, "casa", "nasa", 0.15);
        check(calculator, "casa","gana", 0.22);
        check(calculator, "casa", "cano", 0.22);
        check(calculator, "casa", "gala", 0.22);
        check(calculator, "casa", "tala", 0.22);
        check(calculator, "casa", "tosa", 0.22);

        check(calculator, "cervega", "corveta", 0.26);
        check(calculator, "cervega", "corveia", 0.35);
        check(calculator, "cervega", "corneta", 0.37);
        check(calculator, "cervega", "corrego", 0.44);
        check(calculator, "cervega", "cornea", 0.47);
        check(calculator, "cervega", "colega", 0.47);
        check(calculator, "cervega", "nervura", 0.48);
        check(calculator, "cervega", "cerveja", 0.49);
        check(calculator, "cervega", "centena", 0.51);
        check(calculator, "cervega", "ternura", 0.55);
    }

    private void check(IDistanceCalculator calculator, String target, String word, double distance)
    {
        assertEquals(distance, calculator.calculateDistance(target,word), 0.01);
    }


}
