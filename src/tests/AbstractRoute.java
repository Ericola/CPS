package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IRouteService;

public abstract class AbstractRoute extends AbstractAssertion {

	protected IRouteService route;
	
	@Before
	public abstract void before();

	@After
	public void after() {
		route = null;
	}


	@Test
	public void testInitPositif() {
		int l = 2;
		int h = 2;
		
		// condition initiale : aucune

		// opération
		route.init( l,  h);
		
		assertPerso("init, La hauteur du villageois ne ce fait pas correctement", route.getHauteur() == h);
		assertPerso("init, La largeur du villageois ne ce fait pas correctement",  route.getLargeur() == l);
	}
	
	
}
