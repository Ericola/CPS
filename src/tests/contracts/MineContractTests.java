package tests.contracts;



import implementations.MineImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.MineContract;
import exceptions.PreconditionError;
import tests.AbstractMine;

public class MineContractTests extends AbstractMine{
	@Override
	@Before
	public void before() {
		mine = new MineContract(new MineImplem());
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
