package tests.implemError;

import implementationsError.HotelVilleImplemError;

import org.junit.Before;

import tests.AbstractHotelDeVille;

public class HotelVilleTestsError extends AbstractHotelDeVille {

	@Override
	@Before
	public void before() {
		hdv = new HotelVilleImplemError();
		
		
	}

}
