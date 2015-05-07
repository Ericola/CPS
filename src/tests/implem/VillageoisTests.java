package tests.implem;

import implementations.VillageoisImplem;

import org.junit.Before;

import tests.AbstractVillageois;

public class VillageoisTests extends AbstractVillageois {

	@Override
	@Before
	public void before() {
		villageois = new VillageoisImplem();
		
	}

}
