package tests.contracts;



import implementations.HotelVilleImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.HotelVilleContract;
import exceptions.PreconditionError;
import tests.AbstractHotelDeVille;

public class HotelVilleContractTests extends AbstractHotelDeVille{
	@Override
	@Before
	public void before() {
		hdv= new HotelVilleContract(new HotelVilleImplem());
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
