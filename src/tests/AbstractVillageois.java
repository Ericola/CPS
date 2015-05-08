package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;

import services.IVillageoisService;


//NOTDONE
public abstract class AbstractVillageois extends AbstractAssertion {

	protected IVillageoisService villageois;

	@Before
	public abstract void before();

	@After
	public void after() {
		villageois = null;
	}


	@Test
	public void testInitPositif() {
		int l = 2;
		int h = 2;
		int f =3;
		int v = 4;
		int pdv =10;
				
		ERace type = ERace.HUMAIN;
		// condition initiale : aucune

		// opération
		villageois.init(type, l,  h, f,v,pdv);

		// oracle
		assertPerso("init, La hauteur du villageois ne ce fait pas correctement", villageois.getHauteur() == h);
		assertPerso("init, La largeur du villageois ne ce fait pas correctement",  villageois.getLargeur() == l);
		assertPerso("init, La force du villageois ne ce fait pas correctement", villageois.getForce() == f);
		assertPerso("init, Les point de vie du villageois ne ce fait pas correctement", villageois.getPdv() == pdv);
		assertPerso("init, la vitesse du villageois n'est pas du bon type", villageois.getVitesse() == v);
	/*	for (ETresor tresor : ETresor.values()) {
			assertPerso("init, le nombre de trésor n'est pas initialisé correctement", champion.getNbTresor(tresor) == 0);
		}*/
	}

	@Test
	public void testSetQtorPositif() {
		int l = 2;
		int h = 2;
		int f =3;
		int v = 4;
		int pdv =10;
				
		ERace type = ERace.HUMAIN;
		// condition initiale : aucune

		// opération
		villageois.init(type, l,  h, f,v,pdv);
		villageois.setQtor(2);
		
		// oracle
		assertPerso("setQtor, setQtor ne ce fait pas correctement", villageois.getQtor() == 2);
		
	}
	
	@Test
	public void testRetraitOrPositif() {
		int l = 2;
		int h = 2;
		int f =3;
		int v = 4;
		int pdv =10;
				
		ERace type = ERace.HUMAIN;
		// condition initiale : aucune

		// opération
		villageois.init(type, l,  h, f,v,pdv);
		villageois.setQtor(2);
		villageois.retrait(1);
		
		// oracle
		assertPerso("retrait, retrait ne ce fait pas correctement", villageois.getQtor() == 1);
		
	}
		

}