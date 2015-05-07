package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;

import services.IMineService;

//NOTDONE
public abstract class AbstractMine extends AbstractAssertion {

	protected IMineService mine;

	@Before
	public abstract void before();

	@After
	public void after() {
		mine = null;
	}

	
	@Test
	public void testInitPositif() {
		int l = 2;
		int h = 2;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		assertPerso("init, La hauteur du villageois ne ce fait pas correctement", mine.getHauteur() == h);
		assertPerso("init, La largeur du villageois ne ce fait pas correctement",  mine.getLargeur() == l);
		assertPerso("init, La largeur du villageois ne ce fait pas correctement",  mine.orRestant() == 51);
		assertPerso("init, La largeur du villageois ne ce fait pas correctement",  mine.abandonCompteur() == 51);
		assertPerso("init, La largeur du villageois ne ce fait pas correctement",  mine.appartenance() == ERace.RIEN);
		
	}
	
	
	
	
	//TODO TEST SUR EST LAMINE ET OR...?
	
	
}
