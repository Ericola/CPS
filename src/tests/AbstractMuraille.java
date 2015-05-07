package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IMurailleService;


public abstract class AbstractMuraille extends AbstractAssertion {

protected IMurailleService muraille;
	
	@Before
	public abstract void before();

	@After
	public void after() {
		muraille = null;
	}


	@Test
	public void testInitPositif() {
		int l = 50;
		int h = 50;
		int pdv = 50;
		
		// condition initiale : aucune

		// opération
		muraille.init( l,  h, pdv);
		
		assertPerso("init, La hauteur de la muraille ne ce fait pas correctement", muraille.getHauteur() == h);
		assertPerso("init, La largeur de la muraille ne ce fait pas correctement",  muraille.getLargeur() == l);
		assertPerso("init, La largeur de la muraille ne ce fait pas correctement",  muraille.getLargeur() == pdv);
	}
}
