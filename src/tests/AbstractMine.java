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

		// opération
		mine.init( l,  h);
		
		assertPerso("init, La hauteur de la mines ne s'est pas initialisée correctement", mine.getHauteur() == h);
		assertPerso("init, La largeur de la mines ne s'est pas initialisée correctement",  mine.getLargeur() == l);
		assertPerso("init, La Qtor de la mines ne s'est pas initialisée correctement",  mine.orRestant() == 51);
		assertPerso("init, L abandonCompteur de la mines ne s'est pas initialisé correctement",  mine.abandonCompteur() == 51);
		assertPerso("init, La Appartenance de la mines ne s'est pas initialisée correctement",  mine.appartenance() == ERace.RIEN);
		
	}
	
	
	@Test
	public void testSetAppartenancePositif() {
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		assertPerso("setAppartenance, L'appartenance de la mine n'est pas la bonne ", mine.appartenance() == ERace.ORC);
	
		
	}
	
	@Test
	public void testRetraitPositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.retrait(1);
		
		assertPerso("retrait, Le retrait de la mine ne s'est pas fait correctement", mine.orRestant() == 50);
	}
	
	@Test
	public void testSetAbandonCompteurPositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		
		mine.setAbandonCompteur(1);
		
		assertPerso("setAbandonCompteur, le setAbandonCompteur de la mine ne s'est pas fait correctement", mine.abandonCompteur() == 1);
	}
	
	
	
	@Test
	public void testEstAbandonnePositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		
		
		assertPerso("estAbandonne, estAbandonne de la mine n'est pas effectif alors qu'il le devrait", mine.estAbandonnee() == true);
		
				
	}
	
	@Test
	public void testEstLamineePositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale : aucune

		// opération
		mine.init( l,  h);
		
		mine.setAppartenance(ERace.ORC);
		
	//	mine.setAbandonCompteur(1);
		
		mine.retrait(51);
		
		
		//Oracle
		
		assertPerso("estLaminé, La mine n'est pas Laminée alors qu'elle le devrait ", mine.estLaminee() == true);
		
				
	}
	
	@Test
	public void testAccueilPositif(){
		int l = 50;
		int h = 50;
		
		// condition initiale :
		mine.init( l,  h);
		// opération
		
		mine.accueil(ERace.HUMAIN);
		
		
		//oracle
		assertPerso("accueil, Le accueil de la mine ne s'est pas fait correctement", mine.appartenance() == ERace.HUMAIN);
	}
	//TODO TEST SUR EST LAMINE ET OR...?
	
	
}
