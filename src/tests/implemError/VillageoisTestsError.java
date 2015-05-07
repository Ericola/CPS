package tests.implemError;

import implementations.VillageoisImplem;

import org.junit.Before;

import tests.AbstractVillageois;

public class VillageoisTestsError extends AbstractVillageois {

	@Override
	@Before
	public void before() {
		villageois = new VillageoisImplem();
		
	}

}
