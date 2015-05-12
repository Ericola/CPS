package tests.implemError;

import implementations.RouteImplem;
import implementations.VillageoisImplem;
import implementationsError.RouteImplemError;

import org.junit.Before;

import tests.AbstractRoute;
import tests.AbstractVillageois;

public class RouteTestsError extends AbstractRoute {

	@Override
	@Before
	public void before() {
		route = new RouteImplemError();
		
	}

}
