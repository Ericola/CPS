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
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// operation
		mine.init( l,  h);
		
		//oracle
		assertPerso("testInitPositif, La hauteur de la mines ne s'est pas initialisee correctement", mine.getHauteur() == h);
		assertPerso("testInitPositif, La largeur de la mines ne s'est pas initialisee correctement",  mine.getLargeur() == l);
		assertPerso("testInitPositif, La Qtor de la mines ne s'est pas initialisee correctement",  mine.orRestant() == 51);
		assertPerso("testInitPositif, L abandonCompteur de la mines ne s'est pas initialisee correctement",  mine.abandonCompteur() == 51);
		assertPerso("testInitPositif, La Appartenance de la mines ne s'est pas initialisee correctement",  mine.appartenance() == ERace.RIEN);
		
	}
	
	
	@Test
	public void testSetAppartenancePositif() {
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune
		mine.init( l,  h);
		// operation
		mine.setAppartenance(ERace.ORC);
		
		//oracle
		
		assertPerso("testSetAppartenancePositif, L'appartenance de la mine n'est pas la bonne ", mine.appartenance() == ERace.ORC);
	
		
	}
	
	@Test
	public void testRetraitPositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// operation
		mine.init( l,  h);
		
		mine.retrait(1);
		
		assertPerso("testRetraitPositif, Le retrait de la mine ne s'est pas fait correctement", mine.orRestant() == 50);
	}
	
	@Test
	public void testSetAbandonCompteurPositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// operation
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		
		mine.setAbandonCompteur(1);
		
		assertPerso("testSetAbandonCompteurPositif, le setAbandonCompteur de la mine ne s'est pas fait correctement", mine.abandonCompteur() == 1);
	}
	
	
	
	@Test
	public void testEstAbandonnePositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// operation
		mine.init( l,  h);
		
		
		
		assertPerso("testEstAbandonnePositif, estAbandonne de la mine n'est pas effectif alors qu'il le devrait", mine.estAbandonnee() == true);
		
				
	}
	
	@Test
	public void testEstLamineePositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// operation
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		
	//	mine.setAbandonCompteur(1);
		
		mine.retrait(51);
		
		
		//Oracle
		
		assertPerso("testEstLamineePositif, La mine n'est pas Laminee alors qu'elle le devrait ", mine.estLaminee() == true);
		
				
	}
	
	@Test
	public void testAccueilPositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale :
		mine.init( l,  h);
		// operation
		
		mine.accueil(ERace.HUMAIN);
		
		
		//oracle
		assertPerso("testAccueilPositif, Le accueil de la mine ne s'est pas fait correctement", mine.appartenance() == ERace.HUMAIN);
	}
	
}
