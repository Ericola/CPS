package tests.implem;

import implementations.HotelVilleImplem;
import implementations.VillageoisImplem;

import org.junit.Before;

import tests.AbstractHotelDeVille;

public class HotelVilleTests extends AbstractHotelDeVille {

	@Override
	@Before
	public void before() {
		hdv = new HotelVilleImplem();
		
		
	}

}
