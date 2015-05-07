package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;

import services.IVillageoisService;


@Done
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
	public void testInitPositif2() {
		/*int x = 2;
		int y = 2;
		ETypeChampion typeChamp = ETypeChampion.KIDNAPPEUR;
		// condition initiale : aucune

		// opération
		champion.init(typeChamp, x, y);

		// oracle
		assertPerso("init, Le positionnement en X du héro ne ce fait pas correctement", champion.getX() == x);
		assertPerso("init, Le positionnement en Y du héro ne ce fait pas correctement", champion.getY() == y);
		assertPerso("init, La commande initial du héro est différente de COMMANDE.RIEN", champion.getTypeChampion() == typeChamp);
		assertPerso("init, Le héro n'est pas SANTE.VIVANT", champion.getSante() == ESante.VIVANT);
		assertPerso("init, le type du champion n'est pas du bon type", champion.getTypeChampion() == ETypeChampion.KIDNAPPEUR);
		for (ETresor tresor : ETresor.values()) {
			assertPerso("init, le nombre de trésor n'est pas initialisé correctement", champion.getNbTresor(tresor) == 0);
		}*/
	}

}