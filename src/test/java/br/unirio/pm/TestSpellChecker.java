<<<<<<< HEAD
package br.unirio.pm;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

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

/**
 * Casos de teste do verificador ortográfico
 * 
 * @author marciobarros
 */
public class TestSpellChecker
{
	private static KeyboardLayoutList layouts;

	@BeforeClass
	public static void setup()
	{
		layouts = new KeyboardLayoutReader().LoadFromFile("data/keyboardlayouts.xml");
		
		for (KeyboardLayout layout : layouts)
			layout.PrepareDistances();
	}
	
	@Test
	public void testLevenshteintTecladoNeutro()
	{
		KeyboardLayout layout = new KeyboardLayoutNeutro();
		IDistanceCalculator calculator = new LevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result1 = tree.search("casa", 1, 10);
		check(result1, 0, "casa", 0.0);
		check(result1, 1, "asa", 1.0);
		check(result1, 2, "cas", 1.0);
		check(result1, 3, "casar", 1.0);
		check(result1, 4, "casal", 1.0);
		check(result1, 5, "causa", 1.0);
		check(result1, 6, "casba", 1.0);
		check(result1, 7, "casca", 1.0);
		check(result1, 8, "casta", 1.0);
		check(result1, 9, "caca", 1.0);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 1.0);
		check(result2, 1, "cereja", 2.0);
		check(result2, 2, "corveta", 2.0);
		check(result2, 3, "corveia", 2.0);
		check(result2, 4, "certeza", 2.0);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "aia", 2.0);
		check(result3, 1, "ave", 2.0);
		check(result3, 2, "via", 2.0);
		check(result3, 3, "avioes", 2.0);
		check(result3, 4, "avisar", 2.0);
		check(result3, 5, "avivar", 2.0);
		check(result3, 6, "avidez", 2.0);
		check(result3, 7, "alinea", 2.0);
		check(result3, 8, "ravina", 2.0);
		check(result3, 9, "vies", 2.0);
	}
	
	@Test
	public void testDemerauTecladoNeutro()
	{
		KeyboardLayout layout = new KeyboardLayoutNeutro();
		IDistanceCalculator calculator = new DemerauLevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result1 = tree.search("casa", 1, 10);
		check(result1, 0, "casa", 0.0);
		check(result1, 1, "asa", 1.0);
		check(result1, 2, "cas", 1.0);
		check(result1, 3, "casar", 1.0);
		check(result1, 4, "casal", 1.0);
		check(result1, 5, "causa", 1.0);
		check(result1, 6, "casba", 1.0);
		check(result1, 7, "casca", 1.0);
		check(result1, 8, "casta", 1.0);
		check(result1, 9, "caca", 1.0);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 1.0);
		check(result2, 1, "cereja", 2.0);
		check(result2, 2, "corveta", 2.0);
		check(result2, 3, "corveia", 2.0);
		check(result2, 4, "certeza", 2.0);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "aveia", 1.0);
		check(result3, 1, "aia", 2.0);
		check(result3, 2, "ave", 2.0);
		check(result3, 3, "via", 2.0);
		check(result3, 4, "avioes", 2.0);
		check(result3, 5, "avisar", 2.0);
		check(result3, 6, "avivar", 2.0);
		check(result3, 7, "alinea", 2.0);
		check(result3, 8, "avidez", 2.0);
		check(result3, 9, "ravina", 2.0);
	}
	
	@Test
	public void testLevenshteinTecladoQwerty()
	{
		KeyboardLayout layout = layouts.getLayoutByName("QWERTY");
		IDistanceCalculator calculator = new LevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result = tree.search("casa", 1, 10);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);
		check(result, 2, "caca", 0.20);
		check(result, 3, "cara", 0.20);
		check(result, 4, "fada", 0.23);
		check(result, 5, "vaza", 0.23);
		check(result, 6, "asa", 0.25);
		check(result, 7, "cas", 0.25);
		check(result, 8, "casal", 0.25);
		check(result, 9, "casar", 0.25);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 4, "careta", 0.57);
		check(result2, 5, "ferver", 0.57);
		check(result2, 6, "carreta", 0.57);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);
		check(result2, 9, "cerca", 0.61);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "acida", 0.23);
		check(result3, 1, "afora", 0.34);
		check(result3, 2, "agora", 0.34);
		check(result3, 3, "aries", 0.35);
		check(result3, 4, "aves", 0.36);
		check(result3, 5, "vira", 0.36);
		check(result3, 6, "vies", 0.36);
		check(result3, 7, "avioes", 0.36);
		check(result3, 8, "vida", 0.37);
		check(result3, 9, "avisar", 0.37);
	}
	
	@Test
	public void testDemerauTecladoQwerty()
	{
		KeyboardLayout layout = layouts.getLayoutByName("QWERTY");
		IDistanceCalculator calculator = new DemerauLevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result = tree.search("casa", 1, 10);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);
		check(result, 2, "caca", 0.20);
		check(result, 3, "cara", 0.20);
		check(result, 4, "fada", 0.23);
		check(result, 5, "vaza", 0.23);
		check(result, 6, "asa", 0.25);
		check(result, 7, "cas", 0.25);
		check(result, 8, "casal", 0.25);
		check(result, 9, "casar", 0.25);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 4, "careta", 0.57);
		check(result2, 5, "ferver", 0.57);
		check(result2, 6, "carreta", 0.57);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);
		check(result2, 9, "cerca", 0.61);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "acida", 0.23);
		check(result3, 1, "aveia", 0.25);
		check(result3, 2, "afora", 0.34);
		check(result3, 3, "agora", 0.34);
		check(result3, 4, "aries", 0.35);
		check(result3, 5, "aves", 0.36);
		check(result3, 6, "vira", 0.36);
		check(result3, 7, "vies", 0.36);
		check(result3, 8, "avioes", 0.36);
		check(result3, 9, "vida", 0.37);
	}
	
	@Test
	public void testLevenshteinTecladoDvorak()
	{
		KeyboardLayout layout = layouts.getLayoutByName("DVORAK");
		IDistanceCalculator calculator = new LevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result = tree.search("casa", 1, 10);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cana", 0.11);
		check(result, 2, "caso", 0.11);
		check(result, 3, "cara", 0.15);
		check(result, 4, "nasa", 0.15);
		check(result, 5, "gana", 0.22);
		check(result, 6, "cano", 0.22);
		check(result, 7, "gala", 0.22);
		check(result, 8, "tala", 0.22);
		check(result, 9, "tosa", 0.22);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "corveta", 0.26);
		check(result2, 1, "corveia", 0.35);
		check(result2, 2, "corneta", 0.37);
		check(result2, 3, "corrego", 0.44);
		check(result2, 4, "cornea", 0.47);
		check(result2, 5, "colega", 0.47);
		check(result2, 6, "nervura", 0.48);
		check(result2, 7, "cerveja", 0.49);
		check(result2, 8, "centena", 0.51);
		check(result2, 9, "ternura", 0.55);
	}

	private void check(BurkhardKellerTreeSearchResult result, int position, String word, double distance)
	{
		assertEquals(word, result.getWord(position));
		assertEquals(distance, result.getDistance(position), 0.001);
	}
=======
package br.unirio.pm;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

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

/**
 * Casos de teste do verificador ortográfico
 * 
 * @author marciobarros
 */
public class TestSpellChecker
{
	private static KeyboardLayoutList layouts;

	@BeforeClass
	public static void setup()
	{
		layouts = new KeyboardLayoutReader().LoadFromFile("data/keyboardlayouts.xml");
		
		for (KeyboardLayout layout : layouts)
			layout.PrepareDistances();
	}
	
	@Test
	public void testLevenshteintTecladoNeutro()
	{
		KeyboardLayout layout = new KeyboardLayoutNeutro();
		IDistanceCalculator calculator = new LevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result1 = tree.search("casa", 1, 10);
		check(result1, 0, "casa", 0.0);
		check(result1, 1, "asa", 1.0);
		check(result1, 2, "cas", 1.0);
		check(result1, 3, "casar", 1.0);
		check(result1, 4, "casal", 1.0);
		check(result1, 5, "causa", 1.0);
		check(result1, 6, "casba", 1.0);
		check(result1, 7, "casca", 1.0);
		check(result1, 8, "casta", 1.0);
		check(result1, 9, "caca", 1.0);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 1.0);
		check(result2, 1, "cereja", 2.0);
		check(result2, 2, "corveta", 2.0);
		check(result2, 3, "corveia", 2.0);
		check(result2, 4, "certeza", 2.0);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "aia", 2.0);
		check(result3, 1, "ave", 2.0);
		check(result3, 2, "via", 2.0);
		check(result3, 3, "avioes", 2.0);
		check(result3, 4, "avisar", 2.0);
		check(result3, 5, "avivar", 2.0);
		check(result3, 6, "avidez", 2.0);
		check(result3, 7, "alinea", 2.0);
		check(result3, 8, "ravina", 2.0);
		check(result3, 9, "vies", 2.0);
	}
	
	@Test
	public void testDemerauTecladoNeutro()
	{
		KeyboardLayout layout = new KeyboardLayoutNeutro();
		IDistanceCalculator calculator = new DemerauLevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result1 = tree.search("casa", 1, 10);
		check(result1, 0, "casa", 0.0);
		check(result1, 1, "asa", 1.0);
		check(result1, 2, "cas", 1.0);
		check(result1, 3, "casar", 1.0);
		check(result1, 4, "casal", 1.0);
		check(result1, 5, "causa", 1.0);
		check(result1, 6, "casba", 1.0);
		check(result1, 7, "casca", 1.0);
		check(result1, 8, "casta", 1.0);
		check(result1, 9, "caca", 1.0);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 1.0);
		check(result2, 1, "cereja", 2.0);
		check(result2, 2, "corveta", 2.0);
		check(result2, 3, "corveia", 2.0);
		check(result2, 4, "certeza", 2.0);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "aveia", 1.0);
		check(result3, 1, "aia", 2.0);
		check(result3, 2, "ave", 2.0);
		check(result3, 3, "via", 2.0);
		check(result3, 4, "avioes", 2.0);
		check(result3, 5, "avisar", 2.0);
		check(result3, 6, "avivar", 2.0);
		check(result3, 7, "alinea", 2.0);
		check(result3, 8, "avidez", 2.0);
		check(result3, 9, "ravina", 2.0);
	}
	
	@Test
	public void testLevenshteinTecladoQwerty()
	{
		KeyboardLayout layout = layouts.getLayoutByName("QWERTY");
		IDistanceCalculator calculator = new LevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result = tree.search("casa", 1, 10);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);
		check(result, 2, "caca", 0.20);
		check(result, 3, "cara", 0.20);
		check(result, 4, "fada", 0.23);
		check(result, 5, "vaza", 0.23);
		check(result, 6, "asa", 0.25);
		check(result, 7, "cas", 0.25);
		check(result, 8, "casal", 0.25);
		check(result, 9, "casar", 0.25);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 4, "careta", 0.57);
		check(result2, 5, "ferver", 0.57);
		check(result2, 6, "carreta", 0.57);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);
		check(result2, 9, "cerca", 0.61);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "acida", 0.23);
		check(result3, 1, "afora", 0.34);
		check(result3, 2, "agora", 0.34);
		check(result3, 3, "aries", 0.35);
		check(result3, 4, "aves", 0.36);
		check(result3, 5, "vira", 0.36);
		check(result3, 6, "vies", 0.36);
		check(result3, 7, "avioes", 0.36);
		check(result3, 8, "vida", 0.37);
		check(result3, 9, "avisar", 0.37);
	}
	
	@Test
	public void testDemerauTecladoQwerty()
	{
		KeyboardLayout layout = layouts.getLayoutByName("QWERTY");
		IDistanceCalculator calculator = new DemerauLevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result = tree.search("casa", 1, 10);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);
		check(result, 2, "caca", 0.20);
		check(result, 3, "cara", 0.20);
		check(result, 4, "fada", 0.23);
		check(result, 5, "vaza", 0.23);
		check(result, 6, "asa", 0.25);
		check(result, 7, "cas", 0.25);
		check(result, 8, "casal", 0.25);
		check(result, 9, "casar", 0.25);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 4, "careta", 0.57);
		check(result2, 5, "ferver", 0.57);
		check(result2, 6, "carreta", 0.57);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);
		check(result2, 9, "cerca", 0.61);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "acida", 0.23);
		check(result3, 1, "aveia", 0.25);
		check(result3, 2, "afora", 0.34);
		check(result3, 3, "agora", 0.34);
		check(result3, 4, "aries", 0.35);
		check(result3, 5, "aves", 0.36);
		check(result3, 6, "vira", 0.36);
		check(result3, 7, "vies", 0.36);
		check(result3, 8, "avioes", 0.36);
		check(result3, 9, "vida", 0.37);
	}
	
	@Test
	public void testLevenshteinTecladoDvorak()
	{
		KeyboardLayout layout = layouts.getLayoutByName("DVORAK");
		IDistanceCalculator calculator = new LevenshteinCalculator(layout);
		BurkhardKellerTree tree = new DictionaryReader().loadFromFile("data/dictionary pt-br.zip", calculator);
		
		BurkhardKellerTreeSearchResult result = tree.search("casa", 1, 10);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cana", 0.11);
		check(result, 2, "caso", 0.11);
		check(result, 3, "cara", 0.15);
		check(result, 4, "nasa", 0.15);
		check(result, 5, "gana", 0.22);
		check(result, 6, "cano", 0.22);
		check(result, 7, "gala", 0.22);
		check(result, 8, "tala", 0.22);
		check(result, 9, "tosa", 0.22);
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "corveta", 0.26);
		check(result2, 1, "corveia", 0.35);
		check(result2, 2, "corneta", 0.37);
		check(result2, 3, "corrego", 0.44);
		check(result2, 4, "cornea", 0.47);
		check(result2, 5, "colega", 0.47);
		check(result2, 6, "nervura", 0.48);
		check(result2, 7, "cerveja", 0.49);
		check(result2, 8, "centena", 0.51);
		check(result2, 9, "ternura", 0.55);
	}

	private void check(BurkhardKellerTreeSearchResult result, int position, String word, double distance)
	{
		assertEquals(word, result.getWord(position));
		assertEquals(distance, result.getDistance(position), 0.001);
	}
>>>>>>> 87e1964a057b4007e898f944d7734fd97c07fce9
}