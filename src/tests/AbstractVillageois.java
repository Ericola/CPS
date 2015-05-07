package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IVillageoisService;


@Done
public abstract class AbstractVillageois extends AbstractAssertion {

	protected IVillageoisService champion;

	@Before
	public abstract void before();

	@After
	public void after() {
		champion = null;
	}


	@Test
	public void testInitPositif() {
		/*int x = 2;
		int y = 2;
		ETypeChampion typeChamp = ETypeChampion.HERO;
		// condition initiale : aucune

		// opération
		champion.init(typeChamp, x, y);

		// oracle
		assertPerso("init, Le positionnement en X du héro ne ce fait pas correctement", champion.getX() == x);
		assertPerso("init, Le positionnement en Y du héro ne ce fait pas correctement", champion.getY() == y);
		assertPerso("init, La commande initial du héro est différente de COMMANDE.RIEN", champion.getTypeChampion() == typeChamp);
		assertPerso("init, Le héro n'est pas SANTE.VIVANT", champion.getSante() == ESante.VIVANT);
		assertPerso("init, le type du champion n'est pas du bon type", champion.getTypeChampion() == ETypeChampion.HERO);
		for (ETresor tresor : ETresor.values()) {
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