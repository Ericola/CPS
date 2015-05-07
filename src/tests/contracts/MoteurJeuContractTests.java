package tests.contracts;



import implementations.MoteurJeuImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.MoteurJeuContract;
import exceptions.PreconditionError;
import tests.AbstractMoteurJeu;

public class MoteurJeuContractTests extends AbstractMoteurJeu{
	@Override
	@Before
	public void before() {
		moteurJeu = new MoteurJeuContract(new MoteurJeuImplem());
	}

	/*@Test(expected=PreconditionError.class)
	public void testInitFail() {
		int x = 4;
		int y = 8;
		int amplitude = 2;
		// condition initiale : aucune

		// opération
		bombe.init(x, y, amplitude);

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}*/
}
