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
		
		assertPerso("init, La hauteur de la mines ne ce fait pas correctement", mine.getHauteur() == h);
		assertPerso("init, La largeur de la mines ne ce fait pas correctement",  mine.getLargeur() == l);
		assertPerso("init, La Qtor de la mines ne ce fait pas correctement",  mine.orRestant() == 51);
		assertPerso("init, La largeur de la mines ne ce fait pas correctement",  mine.abandonCompteur() == 51);
		assertPerso("init, La largeur de la mines ne ce fait pas correctement",  mine.appartenance() == ERace.RIEN);
		
	}
	
	
	@Test
	public void testSetAppartenancePositif() {
		int l = 2;
		int h = 2;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		assertPerso("setAppartenance, L'appartenance de la mine n'est pas la bonne ", mine.appartenance() == ERace.ORC);
	
		
	}
	
	@Test
	public void testRetraitPositif(){
		int l = 2;
		int h = 2;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.retrait(1);
		
		assertPerso("retrait, Le retrait de la mine ne s'est pas fait correctement", mine.orRestant() == 50);
	}
	
	@Test
	public void testSetAbandonCompteurPositif(){
		int l = 2;
		int h = 2;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		
		mine.setAbandonCompteur(1);
		
		assertPerso("retrait, Le retrait de la mine ne s'est pas fait correctement", mine.abandonCompteur() == 1);
	}
	
	
	//AFAIREBIEN !
	@Test
	public void testEstAbandonnePositif(){
		int l = 2;
		int h = 2;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		
		mine.setAbandonCompteur(1);
		
		mine.retrait(51);
		
		assertPerso("estLaminé, estLaminé de la mine n'est pas effectif alors qu'il le devrait", mine.estLaminee() == true);
		
				
	}
	
	@Test
	public void testEstLaminéePositif(){
		int l = 2;
		int h = 2;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		
		mine.setAbandonCompteur(1);
		
		mine.retrait(51);
		
		assertPerso("retrait, Le retrait de la mine ne s'est pas fait correctement", mine.estLaminee() == true);
		
				
	}
	
	
	//TODO TEST SUR EST LAMINE ET OR...?
	
	
}
