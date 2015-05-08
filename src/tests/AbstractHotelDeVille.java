package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;

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
		ERace race = ERace.HUMAIN;
		
		// condition initiale : aucune

		// opération
		hdv.init( l,  h, race);
		
		assertPerso("init, La hauteur de la hdv ne ce fait pas correctement", hdv.getHauteur() == h);
		assertPerso("init, La largeur de la hdv ne ce fait pas correctement",  hdv.getLargeur() == l);
		assertPerso("init, La largeur de la hdv ne ce fait pas correctement",  hdv.appartenance() == race);
		assertPerso("init, La largeur de la hdv ne ce fait pas correctement",  hdv.orRestant() == 51);
	}
	
	@Test
	public void testDepotPositif() {
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;
		
		// condition initiale : aucune

		// opération
		hdv.init( l,  h, race);
		  
		hdv.depot(4); 
		
		assertPerso("init, La hauteur de la hdv ne ce fait pas correctement", hdv.orRestant() == 55);
	
	}
	
	
}
