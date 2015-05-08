package tests;


import services.IMoteurJeuService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractMoteurJeu extends AbstractAssertion {
	
	protected IMoteurJeuService moteurJeu;
	@Before
	public abstract void before();

	@After
	public void after() {
		moteurJeu = null;
	}
	
	@Test
	public void test0_1() {

		//Condition Initiale : aucune
		
		//Opération
		moteurJeu.init(1664, 1000, 1000);
		
		//Oracle
		assertPerso("Le nombre de pas de jeu n'est pas égale à 100", moteurJeu.MaxPasJeu() == 1664);
		assertPerso("La valeur du pas courant n'est pas initialiser à 0", moteurJeu.pasJeuCourant() == 0);
		
		//moteurJeu.
		
	}
	
}
