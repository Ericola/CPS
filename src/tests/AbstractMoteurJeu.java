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
	}
	
}
