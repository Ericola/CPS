package tests.contracts;



import implementations.VillageoisImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.VillageoisContract;
import exceptions.PreconditionError;
import tests.AbstractVillageois;

public class VillageoisContractTests extends AbstractVillageois{
	@Override
	@Before
	public void before() {
		villageois = new VillageoisContract(new VillageoisImplem());
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
