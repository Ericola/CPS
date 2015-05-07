package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IHotelVilleService;

public abstract class AbstractHotelDeVille extends AbstractAssertion {
	protected IHotelVilleService hdv;
	
	@Before
	public abstract void before();

	@After
	public void after() {
		hdv = null;
	}


	@Test
	public void testInitPositif() {
		int l = 50;
		int h = 50;
	
		
		// condition initiale : aucune

		// opération
		hdv.init( l,  h);
		
		assertPerso("init, La hauteur de la hdv ne ce fait pas correctement", hdv.getHauteur() == h);
		assertPerso("init, La largeur de la hdv ne ce fait pas correctement",  hdv.getLargeur() == l);
		assertPerso("init, La largeur de la hdv ne ce fait pas correctement",  hdv.orRestant() == 51);
	}
	
	
}
