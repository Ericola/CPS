package tests.implemError;

import implementations.HotelVilleImplem;
import implementations.VillageoisImplem;

import org.junit.Before;

import tests.AbstractHotelDeVille;

public class HotelVilleTestsError extends AbstractHotelDeVille {

	@Override
	@Before
	public void before() {
		hdv = new HotelVilleImplem();
		
		
	}

}
