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
	
}
