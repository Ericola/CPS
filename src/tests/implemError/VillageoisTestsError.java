package tests.implemError;

import implementationsError.VillageoisImplemError;

import org.junit.Before;

import tests.AbstractVillageois;

public class VillageoisTestsError extends AbstractVillageois {

	@Override
	@Before
	public void before() {
		villageois = new VillageoisImplemError();
		
	}

}
