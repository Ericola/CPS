package tests.implem;

import implementations.RouteImplem;
import implementations.VillageoisImplem;

import org.junit.Before;

import tests.AbstractRoute;
import tests.AbstractVillageois;

public class RouteTests extends AbstractRoute {

	@Override
	@Before
	public void before() {
		route = new RouteImplem();
		
	}

}
