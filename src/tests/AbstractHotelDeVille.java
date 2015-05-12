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

		// operation

		hdv.init( l,  h, race);

		//oracle
		assertPerso("testInitPositif, La hauteur de la hdv ne s'est pas initialisee correctement", hdv.getHauteur() == h);
		assertPerso("testInitPositif, La largeur de la hdv ne s'est pas initialisee correctement",  hdv.getLargeur() == l);
		assertPerso("testInitPositif, L'appartenance de la hdv ne s'est pas initialisee correctement",  hdv.appartenance() == race);
		assertPerso("testInitPositif, L'or restant de la hdv ne s'est pas initialisee correctement",  hdv.orRestant() == 16);
		assertPerso("testInitPositif, L'or restant de la hdv ne s'est pas initialisee correctement",  hdv.abandonCompteur() == 0);
	}


	@Test
	public void testDepotPositif() {
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;

		// condition initiale : aucune
		hdv.init( l,  h, race);
		// operation


		hdv.depot(4); 
		//oracle
		assertPerso("testDepotPositif, Le depot de l'hdv n'a pas fonctionnee", hdv.orRestant() == 20);

	}

	@Test
	public void testAbandonedPositif() {
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;

		// condition initiale : aucune
		hdv.init( l,  h, race);
		// operation

		hdv.abandoned();
		//oracle
		assertPerso("testAbandonedPositif, abandoned de hdv ne s'est pas fait pas correctement", hdv.appartenance() == ERace.RIEN);

	}

	@Test
	public void testAccueilPositif() {
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;

		// condition initiale : aucune
		hdv.init( l,  h, race);
		hdv.abandoned();
		// operation

		System.out.println("appartenance : " + hdv.appartenance());
		hdv.accueil(ERace.ORC);
		//oracle
		assertPerso("testAccueilPositif, L'accueil de la hdv n'a pas fonctionnee", hdv.appartenance() == ERace.ORC);

	}
	@Test
	public void testSetOrRestantPositif(){
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;

		// condition initiale : aucune
		hdv.init( l,  h, race);
		// operation

		hdv.setOrRestant(40);

		//oracle
		assertPerso("testSetOrRestantPositif, orRestant de la hdv ne s'est pas fait correctement", hdv.orRestant()== 40);

	}
	@Test
	public void testSetAppartenance(){
		int l =50;
		int h= 50;

		ERace race = ERace.RIEN;

		// condition initiale : aucune
		hdv.init( l,  h, race);
		// operation

		hdv.setAppartenance(ERace.ORC);
		//oracle
		assertPerso("testSetAppartenance, SetAppartenance de la hdv ne s'est pas fait correctement", hdv.appartenance()== ERace.ORC);


	}


	@Test	
	public void testSetAbandonCompteur(){

		int l =50;
		int h= 50;

		ERace race = ERace.HUMAIN;

		// condition initiale : aucune
		hdv.init( l,  h, race);
		// operation
		hdv.setAbandonCompteur(30);

		//oracle
		assertPerso("testSetAbandonCompteur, SetAbandonCompteur de la hdv ne s'est pas fait correctement", hdv.abandonCompteur()==30);


	}
}
