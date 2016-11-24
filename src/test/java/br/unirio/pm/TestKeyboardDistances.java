package br.unirio.pm;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutList;
import br.unirio.pm.reader.KeyboardLayoutReader;

/**
 * Casos de teste para a distância entre teclas em modelos de teclado
 * 
 * @author Marcio Barros
 */
public class TestKeyboardDistances
{
	private static KeyboardLayoutList layouts;

	@BeforeClass
	public static void setup()
	{
		layouts = new KeyboardLayoutReader().loadFromFile("data/KeyboardLayouts.xml");
	}
	
	@Test
	public void testQwerty()
	{

		KeyboardLayout layout = layouts.getLayoutByName("QWERTY");
		layout.prepareDistances();
		assertEquals(0.0, layout.getNominalDistance('q', 'q'), 0.001);
		
		assertEquals(1.0, layout.getNominalDistance('q', 'w'), 0.001);
		assertEquals(1.0, layout.getNominalDistance('w', 'q'), 0.001);
		
		assertEquals(2.0, layout.getNominalDistance('q', 'e'), 0.001);
		assertEquals(2.0, layout.getNominalDistance('e', 'q'), 0.001);

		assertEquals(9.0, layout.getNominalDistance('q', 'p'), 0.001);
		assertEquals(9.0, layout.getNominalDistance('p', 'q'), 0.001);

		assertEquals(dist(0.5, 1), layout.getNominalDistance('q', 'a'), 0.001);
		assertEquals(dist(0.5, 1), layout.getNominalDistance('a', 'q'), 0.001);

		assertEquals(dist(1.5, 1), layout.getNominalDistance('q', 's'), 0.001);
		assertEquals(dist(1.5, 1), layout.getNominalDistance('s', 'q'), 0.001);

		assertEquals(dist(8.5, 1), layout.getNominalDistance('q', 'l'), 0.001);
		assertEquals(dist(8.5, 1), layout.getNominalDistance('l', 'q'), 0.001);

		assertEquals(dist(1.0, 2), layout.getNominalDistance('q', 'z'), 0.001);
		assertEquals(dist(1.0, 2), layout.getNominalDistance('z', 'q'), 0.001);

		assertEquals(dist(2.0, 2), layout.getNominalDistance('q', 'x'), 0.001);
		assertEquals(dist(2.0, 2), layout.getNominalDistance('x', 'q'), 0.001);

		assertEquals(dist(7.0, 2), layout.getNominalDistance('q', 'm'), 0.001);
		assertEquals(dist(7.0, 2), layout.getNominalDistance('m', 'q'), 0.001);

		assertEquals(dist(0.5, 1), layout.getNominalDistance('w', 'a'), 0.001);
		assertEquals(dist(0.5, 1), layout.getNominalDistance('a', 'w'), 0.001);

		assertEquals(dist(0.5, 1), layout.getNominalDistance('w', 's'), 0.001);
		assertEquals(dist(0.5, 1), layout.getNominalDistance('s', 'w'), 0.001);

		assertEquals(dist(0.0, 2), layout.getNominalDistance('w', 'z'), 0.001);
		assertEquals(dist(0.0, 2), layout.getNominalDistance('z', 'w'), 0.001);

		assertEquals(dist(0.5, 1), layout.getNominalDistance('a', 'z'), 0.001);
		assertEquals(dist(0.5, 1), layout.getNominalDistance('z', 'a'), 0.001);

		assertEquals(dist(8.0, 2), layout.getNominalDistance('p', 'z'), 0.001);
		assertEquals(dist(8.0, 2), layout.getNominalDistance('z', 'p'), 0.001);

		assertEquals(dist(0.5, 1), layout.getNominalDistance('g', 't'), 0.001);
		assertEquals(dist(1.5, 1), layout.getNominalDistance('a', 'e'), 0.001);
		assertEquals(dist(1.0, 0), layout.getNominalDistance('b', 'v'), 0.001);

		//assertEquals(0.25, layout.getInsertDeleteDistance(), 0.001);
		assertEquals(dist(9.0, 0), layout.getMaximumDistance(), 0.001);
	}
	
	@Test
	public void testDvorak()
	{
		KeyboardLayout layout = layouts.getLayoutByName("DVORAK");
		layout.prepareDistances();
		assertEquals(dist(3, 1), layout.getNominalDistance('p', 'a'), 0.001);
		assertEquals(dist(9, 1), layout.getNominalDistance('z', 'a'), 0.001);
		assertEquals(dist(9.055, 0), layout.getMaximumDistance(), 0.001);

	}
	
	private double dist(double width, double height)
	{
		return Math.sqrt(width * width + height * height);
	}

}