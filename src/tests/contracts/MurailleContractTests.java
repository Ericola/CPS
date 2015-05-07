package tests.contracts;



import implementations.MurailleImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.MurailleContract;
import exceptions.PreconditionError;
import tests.AbstractMuraille;

public class MurailleContractTests extends AbstractMuraille{
	@Override
	@Before
	public void before() {
		muraille = new MurailleContract(new MurailleImplem());
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
