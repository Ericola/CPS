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
		
		//oracle
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
		//oracle
		assertPerso("init, La hauteur de la hdv ne ce fait pas correctement", hdv.orRestant() == 55);
	
	}
	
	@Test
	public void testAbandonedPositif() {
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;
		
		// condition initiale : aucune

		// opération
		hdv.init( l,  h, race);
		  
		hdv.abandoned();
		//oracle
		assertPerso("abandoned, Le abandoned de hdv ne ce fait pas correctement", hdv.appartenance() == ERace.RIEN);
	
	}
	
	@Test
	public void testAccueilPositif() {
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;
		
		// condition initiale : aucune

		// opération
		hdv.init( l,  h, race);
		hdv.abandoned();  
		hdv.accueil(ERace.ORC);
		//oracle
		assertPerso("init, La hauteur de la hdv ne ce fait pas correctement", hdv.appartenance() == ERace.ORC);
	
	}
	@Test
	public void testSetOrRestantPositif(){
		int l = 50;
		int h = 50;
		ERace race = ERace.HUMAIN;
		
		// condition initiale : aucune

		// opération
		hdv.init( l,  h, race);
		hdv.setOrRestant(40);
		
		//oracle
		assertPerso("init, orRestant de la hdv ne s'est pas fait correctement", hdv.orRestant()== 40);
	
	}
	@Test
	 public void testSetAppartenance(){
		 int l =50;
		  int h= 50;
		  
		  ERace race = ERace.RIEN;
		  
		// condition initiale : aucune

			// opération
			hdv.init( l,  h, race);
			hdv.setAppartenance(ERace.ORC);
			
			//oracle
			assertPerso("setappartenance, SetAppartenance de la hdv ne s'est pas fait correctement", hdv.appartenance()== ERace.ORC);
		
		 
	 }
	
 
	@Test	
	 public void testSetAbandonCompteur(){

		 int l =50;
		  int h= 50;
		  
		  ERace race = ERace.RIEN;
		  
		// condition initiale : aucune

			// opération
			hdv.init( l,  h, race);
			hdv.setAbandonCompteur(51);
			
			//oracle
			assertPerso("setAbandonCompteur, SetAbandonCompteur de la hdv ne s'est pas fait correctement", hdv.appartenance()== ERace.RIEN);
		
		 
	 }
}
