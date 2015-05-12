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

		// operation
		muraille.init( l,  h, pdv);
		
		assertPerso("init, La hauteur de la muraille ne s'est pas initialisee correctement", muraille.getHauteur() == h);
		assertPerso("init, La largeur de la muraille ne s'est pas initialisee correctement",  muraille.getLargeur() == l);
		assertPerso("init, Les points de vie de la muraille ne s'est pas initialisee correctement",  muraille.getPdv() == pdv);
	}
	
	@Test
	public void testRetraitPositif() {
		int l = 50;
		int h = 50;
		int pdv = 50;
		
		// condition initiale : aucune

		// operation
		muraille.init( l,  h, pdv);
		
		muraille.retrait(5);
		
		
		assertPerso("retrait, Le retrait de vie de la muraille n'a pas fonctionne correctement",  muraille.getPdv() == 45);
	}
	
	@Test
	public void testEstMortPositif() {
		int l = 50;
		int h = 50;
		int pdv = 50;
		
		// condition initiale : aucune

		// operation
		muraille.init( l,  h, pdv);
		
		muraille.retrait(50);
		
		
		assertPerso("estMort, Le retrait de vie qui doit detruire la muraille n'a pas fonctionne correctement",  muraille.estMort() == true);
	}
}
